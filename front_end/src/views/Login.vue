<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
          <form @submit.prevent="onSubmit">
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
                <md-input v-model="password" @keyup.enter="login"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-success md-lg" :class="{'btn-success' : !invalidForm}" type="submit"
                :disabled="invalidForm">
                로그인
              </md-button>
              <md-button slot="footer" class="md-simple md-success md-lg">
                회원가입
              </md-button>
            </login-card>
          </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import UserService, { auth } from '../services/UserService.js'

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: "",
      password: ""
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    onSubmit() {
      console.log(this.email, this.password);
      UserService.login(this.email, this.password)
        .then(data => {
          console.log(data);
          alert("로그인");
        })
        .catch(err => {
          console.log(err);
          alert("에러");
        })
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
  }
};
</script>

<style lang="css"></style>
