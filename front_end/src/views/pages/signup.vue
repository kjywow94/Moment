<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">회원가입</h1>
        <div class="md-layout"  style="background:white;">
            <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto;">
            <tabs
                :tab-name="['회원등록', '프로필등록', '가입하기']"
                :tab-icon="['supervisor_account', 'person_pin', 'check']"
                plain
                nav-pills-icons
                color-button="primary"
            >
                <!-- here you can add your content for tab-content -->
                <template slot="tab-pane-1">
                     <div class="md-layout-item md-size-100 md-xsmall-size-100 md-small-size-50 md-medium-size-25">
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
                            <label>Select date</label>
                        </md-datepicker>
                        <md-radio v-model="radio" :value="true">남자</md-radio>
                        <md-radio v-model="radio" :value="false">여자</md-radio>
                    </div>
                </template>
             
                <template slot="tab-pane-2">
                    <label>사진등록</label>
                    <br>
                    <input type="hidden" name="MAX_FILE_SIZE" value="4194304" />
                    <input type="file" />
                    <md-field>
                        <label>소개글</label>
                        <md-textarea v-model="aboutme"></md-textarea>
                    </md-field> 
                    <br>
                    <div class="btn-group btn-group-toggle widthbtn" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> 내성적이다
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" id="option2" autocomplete="off"> 외향적이다
                        </label>
                    </div>
                    <br>
                    <div class="btn-group btn-group-toggle widthbtn" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> 시끌버쩍한 곳
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" id="option2" autocomplete="off"> 한적한 곳
                        </label>
                    </div>
                    <br>
                    <div class="btn-group btn-group-toggle widthbtn" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> 사진찍기
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" id="option2" autocomplete="off"> 먹방여행
                        </label>
                    </div>
                    <br>
                    <div class="btn-group btn-group-toggle widthbtn" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> 쇼핑하기
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" id="option2" autocomplete="off"> 기념품사기
                        </label>
                    </div>
                    <br>
                    <div class="btn-group btn-group-toggle widthbtn" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> 계획적이다
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" id="option2" autocomplete="off"> 즉흥적이다
                        </label>
                    </div>
                </template>
                <template slot="tab-pane-3">
                Completely synergize resource taxing relationships via premier
                niche markets. Professionally cultivate one-to-one customer
                service with robust ideas.
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
      selectedDate: new Date(),
      radio: null,
      aboutme: "",
      checked1: true,
      checked2: true,
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
    }
  }
};
</script>

<style lang="css">
    @import url("../../assets/bootstrap/bootstrap.css");
</style>
