import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import Signup from "./views/pages/signup.vue";
import Profile from "./views/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import AccompanyList from "./views/pages/accompany/AccompanyListPage.vue";
import AccompanyDetail from "./views/pages/accompany/AccompanyDetailPage.vue";
import AccompanyRegist from "./views/pages/accompany/AccompanyRegistPage.vue";
import AccompanyRevise from "./views/pages/accompany/AccompanyRevisePage.vue";
import PasswordChange from "./views/pages/mypage/PasswordChange.vue";
import Admin from "./views/pages/adminPage/Admin.vue";
import MyAccompany from "./views/pages/myAccompany/MyAccompanyPage.vue";
import TravelReview from "./views/pages/travelReview/TravelReviewPage.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyList",
      name: "accompanyList",
      components: { default: AccompanyList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyDetail/:id",
      name: "accompanyDetail",
      components: { default: AccompanyDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyRegist",
      name: "accompanyRegist",
      components: { default: AccompanyRegist, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyRevise/:id",
      name: "accompanyRevise",
      components: { default: AccompanyRevise, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/myAccompany",
      name: "myAccompany",
      components: { default: MyAccompany, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/landing",
      name: "landing",
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/signup",
      name: "signup",
      components: { default: Signup, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/mypage/passwordchange",
      name: "passwordchange",
      components: { default: PasswordChange, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/admin",
      name: "admin",
      components: { default: Admin, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/travelReview",
      name: "trabelReview",
      components: { default: TravelReview, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/userprofile",
      name: "userprofile",
      components: { default: UserProfile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    }
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
