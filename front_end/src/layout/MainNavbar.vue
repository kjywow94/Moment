<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <router-link :to="{name:'travelReview'}" class="hidden-sm" v-if="isLogined">
        <div class="md-toolbar-section-start">
          <h3 class="titleFont" style="color:white; font-size : 2.5em">Moment</h3>
        </div>
      </router-link>

      <router-link :to="{name:'login'}" class="hidden-sm" v-if="!isLogined">
        <div class="md-toolbar-section-start">
          <h3 class="titleFont" style="color:white; font-size : 2.5em">Moment</h3>
        </div>
      </router-link>
      <div class="hidden-sm" style="width:30%;"></div>
      <div class="my-header-sm my-header-md">
        <div>
          <md-list>
            <md-list-item href="#/travelReview" class="hidden-md" v-if="isLogined">
              <i class="material-icons" style="color:white;">rate_review</i>
            </md-list-item>

            <md-list-item href="#/travelReview" v-if="isLogined" class="hidden-sm">
              <i class="material-icons" style="color:white;">rate_review</i>
              <p class="hidden-sm" style="color:white">트레이스</p>
            </md-list-item>

            <md-list-item href="#" v-if="isLogined" @click="sendWriteSign">
              <i class="material-icons" style="color:white;">create</i>
              <p class="hidden-sm" style="color:white">글쓰기</p>
            </md-list-item>

            <md-list-item :href="chatUrl" v-if="isLogined">
              <i class="material-icons" style="color:white;">chat</i>
              <p class="hidden-sm" style="color:white">채팅</p>
              <md-badge  :md-content="noti" type="danger"></md-badge>
            </md-list-item>

            <md-list-item href="#/map/kakaomap" v-if="isLogined">
              <i class="material-icons" style="color:white">gps_fixed</i>
              <p class="hidden-sm" style="color:white">지도</p>
            </md-list-item>

            <md-list-item href="#/login" v-if="!isLogined">
              <i class="material-icons" style="color:white;">account_circle</i>
              <p class="hidden-sm" style="color:white">로그인</p>
            </md-list-item>

            <md-list-item href="#/signUp" v-if="!isLogined">
              <i class="material-icons" style="color:white;">how_to_reg</i>
              <p class="hidden-sm" style="color:white">회원가입</p>
            </md-list-item>

            <md-list-item href="#/find/idfind" v-if="!isLogined">
              <i class="material-icons" style="color:white;">search</i>
              <p class="hidden-sm" style="color:white">아이디 찾기</p>
            </md-list-item>

            <md-list-item href="#/find/passwordfind" v-if="!isLogined" >
              <i class="material-icons" style="color:white;">vpn_key</i>
              <p class="hidden-sm" style="color:white">비밀번호 찾기</p>
            </md-list-item>

            <md-list-item href="#/profile" v-if="isLogined" class="hidden-sm">
              <i class="material-icons" style="color:white;">person_pin</i>
              <p style="color:white">{{$store.state.user.nickname}}님</p>
            </md-list-item>

            <md-list-item href="#/mypage/myuserinfo" v-if="isLogined" class="hidden-sm">
              <i class="material-icons" style="color:white;">build</i>
              <p style="color:white">내정보</p>
            </md-list-item>

            <!-- 모달 내정보 -->
            <md-list-item v-if="isLogined" class="hidden-md" @click="infoModalOn">
              <i class="material-icons" style="color:white;">person_pin</i>
            </md-list-item>

            <md-list-item
              href="#/admin"
              v-if="$store.state.user.authority == 'A'"
              class="hidden-sm"
            >
              <i class="material-icons" style="color:white;">settings_applications</i>
              <p class="hidden-sm" style="color:white;">관리자</p>
            </md-list-item>

            <md-list-item href="#/" v-on:click="isLogout" v-if="isLogined" class="hidden-sm">
              <i class="material-icons" style="color:white;">how_to_reg</i>
              <p class="hidden-sm" style="color:white;">로그아웃</p>
            </md-list-item>

            <!-- <md-list-item href="https://lab.ssafy.com/s1-final/" target="_blank">
                <i class="fab fa-github" style="color:white;"></i>
                <md-tooltip md-direction="bottom">Github</md-tooltip>
            </md-list-item>-->
          </md-list>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

import MobileMenu from "@/layout/MobileMenu";
import LocationService from "@/services/LocationService.js";
import ChatListService from "@/services/ChatListService.js";

export default {
  components: {
    MobileMenu
  },
  props: {
    type: {
      type: String,
      default: "rose",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info",
          "rose"
        ].includes(value);
      }
    },
    colorOnScroll: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      chatUrl: null,
      extraNavClasses: "",
      toggledClass: false,
      noti: 0,
      chatNotiInterval: null
    };
  },
  methods: {
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    },
    isLogout() {
      this.$store.commit("logout");
      clearInterval(this.chatNotiInterval);
      sessionStorage.clear();
      localStorage.clear();
      alert("정상적으로 로그아웃 되었습니다.");
      location.reload();
    },

    async sendWriteSign() {
      this.$store.state.ReviewWriteOn = true;
      
    },
    infoModalOn() {
      this.$store.state.myInfoModalOn = true;
    }
  },
  mounted() {
    let self = this;
    document.addEventListener("scroll", this.scrollListener);
    // this.$store.state.isLogin = true;
    
    // setInterval(() => {
    //   ChatListService.selectAllNotiByUid(self.$store.state.user.id).then(function(data){
    //   self.noti = data.data;
    // })
    // }, 1000);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  },
  computed: {
    isLogined() {
      let self = this;
      if (this.$store.state.isLogin == true) {
      this.chatNotiInterval = setInterval(() => {
        ChatListService.selectAllNotiByUid(this.$store.state.user.id).then(
        function(data) {
          self.noti = data.data;
        }
      );
      }, 1000);
      this.chatUrl = "#/privateChat/" + this.$store.state.user.id;
    }
      return this.$store.state.isLogin;
    }
  }
};
</script>
<style scoped>
@media (min-width: 481px) {
  .my-header-sm {
    text-align: right;
    /* padding-right: 0px; */
  }
}
@media (max-width: 480px) {
  .my-header-sm {
    margin: auto;
  }
}
</style>