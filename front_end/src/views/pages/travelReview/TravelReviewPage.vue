<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
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