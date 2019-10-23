<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <router-link :to="{name:'index'}">
        <div class="md-toolbar-section-start">
          <h3 class="md-title">Travel Maker</h3>
        </div>
      </router-link>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>
         {{$store.state.user.authority}}
        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="false">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>
              <md-list-item href="#/admin" v-if="$store.state.user.authority === 'U'">
                <i class="material-icons">settings_applications</i>
                <p>관리자</p>
              </md-list-item>

              <md-list-item href="#/accompanyList" v-if="isLogined">
                <i class="material-icons">flight</i>
                <p>동행 목록</p>
              </md-list-item>

              <md-list-item href="#/myAccompany" v-if="isLogined">
                <i class="material-icons">star_border</i>
                <p>내 동행</p>
              </md-list-item>

              <md-list-item href="javascript:void(0)" v-if="isLogined">
                <i class="material-icons">rate_review</i>
                <p>여행 리뷰</p>
              </md-list-item>

              <md-list-item href="#/login" v-if="!isLogined">
                <i class="material-icons">account_circle</i>
                <p>로그인</p>
              </md-list-item>

               <md-list-item href="#/signup" v-if="!isLogined">
                <i class="material-icons">how_to_reg</i>
                <p>회원가입</p>
              </md-list-item>

              <md-list-item href="#/" v-on:click="isLogout" v-if="isLogined">
                <i class="material-icons">how_to_reg</i>
                <p>로그아웃</p>
              </md-list-item>

              <md-list-item href="https://lab.ssafy.com/s1-final/" target="_blank">
                <i class="fab fa-github"></i>
                <p class="hidden-lg">Git</p>
                <md-tooltip md-direction="bottom">Gitgub</md-tooltip>
              </md-list-item>
            </md-list>
          </div>
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
export default {
  components: {
    MobileMenu
  },
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info"
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
      extraNavClasses: "",
      toggledClass: false
    };
  },
  computed: {},
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
    isLogout(){
      console.log(this.$store.state.user);
      this.$store.commit("logout")
      this.$store.state.isLogin = false;
      this.$store.state.user = "";
      console.log(this.$store.state.isLogin);
      
      sessionStorage.clear();
      console.log(sessionStorage)
      alert("정상적으로 로그아웃 되었습니다.");
      this.$router.push("/login");
      location.reload();
    }
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
    // this.$store.state.isLogin = true;
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  },
  computed : {
    isLogined(){
      console.log("here is my code : " + this.$store.state.isLogin)
      return this.$store.state.isLogin;
    },
  }
};
</script>
