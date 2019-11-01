<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">비밀번호 변경</h1>
        <div class="md-layout"  style="background:white;">
            <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto;     min-width: 55%;">
              <MainNav></MainNav>
              <template>
                <div class="card" style="margin-bottom:16px;">
                <div class="card-header" style="background: linen;">
                  <h4 style="color:black; font-weight: bold;">비밀번호를 변경해주세요</h4>
                </div>
                <div class="card-body">
                    <md-field>
                        <label>이전 비밀번호</label>
                        <md-input v-model="input.yesterpass" type="text"></md-input>
                    </md-field>
                    <md-field>
                        <label>신규 비밀번호</label>
                        <md-input v-model="input.newopass" type="text"></md-input>
                    </md-field>
                    <md-field>
                        <label>신규 비밀번호 번호</label>
                        <md-input v-model="input.chkpass" type="text"></md-input>
                    </md-field>
                  <button type="button" class="btn btn-outline-success" v-on:click="update()">비밀번호 변경</button>
                  <button type="button" class="btn btn-outline-danger" v-on:click="stringclear()" style="float: right;">초기화</button>
                </div>
                </div>
              </template>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { Tabs } from "@/components";
import { log } from 'util';
import MainNav from "../../../layout/MypageNavbar";
import UserService from "@/services/UserService.js";

export default {
  components: {
    LoginCard,
    Tabs,
   
  },
  bodyClass: "login-page",
  data() {
    return {
      user: {
        id: 0,
        email: "",
        nickname: "",
        password: ""
      },
      input: {
        yesterpass : "",
        newopass : "",
        chkpass : ""
      },
    }
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  components: {
     MainNav
  },
  methods: {
    stringclear() {
      this.input.yesterpass = "";
      this.input.newopass = "";
      this.input.chkpass = "";
    },
    update: function () {
      var scope = this;
      if(this.$store.state.userinfo.password !== this.input.yesterpass){
          alert("입력하신 비밀번호가 일치하지 않습니다.");
          return;
      }
      
      // 비밀번호를 공백으로 입력했는지 확인한다.
      if (this.input.newopass === "") {
          alert("신규 비밀번호를 입력해주세요.");
          return;
      }
      // 신규비밀번호와 신규비밀번호 확인이 일치하지 않는 경우를 확인한다.
      if (this.input.newopass !== this.input.chkpass) {
          alert("신규 비밀번호와 신규 비밀번호 확인이 일치하지 않습니다.");
          return;
      }
      
      UserService.update(this.input.newopass, this.$store.state.user)
        .then(data => {          
          if(data.config !== ""){
            var temp = JSON.parse(data.config.data)
            this.$store.commit("update", temp)
            sessionStorage.setItem('PW', temp.password);
            scope.$router.go(-1);
            alert("비밀번호 변경아 되었습니다");            
          }
          else {
            alert("다시 확인해주세요....")
          }
        })
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="css">
    @import url("../../../assets/bootstrap/bootstrap.css");
</style>
