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
          <h3 class="title">{{userProfile.nickname}}</h3>
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
  </div>
  <div class="profile-tabs">
    <tabs
      :tab-name="['my writing']"
      :tab-icon="['message']"
      plain
      nav-pills-icons
      color-button="info"
    >
      <template slot="tab-pane-1">
        <div class="md-layout">

          <!-- test 
           <div v-for="item in userImg">
            <img :src="item.imgData">
           </div>-->
        </div>
      </template>

    </tabs>
  </div>
</div>
</template>

<script>
import { Tabs } from "@/components";
import UserProfileService from '@/services/UserProfileService.js';
import { Modal } from "@/components";
import StarRating from 'vue-star-rating';


export default {
  components: {
    Tabs,
    Modal,
    StarRating
  },
  bodyClass: "profile-page",
  data() {
    return {
      uid: 2,
      email : 'coin@ggg',
      userImg: [],
      userProfile: [],
      // tasteIdx : 7,
      // tasteTitle: ['구경', '사진', '음식', '사람', '계획', '쇼핑' ,'경비'],
      // tasteName:[['번화가 좋아요','자연이 좋아요'],['남는건 사진뿐','눈으로 찍을래']
      //           ,['맛집 좋아요','아무거나 잘먹어요'],['다양한 사람과 많이 만나기','소수의 사람과 친밀하게']
      //           ,['계획적인 여행','즉흥적인 여행'],['쇼핑 좋아요','쇼핑 싫어요']
      //           ,['경비는 여유롭게 사용','경비는 알뜰살뜰하게 사용']],
      // tasteSelect: [],
      userReview: []
      // ,
      // tabPane1: [
      //   { image: require("@/assets/img/examples/studio-1.jpg") },
      //   { image: require("@/assets/img/examples/studio-2.jpg") },
      //   { image: require("@/assets/img/examples/studio-4.jpg") },
      //   { image: require("@/assets/img/examples/studio-5.jpg") }
      // ]
    };
  },
  props: {
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  methods: {
    // getCalculateAge : function(birthday) {
    //   let birth = new Date(birthday);
    //   let today = new Date();
    //   let years = today.getUTCFullYear() - birth.getUTCFullYear();

    //   birth.setFullYear(today.getFullYear());
    //   if(today < birth) years--; 
    //   return years;
    // },
    // getTasteContent: function(index) {
    //   return "taste" + index + "";
    // },
    getDate: function(dates) {
      let date = new Date(dates);
      return date.getUTCFullYear() + "." + (date.getMonth()) + "." + date.getDate();
    },
    ModalAccompanyReviewHide() {
      this.ModalAccompanyReview = false;
      this.review = null;
    },
    setRating: function(rating) {
      this.rating = "You have Selected: " + rating + " stars";
    },
    showCurrentRating: function(rating) {
      this.currentRating = (rating === 0) ? this.currentSelectedRating : "Click to select " + rating + " stars"
    },
    setCurrentSelectedRating: function(rating) {
      this.currentSelectedRating = "You have Selected: " + rating + " stars";
    }
    // ,
    // uploadImg(){
    //   console.log("refs : ", this.$refs);
    //   this.$refs.inputRef.click();
    // }
  },
  mounted() {
    //사용자의 정보를 가져온다.
    UserProfileService.getUserProfile(this.uid)
      .then(userProfile => {
        // userProfile.data.gender = (userProfile.data.gender == 'M' ? '남자' : '여자');
        // userProfile.data.age = this.getCalculateAge(userProfile.data.birthday);
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
    // UserProfileService.getUserReview(this.uid)
    //   .then(userReview => {
    //     this.userReview = userReview.data;
    //     for(let i = 0; i < this.userReview.length; i++){
    //       this.userReview[i].endDate = this.getDate(this.userReview[i].endDate);
    //     }
    //   })
    //   .catch(err => {
    //     console.log("userReview error : ", err);
    //   });

    //사용자의 사진을 가져온다.
    // UserProfileService.getUserImage(this.email)
    //   .then(userImg => {
    //     this.userImg = userImg.data;
    //     console.log("userImg : ",userImg);
    // })
    // .catch(err => {
    //   console.log("UserProfileService error : ", err);
    // });
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