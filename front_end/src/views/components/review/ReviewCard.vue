<template>
  <div>
    <div>
      <div class="md-layout">
        <div
          class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-95 my-card-container"
          v-for="r in reviewList"
          :key="r.id"
          @click="detailModalShow(r)"
        >
          <div class="md-card md-card-blog md-theme-default text-left list-inline md-with-hover">
            <div class="my-card-title">
              <div style="display : inline-block">
                <div>
                  <img :src="r.userImgData" alt="Avatar" class="Avatar_image" />
                </div>
              </div>
              <div style="display : inline-block; position:absolute; left:80px">
                <div>
                  {{r.location}}
                  <!--장소-->
                </div>
                <div>
                  by {{r.nickname}}
                  <!--작성자-->
                </div>
              </div>
              <hr />
            </div>
            <div class="md-card-content" style="padding-top: 0px;">
              <img :src="r.imageData" class="img" style="height : 200px" />
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
      </div>
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
import EthereumService from "@/services/EthereumService.js";
import LocationService from "@/services/LocationService.js";
import ReviewCard from "@/views/components/review/ReviewCard";
import { Tabs } from "@/components";
import { Modal } from "@/components";

export default {
  components: {
    Tabs,
    ReviewCard,
    Modal
  },
  data() {
    return {
      reviewList: [{}],
      active: false,
      value: null,
      isDetail: false,
      detailModalData: null,
      distance: 5,
      isLike: false
    };
  },
  mounted() {
    this.init();
    EventBus.$on("reloadReviewList", () => {
      this.init();
    });
  },
  methods: {
    init() {
      LocationService.getLocation((latitude, longitude) => {
        ReviewService.getReviewListByLocation({
          latitude: latitude,
          longitude: longitude,
          distance: this.distance
        }).then(reviewList => {
          this.reviewList = reviewList.data;
        });
      });
    },
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
        this.detailModalData.like = response.data;
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
.md-button-content {
  margin-bottom: auto;
  display: flex;
  align-items: center;
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
}
</style>