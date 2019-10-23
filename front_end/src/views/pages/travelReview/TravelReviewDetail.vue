<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div>


              {{review}}
<!-- <div class = "md-layout">
<div class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-100" v-for="r in review" :key='r.id' >
  <router-link :to="{name:'travelReviewDetail', params: { id: r.id }}">
  <div class="md-card md-card-blog md-theme-default text-left list-inline"  >
    <span class="overlay"><div style="position: absolute;">

<div class="alert alert-info">
        <div class="container">
          <b>{{r['tname']}} </b>
        </div>
      </div>

      </div>
      <img src="@/assets/img/default.jpg" class="img" >
    </span>
    <div class="md-card-content">
      <h6 class="card-category text-rose">
                {{r['title']}}
              </h6>
            </div>
          </div>
        </router-link>
      </div>
    </div> -->
   </div>
            </div>
          </div>
        </div>
      </div>
      </div>
</template>

<script>
import TravelReviewService from "@/services/TravelReviewService.js";
import AccompanyService from "@/services/AccompanyService.js";
import { Tabs } from "@/components";
export default {
  components: {
    TravelReviewService,
    AccompanyService,
    Tabs
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
      review:[{}]
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  mounted(){
    this.init();
    
  },
  methods : {
    async init(){
      this.review  = await TravelReviewService.getReview(this.$route.params.id);
      console.log(this.review)
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
