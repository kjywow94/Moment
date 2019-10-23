<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div>
            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto">
              <div class="title">
                <img src="@/assets/img/default.jpg" class="img" >
                <h3>해쉬태그</h3>
              </div>
              <div class="tim-typo">
                <h2 class="title" v-for="hash in hashtag" :key="hash.none">#{{hash}}</h2>
              </div>
              <div class="title">
                <h3>여행내용</h3>
              </div>
              <div class="tim-typo">
                <div class="blockquote undefined">
                  <p>
                    {{review['review']}}
                  </p>
                </div>
              </div>
              <!-- <div id = "inputButton">
                <div class="md-layout-item md-size-70 mx-auto">
                  <router-link
                    :to="{name:'accompanyRevise', params: { id: id }}"
                    class="my-table-content"
                  ><md-button class="md-primary"
                      ><md-icon>favorite</md-icon>동행수정
                    </md-button></router-link>
                </div> 
              </div> -->
            </div>
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
      review:[{
        id:null,
        tid:null,
        title:null,
        hashtag:null,
        review:null,
        is_remove:null
      }],
      hashtag:[{}]

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
      let temp  = await TravelReviewService.getReview(this.$route.params.id);
      this.review = temp['data'];
      this.hashtag = this.review['hashtag'].split(',');
    }
  }
};
</script>
