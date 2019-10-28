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

            <!--수정한 부분 붙여넣을것-->
            <div>
              <div class = "md-layout">
                <div class="md-layout-item md-large-size-33 md-medium-size-50 md-small-size-100" v-for="r in review" :key='r.id' >
                         
                    <div class="md-card md-card-blog md-theme-default text-left list-inline md-with-hover"  >
                      <!-- <span class="overlay">
                        <div style="position: absolute;">
                          <div class="alert alert-info">
                            <div class="container">
                              <b>{{r['tname']}} </b>
                            </div>
                          </div>
                        </div>      
                      </span> -->
                      <div class="md-card-header" style="background-color: rgba(255, 255, 255, 0.7)">
                        <div class="md-avatar">
                          <img src="/assets/examples/avatar.png" alt="Avatar"/><!--작성자이미지-->
                        </div>

                        <div class="md-title">{{r['title']}}<!--작성자--></div>
                        <div class="md-subhead">{{r['tname']}}<!--장소--></div>
                      </div>
                      <div class="md-card-content">
                        <img src="@/assets/img/default.jpg" class="img" />
                        <!-- <h6 class="card-category text-rose">
                          {{r['title']}}
                        </h6> -->
                        <hr/>
                        <h3 style="text-align:center">
                          Lorem ipsum dolor
                          <!--title-->
                        </h3>
                      </div>
                      
                    </div>
                </div>
              </div>
            </div>
            <!--수정한 부분 붙여넣을것-->

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
  // //사용자의 정보를 가져온다.
  //   UserProfileService.getUserProfile(this.uid)
  //     .then(userProfile => {
  //       // userProfile.data.gender = (userProfile.data.gender == 'M' ? '남자' : '여자');
  //       // userProfile.data.age = this.getCalculateAge(userProfile.data.birthday);
  //       this.userProfile = userProfile.data;

  //       //선택한 여행스타일 번호를 배열에 담아준다.
  //       for(let i = 1; i <= this.tasteIdx; i++){
  //         this.tasteSelect[i-1] = userProfile.data[this.getTasteContent(i)];
  //       } 
  //       //console.log("userProfile : ", this.userProfile);
  //     })
  //     .catch(err => {
  //       console.log("userProfile error : ", err);
  //     });

  //   //사용자 후기를 가져온다.
  //   // UserProfileService.getUserReview(this.uid)
  //   //   .then(userReview => {
  //   //     this.userReview = userReview.data;
  //   //     for(let i = 0; i < this.userReview.length; i++){
  //   //       this.userReview[i].endDate = this.getDate(this.userReview[i].endDate);
  //   //     }
  //   //   })
  //   //   .catch(err => {
  //   //     console.log("userReview error : ", err);
  //   //   });

  //   //사용자의 사진을 가져온다.
  //   // UserProfileService.getUserImage(this.email)
  //   //   .then(userImg => {
  //   //     this.userImg = userImg.data;
  //   //     console.log("userImg : ",userImg);
  //   // })
  //   // .catch(err => {
  //   //   console.log("UserProfileService error : ", err);
  //   // });
  // }
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
