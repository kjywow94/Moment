<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
          <form @submit.prevent="onSubmit()">
            <login-card header-color="green">
              <h4 slot="title" class="card-title" style="font-size:30px;">로그인</h4>
              <p slot="description" class="description">Or Be Classical</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>아이디를 입력해주세요...</label>
                <md-input v-model="email"></md-input>
              </md-field>
                <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호를 입력해주세요...</label>
                <md-input v-model="password" @keyup.enter="onSubmit()"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-success md-lg" :class="{'btn-success' : !invalidForm}" type="submit"
                :disabled="invalidForm">
                로그인
              </md-button>
              <md-button v-on:clicl="signup()" slot="footer" class="md-simple md-success md-lg">
                <router-link to="/signup">회원가입</router-link>
              </md-button>
           
            </login-card>
          </form>
          <p class="error" v-if="error">{{error}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import UserService from "@/services/UserService.js";


export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: "",
      password: "",
      error: ""
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
    invalidForm(){
      return !this.email || !this.password
    }
  },
  create(){
    this.rPath = this.$router.query.rPath || '/'
  },
  methods: {
    onSubmit() {
      var scope = this;
      UserService.Login(this.email, this.password)
        .then(data => {
          if(data.data !== ""){
            this.$store.commit("login", data.data)
            console.log(data.data);
            
            // this.$store.commit("login", {data.data.email, data.data.password}), 
            if(window.sessionStorage){
              sessionStorage.setItem('Auth', data.data.authority);
              sessionStorage.setItem('ID', data.data.email);
              sessionStorage.setItem('PW', data.data.password);
            }
            
            scope.$router.push('/');
            alert("로그인");
          }
          else {
            alert("다시 확인해주세요...");
          }
        })
    },
    signup(){
      scope.$router.push("/signup");
    }
  },
 
};
</script>

<style lang="css"></style>
