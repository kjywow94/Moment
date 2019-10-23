<template>
  <div>
    <div class="md-layout">
    <div class="md-layout-item md-size-50 mx-auto">
      <div class="profile">
        <div class="avatar">
          <img
            :src="img"
            alt="Circle Image"
            class="img-raised rounded-circle img-fluid"
          />
        </div>
        <div class="name">
          <h3 class="title">{{userProfile.userName}}</h3>
          <h6>{{userProfile.gender}}, {{userProfile.age}}살</h6>
          <md-button
            href="javascript:void(0)"
            class="md-just-icon md-simple md-twitter"
            ><i class="fab fa-facebook"></i
          ></md-button>
          <md-button
            href="javascript:void(0)"
            class="md-just-icon md-simple md-pinterest"
            ><i class="fab fa-instagram"></i
          ></md-button>
        </div>
      </div>
    </div>
  </div>
  <div class="description text-center">
    <p>{{userProfile.about}}</p>
    {{userProfile}}
  </div>
  <div class="profile-tabs">
    <tabs
      :tab-name="['사진', '여행스타일', '여행후기']"
      :tab-icon="['camera', 'favorite', 'message']"
      plain
      nav-pills-icons
      color-button="info"
    >
      <template slot="tab-pane-1">
        <div class="md-layout">
          <div class="md-layout-item md-size-25 ml-auto">
            <img :src="tabPane1[0].image" class="rounded" />
            <img :src="tabPane1[1].image" class="rounded" />
          </div>
          <div class="md-layout-item md-size-25 mr-auto">
            <img :src="tabPane1[3].image" class="rounded" />
            <img :src="tabPane1[2].image" class="rounded" />
          </div>
        </div>
      </template>

      <template slot="tab-pane-2">
        <div align="center">
          <div class = "col-10 col-md-6 col-xl-6 font-size">
            <div v-for="n in tasteIdx">
              <div class="alert alert-info" style="background-color: rgba(255, 174, 236, 0.72); color:#076e7f;" >
                <div class="container">
                  <div style="font-weight: bold;">{{tasteTitle[n-1]}}</div>
                  <div>{{tasteName[n-1][tasteSelect[n-1]]}}</div>
                </div>
              </div>
            </div> 
          </div>
        </div>  
      </template>

      <template slot="tab-pane-3">
        <div align="center">
          <div class = "col-10 col-md-6 col-xl-6">
            <div v-for="item in userReview">
              <div class="alert alert-info" style="background-color: #3ddcf080; color:black;" >
                <div class="container" align="left">
                  <div>
                    <b>날짜 : {{item.endDate}}</b>
                    <div class="star-size"> <b>평점 :</b>
                      <span style="color:yellow">
                        <template v-for="n in item.grade">
                          ★ 
                        </template>
                        <template v-for="n in (5-item.grade)">
                          ☆ 
                        </template>
                      </span>
                      <b>({{item.grade}}/5)</b>
                    </div>
                  </div>
                  <hr class="hrColor">
                  <div>{{item.review}}</div>
                </div>
              </div>
            </div> 
          </div>
        </div>  
      </template>
    </tabs>
  </div>
</div>
</template>

<script>
import { Tabs } from "@/components";
import UserProfileService from '@/services/UserProfileService.js';

export default {
  components: {
    Tabs
  },
  bodyClass: "profile-page",
  data() {
    return {
      uid: 2,
      userProfile: [],
      tasteIdx : 7,
      tasteTitle: ['구경', '사진', '음식', '사람', '계획', '쇼핑' ,'경비'],
      tasteName:[['번화가 좋아요','자연이 좋아요'],['남는건 사진뿐','눈으로 찍을래']
                ,['맛집 좋아요','아무거나 잘먹어요'],['다양한 사람과 많이 만나기','소수의 사람과 친밀하게']
                ,['계획적인 여행','즉흥적인 여행'],['쇼핑 좋아요','쇼핑 싫어요']
                ,['경비는 여유롭게 사용','경비는 알뜰살뜰하게 사용']],
      tasteSelect: [],
      userReview: [],
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") }
      ]
    };
  },
  props: {
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  methods: {
    getCalculateAge : function(birthday) {
      let birth = new Date(birthday);
      let today = new Date();
      let years = today.getUTCFullYear() - birth.getUTCFullYear();

      birth.setFullYear(today.getFullYear());
      if(today < birth) years--; 
      return years;
    },
    getTasteContent: function(index) {
      return "taste" + index + "";
    },
    getDate: function(dates) {
      let date = new Date(dates);
      return date.getUTCFullYear() + "." + (date.getMonth()) + "." + date.getDate();
    }
  },
  mounted() {
    //사용자의 정보를 가져온다.
    UserProfileService.getUserProfile(this.uid)
      .then(userProfile => {
        userProfile.data.gender = (userProfile.data.gender == 'M' ? '남자' : '여자');
        userProfile.data.age = this.getCalculateAge(userProfile.data.birthday);
        this.userProfile = userProfile.data;

        //선택한 여행스타일 번호를 배열에 담아준다.
        for(let i = 1; i <= this.tasteIdx; i++){
          this.tasteSelect[i-1] = userProfile.data[this.getTasteContent(i)];
        } 
        //console.log("userProfile : ", this.userProfile);
      })
      .catch(err => {
        console.log("userProfile error : ", err);
      });
    //사용자 후기를 가져온다.
    UserProfileService.getUserReview(this.uid)
      .then(userReview => {
        this.userReview = userReview.data;
        for(let i = 0; i < this.userReview.length; i++){
          this.userReview[i].endDate = this.getDate(this.userReview[i].endDate);
        }
      })
      .catch(err => {
        console.log("userReview error : ", err);
      });
  }
};
</script>
<style lang="scss" scoped>
@import url("../../../assets/bootstrap/bootstrap.css");
  .profile-tabs /deep/ {
    .md-card-tabs .md-list {
      justify-content: center;
    }

    [class*="tab-pane-"] {
      margin-top: 3.213rem;
      padding-bottom: 50px;

      img {
        margin-bottom: 2.142rem;
      }
    }
  }
  .font-size {
    font-size: 1.2em;
  }
  .star-size {
    font-size: 1.0em;
  }
  .hrColor {
    border: thin solid #17a2b8;
  }
</style>