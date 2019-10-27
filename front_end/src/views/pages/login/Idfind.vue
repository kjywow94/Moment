<template>
      <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">아이디 찾기
          </h1>
        <div class="md-layout"  style="background:white;">
          <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto;">

            <template>
              <h2 style="color:black; font-weight: bold;">아이디 찾기</h2>
              <p style="color:black; margin-bottom: 55px;">아이디를 잊으셨나요?</p>
            </template>
            <form @submit.prevent="onSubmit()">
                <div class="form-group">
                    <label for="exampleInputEmail1" style="color:black;">사용자 이름</label>
                    <input v-model="user_name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="이름을 입력해주세요..">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1" style="color:black;">번호</label>
                    <input v-model="phone" class="form-control" id="exampleInputPassword1" placeholder="010-xxxx-xxxx">
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block" :class="{'btn-success' : !invalidForm}" style="margin-bottom: 16px;" :disabled="invalidForm">아이디 찾기</button>
            </form>
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
import UserService from "@/services/UserService.js";

export default {
  components: {
    LoginCard,
    Tabs
  },
  bodyClass: "login-page",
  data() {
    return {
        user_name : "",
        phone : ""
    };
  },
  components: {
    
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    onSubmit() {
      var scope = this;
      console.log("되고있나?");
      
      UserService.findid(this.user_name, this.phone)
        .then(data => {
          if(data.data !== ""){
            // this.$store.commit("login", data.data)
            console.log(data.data);
            
            alert("성공");
          }
          else {
            alert("실패...");
          }
        })
    },
    onclick(){
        console.log(11111);
        
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
    invalidForm(){
      return !this.user_name || !this.phone
    }
  }
};
</script>

<style lang="css">
    @import url("../../../assets/bootstrap/bootstrap.css");
</style>
