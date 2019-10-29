<template>
  <div>
    <div>
      <div class="md-layout">
        <div
          class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-100"
          v-for="r in reviewList"
          :key="r.id"
          @click="detailModalShow(r)"
        >
          <div class="md-card md-card-blog md-theme-default text-left list-inline md-with-hover">
            <div class="md-card-content">
              <div class="review-card-title">
                <div class="md-avatar" style="margin-bottom: 1.5vw">
                  <img :src="r.userImgData" alt="Avatar" />
                </div>
              </div>
              <div style="display : inline-block;" >
                <h4>
                  {{r.nickname}}
                  <!--작성자-->
               </h4>
                <h5>
                  {{r.location}}
                  <!--장소-->
                </h5>
              </div>
              <hr />
            </div>

            <div class="md-card-content">
              <img :src="r.imageData" class="img" />
              <hr />
              <div style="display:inline-block">
                <h4>{{r.title}}</h4>
              </div>
              <div style="display:inline-block; float:right">
                <md-icon>favorite</md-icon>
                <p class="text-rose">{{r.liked}}</p>
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
          console.log(this.reviewList);
        });
      });
    },
    detailModalShow(selectedData) {
      this.detailModalData = selectedData;
      EthereumService.read(selectedData.hash, content => {
        this.detailModalData.content = content;
        UserService.getUserById(this.detailModalData.uid).then(user => {
          this.detailModalData.nickname = user.data.nickname;
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
      });
    },
    detailModalHide() {
      this.isDetail = false;
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
.review-card-title{
    display : inline-block;
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
.contact-form {
  margin-top: 30px;
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
}
</style>