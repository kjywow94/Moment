<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">내 정보</h1>
            <div class="md-layout"  style="background:white;">
                <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto; min-width: 55%;">
                    <MainNav></MainNav>
                    <tabs
                :tab-name="['회원등록', '프로필등록', '가입하기']"
                :tab-icon="['supervisor_account', 'person_pin', 'check']"
                plain
                nav-pills-icons
                color-button="primary"
            >
                <!-- here you can add your content for tab-content -->
                <template slot="tab-pane-1">
                     <div class="md-layout-item md-size-100 md-xsmall-size-100 md-small-size-100 md-medium-size-100">
                        <md-field>
                            <label>아이디</label>
                            <md-input v-model="idlabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>이름</label>
                            <md-input v-model="namelabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>비밀번호</label>
                            <md-input v-model="passwordlabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>비밀번호 확인</label>
                            <md-input v-model="passwordcheck" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>휴대폰</label>
                            <md-input v-model="phonelabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>이메일</label>
                            <md-input v-model="emaillabel" type="text"></md-input>
                        </md-field>
                        <md-button class="md-primary md-round">이메일 인증</md-button>
                        <md-datepicker v-model="selectedDate">
                            <label>생년월일</label>
                        </md-datepicker>
                        <md-radio v-model="radio" :value="true">남자</md-radio>
                        <md-radio v-model="radio" :value="false">여자</md-radio>
                    </div>
                </template>
             
                <template slot="tab-pane-2">
                    <label>사진등록</label>
                    <br>
                    <div class="ml-2 col-sm-6">
                        <img :src="img" id="preview" class="img-thumbnail">
                    </div>
                    <div class="ml-2 col-sm-6">
                        <div id="msg"></div>
                        <form method="post" id="image-form">
                            <input type="file"  ref="inputRef" name="img[]" class="file" accept="image/*" @change="changeFile($event)" style="display:none">
                            <div class="input-group my-3">
                            <input type="text" class="form-control" disabled placeholder="Upload File" v-model="imgName">
                            <div class="input-group-append">
                                <button type="button" class="browse btn btn-primary" v-on:click="uploadImg()">Browse...</button>
                            </div>
                            </div>
                        </form>
                    </div>                    
                    <md-field>
                        <label>소개글</label>
                        <md-textarea v-model="aboutme"></md-textarea>
                    </md-field> 
                </template>
                <template slot="tab-pane-3">
                    <p>가입을 축하드립니다!!</p>
                    <br>
                    여러분들의 여행에 좋은 인연을 만나기를 기원합니다
                    <br>
                    <md-button type="submit" class="md-primary md-round">회원가입</md-button>
                </template>
            </tabs>
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

export default {
  components: {
    LoginCard,
    Tabs
  },
  bodyClass: "login-page",
  data() {
    return {
      idlabel: "",
      namelabel: "",
      passwordlabel: "",
      passwordcheck: "",
      phonelabel: "",
      emaillabel: "",
      imgName: "프로필등록..",
      img: "https://placehold.it/360x360",
      selectedDate: new Date(),
      radio: null,

      aboutme: "",
    };
  },
  components: {
    MainNav
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    stringclear() {
      
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
