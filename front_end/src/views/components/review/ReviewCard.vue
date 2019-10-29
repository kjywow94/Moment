<template>
  <div>
    <div class="md-layout classic-modal">
      <div
        class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-100"
        v-for="r in reviewList"
        :key="r.id"
        @click="detailModalShow(r)"
      >
        <div class="md-card md-card-blog md-theme-default text-left list-inline">
          <span class="overlay">
            <!-- <div style="position: absolute;">
              <div class="alert alert-info">
                <div class="container">
                  <b>{{r.nickname}}TT</b>
                </div>
              </div>
            </div> -->
            <img :src="r.imageData" class="img" />
          </span>
          <div class="md-card-content">
            <h6 class="card-category text-rose">{{r['title']}}</h6>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <modal v-if="isDetail" @close="detailModalHide">
      <template slot="header">
        <h4 class="modal-title">Modal Title</h4>
        <md-button
          class="md-simple md-just-icon md-round modal-default-button"
          @click="detailModalHide"
        >
          <md-icon>clear</md-icon>
        </md-button>
      </template>

      <template slot="body">{{this.detailModalData.title}}</template>

      <template slot="footer">
        <md-button class="md-simple">Nice Button</md-button>
        <md-button class="md-danger md-simple" @click="detailModalHide">Close</md-button>
      </template>
    </modal>
    <!--Model end-->
  </div>
</template>
<script>
import ReviewService from "@/services/ReviewService.js";
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
      distance: 5
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      LocationService.getLocation((latitude, longitude) => {
        console.log(latitude);
        console.log(longitude);
        ReviewService.getReviewListByLocation({
          latitude: latitude,
          longitude: longitude,
          distance: this.distance
        }).then(reviewList => {
          console.log(reviewList);
          this.reviewList = reviewList.data;
        });
      });
    },
    detailModalShow(selectedData) {
      console.log(selectedData);
      this.detailModalData = selectedData;
      this.isDetail = true;
    },
    detailModalHide() {
      this.isDetail = false;
    }
  }
};
</script>
<style>
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