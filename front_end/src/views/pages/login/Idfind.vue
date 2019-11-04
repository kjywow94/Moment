<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto; background:white;">
            <template>
              <h2 style="color:black; font-weight: bold;">아이디 찾기</h2>
              <p style="color:black; margin-bottom: 55px;">아이디를 잊으셨나요?</p>
            </template>
            <form @submit.prevent="onSubmit()">
                <div class="form-group">
                    <label for="exampleInputEmail1" style="color:black;">사용자 이름</label>
                    <input v-model="userName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="이름을 입력해주세요..">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1" style="color:black;">번호</label>
                    <input v-model="phone" class="form-control" id="exampleInputPassword1" placeholder="010-xxxx-xxxx">
                </div>
                <br>
                <button class="btn btn-primary btn-lg btn-block" :class="{'btn-success' : !invalidForm}" style="margin-bottom: 16px;"  :disabled="invalidForm" @click="classicModal = false">
                  아이디 찾기</button>
                  <br>
                <modal v-if="classicModal" @close="classicModalHide">
                  <template slot="header">
                    <h4 class="modal-title" style="color: black;">아이디를 확인해주세요</h4>
                    <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                      <md-icon>clear</md-icon>
                    </md-button>
                  </template>

                  <template slot="body">
                    <p style="color:black; font-size: 30px; font-weight: bold;">{{this.$store.state.findid}}</p>
                  </template>

                  <template slot="footer">
                    <md-button class="md-simple" @click="clearvuex">홈으로 돌아가기</md-button>
                    <md-button class="md-danger md-simple" @click="classicModalHide">돌아가기</md-button>
                  </template>
                </modal>
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
import { Modal } from '@/components';
import UserService from "@/services/UserService.js";

export default {
  components: {
    LoginCard,
    Tabs,
    Modal
  },
  bodyClass: "login-page",
  data() {
    return {
        userName : "",
        phone : "",
        classicModal: false,
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
      var scope = this;

      UserService.findid(this.userName, this.phone)
        .then(data => {          
          if(data.data !== ""){
            this.$store.commit("findid", data.data);
            this.classicModal = true;
          }
          else {
            this.classicModal = false;
            alert("실패...");
          }
        })
    },
    classicModalHide(){
      this.classicModal = false;
      this.$store.commit("clearfind");
    },
    clearvuex(){
      this.$store.commit("clearfind");
      var scope = this;
      scope.$router.push('/');
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url("https://images.unsplash.com/photo-1542435503-956c469947f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80")`
      };
    },
    invalidForm(){
      return !this.userName || !this.phone
    }
  }
};
</script>

<style lang="css">
    @import url("../../../assets/bootstrap/bootstrap.css");
</style>
