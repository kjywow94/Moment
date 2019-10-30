<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <!-- <ReviewWrite /> -->
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
import ReviewWrite from "@/views/components/review/ReviewWrite";
import { Tabs } from "@/components";
import { Modal } from "@/components";
import InputTag from "vue-input-tag";

export default {
  components: {
    TravelReviewService,
    AccompanyService,
    Tabs,
    Modal,
    InputTag,
    ReviewCard,
    ReviewWrite
  },
  bodyClass: "landing-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/beach_3.jpg")
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
    return {};
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  methods: {
    classicModalHide() {
      this.classicModal = false;
      this.hash = [];
      this.content = null;
      this.img = null;
    }
  }
};
</script>

<style lang="scss" scoped>
// .md-card-actions.text-center {
//   display: flex;
//   justify-content: center !important;
// }
// .contact-form {
//   margin-top: 30px;
// }

// .md-has-textarea + .md-layout {
//   margin-top: 15px;
// }

// .review_button {
//   text-align: center;
// }

// @media (min-width: 481px) {
//   .mdquery-xs {
//     display: none;
//   }
// }

// /* 모바일*/
// @media (max-width: 480px) {
//   .mdquery-md {
//     display: none;
//   }
// }
</style>
