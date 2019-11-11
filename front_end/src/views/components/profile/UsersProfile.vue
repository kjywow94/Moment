<template>
  <div>
    <div class="md-layout">
    <div class="md-layout-item md-size-50 mx-auto">
      <div class="profile">
        <div class="avatar">
          <img
            :src="userImg"
            alt="Circle Image"
            class="img-raised rounded-circle img-fluid"
          />
        </div>
        <div class="name">
          <h3><b>{{userProfile.nickname}}</b></h3>
          <md-button
            :href="facebook"
            class="md-just-icon md-simple md-twitter" target="_blank"
            v-if="facebook !== ''"
            ><i class="fab fa-facebook" style="color:#4267b2 !important"></i
          ></md-button>
          <md-button
            :href="instagram"
            class="md-just-icon md-simple md-pinterest" target="_blank"
            v-if="instagram !== ''"
            ><i class="fab fa-instagram" style="color:#a91ba6 !important"></i
          ></md-button>
        </div>
      </div>
    </div>
  </div>
  <div class="description text-center">
    <p style="font-size : 1.2em">{{userProfile.about}}</p>
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
          <ReviewCard />
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
import ReviewCard from "@/views/components/profile/UserReviewCard";


export default {
  components: {
    Tabs,
    Modal,
    StarRating,
    ReviewCard
  },
  bodyClass: "profile-page",
  data() {
    return {
      uid: 2,
      email : 'coin@ggg',
      userImg: "",
      userProfile: [],
      userReview: [],
      facebook: this.$store.state.user.sns1,
      instagram: this.$store.state.user.sns2
    };
  },
  props: {
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  methods: {
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
  },
  mounted() {
    //사용자의 정보를 가져온다.
    UserProfileService.getUserProfile(this.$store.state.user.id)
      .then(userProfile => {
        this.userProfile = userProfile.data;
      })
      .catch(err => {
        console.log("userProfile error : ", err);
      });

    //사용자의 사진을 가져온다.
    UserProfileService.getUserImage(this.$store.state.user.email)
      .then(userImg => {
        this.userImg = userImg.data[0].imgData;
        //console.log("userImg : ",this.userImg);
    })
    .catch(err => {
      console.log("UserProfileService error : ", err);
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

  }
};
</script>
<style lang="scss" scoped>
@import url("../../../assets/bootstrap/bootstrap.css");
  .profile-tabs /deep/ {
    .md-card-tabs .md-list {
      justify-content: center;
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
