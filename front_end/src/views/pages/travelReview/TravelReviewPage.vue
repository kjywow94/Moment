<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-100 mx-auto review_button" >

            <md-button class="md-info md-just-icon md-round" @click="classicModal = true"><md-icon >create</md-icon></md-button>

            <modal v-if="classicModal" @close="classicModalHide">
              <template slot="header">
                <h4 class="modal-title">리뷰쓰기</h4>
                <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                  <md-icon>clear</md-icon>
                </md-button>
              </template>
              <template slot="body">
                <input-tag v-model="hash" placeholder = "태그"></input-tag>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>edit</md-icon>
                  <md-textarea v-model="content" md-autogrow placeholder="내용"></md-textarea>
                </md-field>
                <md-field class="md-form-group" slot="inputs">
                  <md-file v-model="img" placeholder = "사진" multiple />
                </md-field>
              </template>
              <template slot="footer">
                <md-button class="md-info md-simple" @click="classicModalHide">등록</md-button>
                <md-button class="md-danger md-simple" @click="classicModalHide">취소</md-button>
              </template>
            </modal>
            </div>

            <div>
              <div class = "md-layout">
                <div class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-100" v-for="r in review" :key='r.id' >
                  <router-link :to="{name:'travelReviewDetail', params: { id: r.id }}">
                    <div class="md-card md-card-blog md-theme-default text-left list-inline"  >
                      <span class="overlay">
                        <div style="position: absolute;">
                          <div class="alert alert-info">
                            <div class="container">
                              <b>{{r['tname']}} </b>
                            </div>
                          </div>
                        </div>
                        <img src="@/assets/img/default.jpg" class="img" />
                      </span>
                      <div class="md-card-content">
                        <h6 class="card-category text-rose">
                          {{r['title']}}
                        </h6>
                      </div>
                    </div>
                  </router-link>
                </div>
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
import {Modal} from '@/components';
import InputTag from 'vue-input-tag';

export default {
  components: {
    TravelReviewService,
    AccompanyService,
    Tabs,
    Modal,
    InputTag 
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
      review:[{}],
      showDialog: false,
      hash : [],
      classicModal : false,
      content : null,
      img: null
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
      let data  = await TravelReviewService.getReviews();
      data = data['data'];
      for(let i = 0 ; i < data.length ; i++){
        
        let temp = await AccompanyService.getAccompanyRegistById(data[i]['tid']);
        data[i]['tname'] = temp['data']['title'];
      }
      this.review = data;
    },
    classicModalHide(){
      this.classicModal = false;
      this.hash = [];
      this.content= null;
      this.img = null;
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

.review_button{
  text-align: center;
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
