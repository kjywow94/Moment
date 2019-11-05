<template>
  <v-popover offset="8" placement="left" class="my-card-title">
    <div style="display : inline-block">
      <div>
        <img :src="info.userImgData" alt="Avatar" class="Avatar_image" />
      </div>
    </div>
    <div style="display : inline-block; position:absolute; left:80px">
      <div style="align-items: center;">
        <!--장소-->
        {{info.location}}
      </div>
      <div>
        <!--작성자-->
        by {{info.nickname}}
      </div>
    </div>

    <!-- Pop Up -->
    <template slot="popover">
      <img :src="userImg['imgData']" alt="Avatar" class="Avatar_image" />
      <router-link :to="{ name: 'userProfile', params: { userEmail: user.email, id : user.id }}">
        <h3 class="popover-header" style="text-align:center; cursor:pointer;">{{user.nickname}}</h3>
      </router-link>
      <hr style="width:90%" />
      <md-button
        :href="user.sns1"
        class="md-just-icon md-simple md-twitter"
        target="_blank"
        v-if="user.sns1 !== ''"
      >
        <i class="fab fa-facebook" style="color:#4267b2 !important"></i>
      </md-button>
      <md-button
        :href="user.sns2"
        class="md-just-icon md-simple md-pinterest"
        target="_blank"
        v-if="user.sns2 !== ''"
      >
        <i class="fab fa-instagram" style="color:#a91ba6 !important"></i>
      </md-button>
      <div class="popover-body" style="text-align:center">
        {{user.about}}
        <hr />
        <div style="text-align:right">
          <a :href="'#/PrivateChat/' + user.id">
            <md-button class="md-rose">
              <i class="material-icons">mail</i>메세지 보내기
            </md-button>
          </a>
        </div>
      </div>
    </template>
  </v-popover>
</template>
<script>
import UserService from "@/services/UserService.js";
import UserProfileService from "@/services/UserProfileService.js";
export default {
  components: {
    UserService,
    UserProfileService
  },
  props: {
    info: {}
  },
  data() {
    return {
      user: {},
      userImg: {}
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      let uid = this.info["uid"];
      this.user = await UserService.getUserById(uid);
      this.user = this.user["data"];
      this.setData();

      this.userImg = await UserProfileService.getUserImage(this.user["email"]);
      this.userImg = this.userImg["data"][0];
    },
    setData() {
      let birth = new Date(this.user.birthday);
      this.user.birthday =
        1900 +
        birth.getYear() +
        "년 " +
        (birth.getMonth() + 1) +
        "월 " +
        birth.getDay() +
        "일";
      this.user.gender = this.user.gender == "M" ? "남" : "여";
    }
  }
};
</script>
<style>
.md-button-content {
  margin-bottom: auto;
  display: flex;
  align-items: center;
}
.my-hashtag-div {
  margin-top: 10px;
}
.my-hashtag {
  float: left;
}
.my-card-content {
  margin: 0px;
}
.my-card-title {
  padding-top: 15px;
  padding-bottom: 0px;
  padding-left: 20px;
  padding-right: 20px;
}

.Avatar_image {
  max-width: 50px;
  max-height: 50px;
  border-radius: 50px;
}
.review-card-title {
  display: inline-block;
  padding: 5px;
  margin: 5px;
}
.modal-img {
  margin-bottom: 5px;
}
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.md-has-textarea + .md-layout {
  margin-top: 15px;
}

.popover-inner {
  padding-top: 15px;
  background-color: white;
  max-width: 500px;
  width: 500px;
}
.md-tooltip.popover .popover.popover {
  max-width: 500px !important;
  width: 500px !important;
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
  .my-card-container {
    margin: auto;
    height: 380px;
  }
  .popover-inner {
    padding-top: 15px;
    background-color: white;
    max-width: 300px;
    width: 300px;
  }
  .md-tooltip.popover .popover.popover {
    max-width: 300px !important;
    width: 300px !important;
  }
}
</style>