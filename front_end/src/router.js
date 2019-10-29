import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import Signup from "./views/pages/signup.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import AccompanyList from "./views/pages/accompany/AccompanyListPage.vue";
import AccompanyDetail from "./views/pages/accompany/AccompanyDetailPage.vue";
import AccompanyRegist from "./views/pages/accompany/AccompanyRegistPage.vue";
import AccompanyRevise from "./views/pages/accompany/AccompanyRevisePage.vue";
import AccompanyChat from "./views/pages/accompany/AccompanyChatPage.vue";
import PrivateChat from "./views/pages/accompany/PrivateChat.vue";
import PasswordChange from "./views/pages/mypage/PasswordChange.vue";
import Admin from "./views/pages/adminPage/Admin.vue";
import MyAccompany from "./views/pages/myAccompany/MyAccompanyPage.vue";
import TravelReview from "./views/pages/travelReview/TravelReviewPage.vue";
import UserProfile from "./views/pages/profile/UsersProfilePage.vue";
import TravelReviewDetail from "./views/pages/travelReview/TravelReviewDetail.vue";
import Mywallet from "./views/pages/mypage/MyWallet.vue";
import MyReview from "./views/pages/mypage/MyReviewpage.vue";
import UserInfo from "./views/pages/mypage/UserInfo.vue";
import MyPageNav from "./layout/MypageNavbar.vue";
import PasswordFind from "./views/pages/login/Passwordfind.vue";
import IdFind from "./views/pages/login/Idfind.vue";
import Kakaomap from "./views/kakaomap/Kakaomap.vue";

Vue.use(Router);

const requireAuth = (to, from, next) => {
  const isAuth = sessionStorage.getItem('Auth')
  const loginPath = '/login?rPath=${encodeURIComponent(to.path)}'
  isAuth ? next() : next(loginPath)
}

const loginAuth = (to, from, next) => {
  if(sessionStorage.length === 0)
    var isAuth =  true;
  const loginNo = '/'
  isAuth ? next() : next(loginNo)
}

const adminAuth = (to, from, next) => {
  if(sessionStorage.Auth === "U"){
    var aminAuth = true;
    console.log("uuuuuu");
    
  }
  const adminYes = '/admin'
  const adminNo = "/"
  aminAuth ? next() : next(adminYes)  
}

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      beforeEnter : requireAuth,
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyList",
      name: "accompanyList",
      beforeEnter : requireAuth,
      components: { default: AccompanyList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyChat/:id",
      name: "accompanyChat",
      beforeEnter : requireAuth,
      components: { default: AccompanyChat, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/privateChat/:id",
      name: "privateChat",
      beforeEnter : requireAuth,
      components: { default: PrivateChat, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyDetail/:id",
      name: "accompanyDetail",
      beforeEnter : requireAuth,
      components: { default: AccompanyDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyRegist",
      name: "accompanyRegist",
      beforeEnter : requireAuth,
      components: { default: AccompanyRegist, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/accompanyRevise/:id",
      name: "accompanyRevise",
      beforeEnter : requireAuth,
      components: { default: AccompanyRevise, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/myAccompany",
      name: "myAccompany",
      beforeEnter : requireAuth,
      components: { default: MyAccompany, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/landing",
      name: "landing",
      beforeEnter : requireAuth,
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      beforeEnter : loginAuth,
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/signup",
      name: "signup",
      beforeEnter : loginAuth,
      components: { default: Signup, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      beforeEnter : requireAuth,
      components: { default: UserProfile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/admin",
      name: "admin",
      beforeEnter : adminAuth,
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
      path: "/travelReviewDetail/:id",
      name: "travelReviewDetail",
      components: { default: TravelReviewDetail, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/mypage/mywallet",
      name: "mywallet",
      components: { default: Mywallet, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/mypage/passwordchange",
      name: "passwordchange",
      beforeEnter : requireAuth,
      components: { default: PasswordChange, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/mypage/userinfo",
      name: "userinfo",
      beforeEnter : requireAuth,
      components: { default: UserInfo, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/find/passwordfind",
      name: "passwordfind",
      components: { default: PasswordFind, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
<<<<<<< HEAD
    },
    {
      path: "/find/idfind",
      name: "idfind",
      components: { default: IdFind, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/map/kakaomap",
      name: "kakaomap",
      components: { default: Kakaomap, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
=======
>>>>>>> 03fe9cb79a98bc3a42a7c6aecc9b42513b4068bf
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
