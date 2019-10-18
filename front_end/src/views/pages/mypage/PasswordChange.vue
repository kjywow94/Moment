<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">비밀번호수정</h1>
        <div class="md-layout"  style="background:white;">
            <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 20px auto;">
                <template>
                  <div class="card md-layout-item md-size-100 md-xsmall-size-100 md-small-size-100 md-medium-size-100" style="width: 28rem;">
                      <md-field>
                          <label>이전 비밀번호</label>
                          <md-input v-model="idlabel" type="text"></md-input>
                      </md-field>
                      <md-field>
                          <label>신규 비밀번호</label>
                          <md-input v-model="idlabel" type="text"></md-input>
                      </md-field>
                      <md-field>
                          <label>신규 비밀번호 번호</label>
                          <md-input v-model="idlabel" type="text"></md-input>
                      </md-field>
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

export default {
  components: {
    LoginCard,
    Tabs
  },
  bodyClass: "login-page",
  data() {
    return {

    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
  
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
