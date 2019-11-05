<template>
  <div>
    <div>
      <div class="md-layout" style="margin:auto;">
        <div
          class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-95 my-card-container myMargin"
          v-for="r in reviewList"
          :key="r.id"
        >
          <div class="md-card md-theme-default text-left list-inline md-with-hover">
            <ProfileCard :info="r" :uid="r.uid"></ProfileCard>
            <hr />
            <div
              class="md-card-content my-card-body"
              style="padding-top:0px; padding-bottom:10px; padding-right:30px; padding-left:30px;"
              @click="detailModalShow(r)"
            >
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
  </div>
</template>


<script>
import { EventBus } from "@/main.js";
import ReviewService from "@/services/ReviewService.js";
import UserService from "@/services/UserService.js";
import UserProfileService from "@/services/UserProfileService.js";
import EthereumService from "@/services/EthereumService.js";
import LocationService from "@/services/LocationService.js";
import ProfileCard from "@/views/components/profile/ProfileCard";
import { Tabs } from "@/components";

export default {
  components: {
    Tabs,
    ProfileCard
  },
  data() {
    return {
      busy: false,
      reviewList: [],
      idx: 0,
      distance: 10,
      latitude: 0,
      longitude: 0,
      loadingPageNumber: 9999,
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
      UserProfileService.getReviewByEmail(this.$route.params.userEmail).then(
        reviewList => {
          this.reviewList = reviewList.data;
        }
      );
    },
    detailModalShow(selectedData) {
      this.$store.state.detailModalData = selectedData;
      this.$store.state.isDetailOn = true;
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
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 0px;
  margin-bottom: 0px;
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
  .myMargin {
    margin-bottom: 20px;
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
