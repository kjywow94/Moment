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
      color-button="success"
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
        <div class="md-layout">
          <div class="md-layout-item md-size-25 ml-auto">
            <img :src="tabPane2[0].image" class="rounded" />
            <img :src="tabPane2[1].image" class="rounded" />
            <img :src="tabPane2[2].image" class="rounded" />
          </div>
          <div class="md-layout-item md-size-25 mr-auto">
            <img :src="tabPane2[3].image" class="rounded" />
            <img :src="tabPane2[4].image" class="rounded" />
          </div>
        </div>
      </template>
      <template slot="tab-pane-3">
        <div class="md-layout">
          <div class="md-layout-item md-size-25 ml-auto">
            <img :src="tabPane3[0].image" class="rounded" />
            <img :src="tabPane3[1].image" class="rounded" />
          </div>
          <div class="md-layout-item md-size-25 mr-auto">
            <img :src="tabPane3[2].image" class="rounded" />
            <img :src="tabPane3[3].image" class="rounded" />
            <img :src="tabPane3[4].image" class="rounded" />
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
      userProfile: [],
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") }
      ],
      tabPane2: [
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/cynthia-del-rio.jpg") },
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/clem-onojegaw.jpg") }
      ],
      tabPane3: [
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/studio-3.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/studio-1.jpg") }
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
    }
  },
  mounted() {
    UserProfileService.getUserProfile(2)
      .then(userProfile => {
        userProfile.data.gender = (userProfile.data.gender == 'M' ? '남자' : '여자');
        userProfile.data.age = this.getCalculateAge(userProfile.data.birthday);
        this.userProfile = userProfile.data;
        console.log("userProfile : ", this.userProfile);
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>
<style lang="scss" scoped>
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
</style>