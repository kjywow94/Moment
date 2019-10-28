<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div>
              <md-dialog-prompt
                :md-active.sync="active"
                v-model="value"
                md-title="What's your name?"
                md-input-maxlength="30"
                md-input-placeholder="Type your name..."
                md-confirm-text="Done"
              />

              <md-button class="md-primary md-raised" @click="active = true">Prompt</md-button>
              <span v-if="value">Value: {{ value }}</span>
            </div>
            <ReviewCard />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TravelReviewService from "@/services/TravelReviewService.js";
import AccompanyService from "@/services/AccompanyService.js";
import ReviewCard from "@/views/components/review/ReviewCard";
import { Tabs } from "@/components";
import { Modal } from "@/components";

export default {
  components: {
    TravelReviewService,
    AccompanyService,
    Tabs,
    ReviewCard
  },
  bodyClass: "landing-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg7.jpg")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/faces/kendall.jpg")
    }
  },
  data() {
    return {
      review: [{}],
      active: false,
      value: null,
      isDetail: false
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      let data = await TravelReviewService.getReviews();
      data = data["data"];
      for (let i = 0; i < data.length; i++) {
        let temp = await AccompanyService.getAccompanyRegistById(
          data[i]["tid"]
        );
        data[i]["tname"] = temp["data"]["title"];
      }
      this.review = data;
    }
  }
};
</script>

<style lang="scss" scoped>
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
