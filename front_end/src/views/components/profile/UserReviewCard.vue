<template>
  <div>
    <div>
      <!-- <div
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="busy"
        infinite-scroll-distance="10"
      > -->
        <div class="md-layout" style="margin:auto;">
          <!-- <ReviewWrite /> -->
          <div
            class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-95 my-card-container"
            v-for="r in reviewList"
            :key="r.id"
          >
            <div class="md-card md-card-blog md-theme-default text-left list-inline md-with-hover">
              <ProfileCard :info="r" :uid="r.uid"></ProfileCard>
              <hr />
              <div class="md-card-content" style="padding-top: 0px;" @click="detailModalShow(r)">
                <div class="my-card-img-box" style="height:200px;">
                  <img class="my-card-img" :src="r.imageData" />
                </div>
                <hr />
                <div class="my-card-content" style="display:inline-block">
                  <h4 class="my-card-content">{{r.title}}</h4>
                </div>
                <div style="display:inline-block; float:right">
                  <md-icon>favorite</md-icon>
                  {{r.liked}}
                </div>
              </div>
            </div>
          </div>
          <div v-if="busy" class="loadingBox md-layout-item md-size-100 mx-auto">
            <img src="@/assets/img/loading.gif" class="loadingImg" />
          </div>
        </div>
      <!-- </div> -->
    </div>

    <!-- Modal -->
    <modal v-if="isDetail" @close="detailModalHide">
      <template slot="header">
        <h4>{{this.detailModalData.location}}</h4>
        <md-button
          class="md-simple md-just-icon md-round modal-default-button"
          @click="detailModalHide"
        >
          <md-icon>clear</md-icon>
        </md-button>
      </template>

      <template slot="body">
        <div class="modal-body blockquote undefined">
          <img :src="this.detailModalData.imageData" class="modal-img img-raised rounded" />
          <h5>{{this.detailModalData.date | moment("YYYY MM DD, dddd")}}</h5>
          <p>{{this.detailModalData.content}}</p>
          <small>by {{this.detailModalData.nickname}}</small>
          <div class="my-hashtag-div">
            <small class="my-hashtag">{{this.detailModalData.hashtag}}</small>
          </div>
        </div>
      </template>

      <template slot="footer">
        <div>
          <md-button class="md-simple md-danger" v-if="isLike" @click="unLike()">
            <md-icon>favorite</md-icon>
            {{this.detailModalData.liked}}
          </md-button>

          <md-button class="md-simple" v-if="!isLike" @click="likeIt()">
            <md-icon>favorite</md-icon>
            {{this.detailModalData.liked}}
          </md-button>

          <md-button class="md-danger md-simple" @click="detailModalHide">Close</md-button>
        </div>
      </template>
    </modal>
    <!--Model end-->
  </div>
</template>


<script>
import { EventBus } from "@/main.js";
import ReviewService from "@/services/ReviewService.js";
import UserService from "@/services/UserService.js";
import UserProfileService from "@/services/UserProfileService.js";
import EthereumService from "@/services/EthereumService.js";
import LocationService from "@/services/LocationService.js";
import ReviewWrite from "@/views/components/review/ReviewWrite";
import ProfileCard from "@/views/components/profile/ProfileCard";
import { Tabs } from "@/components";
import { Modal } from "@/components";

export default {
  components: {
    Tabs,
    ReviewWrite,
    Modal,
    ProfileCard
  },
  data() {
    return {
      busy: false,
      reviewList: [],
      idx: 0,
      active: false,
      value: null,
      isDetail: false,
      detailModalData: null,
      distance: 10,
      isLike: false,
      latitude: 0,
      longitude: 0,
      loadingPageNumber: 6,
      isEnd: false
    };
  },
  mounted() {
    this.init();
    EventBus.$on("reloadReviewList", () => {
      this.init();
    });
  },
  methods: {
    loadMore: function() {
      if (!this.isEnd) {
        this.busy = true;
        setTimeout(() => {
          this.idx += this.loadingPageNumber;
          this.loading(reviewList => {
            if (reviewList.length == 0) this.isEnd = true;
            for (var i = 0; i < reviewList.length; i++) {
              this.reviewList.push(reviewList[i]);
            }
            this.busy = false;
          });
        }, 1000);
      }
    },
    init() {
      UserProfileService.getReviewByEmail(this.$store.state.user.email)
      .then(reviewList => {
        this.reviewList = reviewList.data;
      });
    }
    // ,
    // loading(callback) {
    //   ReviewService.getReviewListByLocation({
    //     start: this.idx,
    //     end: this.loadingPageNumber,
    //     latitude: this.latitude,
    //     longitude: this.longitude,
    //     distance: this.distance
    //   }).then(reviewList => {
    //     callback(reviewList.data);
    //   });
    // }
    ,
    detailModalShow(selectedData) {
      this.detailModalData = selectedData;
      EthereumService.read(selectedData.hash, content => {
        this.detailModalData.content = content;
        ReviewService.isLike({
          uid: this.$store.state.user.id,
          rid: selectedData.id
        }).then(response => {
          if (response.data == 0) this.isLike = false;
          else {
            this.detailModalData.like = response.data;
            this.isLike = true;
          }
          this.isDetail = true;
        });
      });
    },
    detailModalHide() {
      this.isDetail = false;
      this.detailModalData = null;
    },
    likeIt() {
      ReviewService.likeIt({
        uid: this.$store.state.user.id,
        rid: this.detailModalData.id
      }).then(response => {
        var max = response.data.max;
        if (response.data.point) {
          let point = max / 10;
          UserService.getUserById(this.detailModalData.uid).then(userInfo => {
            let to = userInfo.data.walletAddress;
            EthereumService.sendPoint(to, point, recept => {});
          });
        }
        this.detailModalData.like = response.data.id;
        this.detailModalData.liked = this.detailModalData.liked + 1;
        this.isLike = true;
      });
    },
    unLike() {
      ReviewService.unLike({
        uid: this.$store.state.user.id,
        rid: this.detailModalData.id,
        id: this.detailModalData.like
      }).then(response => {
        this.detailModalData.liked = this.detailModalData.liked - 1;
        this.isLike = false;
      });
    }
  }
};
</script>



<style>
.my-card-img {
  position: absolute;
  left: 50%;
  top: 50%;
  height: 100%;
  width: auto;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.my-card-img-box {
  position: relative;
  overflow: hidden;
}
.my-hashtag-div {
  margin-top: 10px;
}
.my-hashtag {
  float: left;
}
.my-card-content {
  margin: 0px;
}
.my-card-title {
  padding-top: 15px;
  padding-bottom: 0px;
  padding-left: 20px;
  padding-right: 20px;
}

.Avatar_image {
  max-width: 50px;
  max-height: 50px;
  border-radius: 50px;
}
.review-card-title {
  display: inline-block;
  padding: 5px;
  margin: 5px;
}
.modal-img {
  margin-bottom: 5px;
  max-height: 400px;
}
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.md-has-textarea + .md-layout {
  margin-top: 15px;
}

@media (min-width: 481px) {
  .mdquery-xs {
    display: none;
  }
}

/* 모바일*/
@media (max-width: 480px) {
  .mdquery-md {
    display: none;
  }
  .my-card-container {
    margin: auto;
    height: 380px;
  }
  .loadingImg {
    height: 50px;
    width: 50px;
    margin-top: 10%;
  }
}

.loadingImg {
  height: 50px;
  width: 50px;
}
.loadingBox {
  text-align: center;
}
</style>
