<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1 class="title">동행 등록 페이지</h1>
            <h4>동행을 등록하는 페이지 입니다.</h4>
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto">
              <div class="title">
                <h3>동행제목</h3>
              </div>
              <md-field>
                <md-input v-model="title" placeholder="갑시다갑시다디지몬세계로"></md-input>
              </md-field>
              <div id = "datePick">
                <div class="title">
                  <h3>동행시작일</h3>
                </div>
                <md-datepicker v-model="startDate">
                  <label>start date</label>
                </md-datepicker>       
                <div class="title">
                  <h3>동행종료일</h3>
                </div>
                <md-datepicker v-model="endDate">
                  <label>end date</label>
                </md-datepicker>
              </div>
              <div id="inputs">
                <div class="title">
                  <h3>최대참가인원</h3>
                </div>
                <md-field>
                  <md-input v-model="max" placeholder="숫자로 입력해주세요"></md-input>
                </md-field>
              </div>
              <div id="thema">
                <div class="title">
                  <h3>테마</h3>
                </div>
                <div class="flex-column">
                  <md-checkbox value="Picture" v-model="thema">Picture</md-checkbox>
                  <md-checkbox value="Food" v-model="thema">Food</md-checkbox>
                  <md-checkbox value="Walking" v-model="thema">Walking</md-checkbox>
                </div>
              </div>
              <div id="region">
                <div class="title">
                  <h3>여행지역</h3>
                </div>
                <md-field>
                  <md-select v-model="region" name="region" id="region" placeholder="여행지역">
                    <md-option value="국내">국내</md-option>
                    <md-option value="아시아">아시아</md-option>
                    <md-option value="호주">호주</md-option>
                    <md-option value="북미">북미</md-option>
                    <md-option value="남미">남미</md-option>
                    <md-option value="유럽">유럽</md-option>
                    <md-option value="아프리카">아프리카</md-option>
                  </md-select>
                </md-field>
              </div>
              <div id="city">
                <div class="title">
                  <h3>여행도시</h3>
                </div>
                <md-field>
                  <md-input v-model="city" placeholder="여행도시"></md-input>
                </md-field>
              </div>
              <div id="hash">
                <div class="title">
                  <h3>해쉬태그</h3>
                </div>
                <input-tag v-model="hash"></input-tag>
              </div>
              <div id="content">
                <div class="title">
                  <h3>여행내용</h3>
                </div>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>edit</md-icon>
                  <md-textarea v-model="content" md-autogrow placeholder="여행내용"></md-textarea>
                </md-field>
              </div>
              <div id = "inputButton">
                <div class="md-layout-item md-size-70 mx-auto">
                  <md-button class="md-primary" @click="accompanyRegist"
                    ><md-icon>favorite</md-icon>동행등록
                  </md-button>
                </div> 
              </div>
            </div>           
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/components";
import AccompanyService from '@/services/AccompanyService.js';
import InputTag from 'vue-input-tag';

export default {
  components: {
    Tabs,
    InputTag
  },
  bodyClass: "profile-page",
  data() {
    return {
      title : null,
      startDate : null,
      endDate : null,
      max : null,
      thema: [],
      region : null,
      city : null,
      hash : [],
      content : null,
      tags : null
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  mounted(){
      this.dateInitialize();
  },
  methods : {
    test(){
      console.log(this.hash);
    },
    dateInitialize(){
      var today = new Date();
      var year = today.getUTCFullYear();
      var month = today.getUTCMonth()+1;
      var date = today.getUTCDate();

      var target = year+"/"+month+"/"+date;

      this.startDate = new Date(target); 
      this.endDate = new Date(target);
    },
    accompanyRegist(){

      var them ="";
      var hasi ="";
      for(var i = 0; i < this.thema.length-1;i++){
        them += this.thema[i]+",";
      }
      them += this.thema[this.thema.length-1];

      for(var i = 0; i < this.hash.length-1;i++){
        hasi += this.hash[i]+",";
      }
      hasi += this.hash[this.hash.length-1];

      var accompany = {
        title : this.title,
        content : this.content,
        process : "모집중",
        startDate : this.startDate,
        endDate : this.endDate,
        city : this.city,
        region : this.region,
        thema : them,
        max : this.max,
        hashtag : hasi,
        user : sessionStorage.getItem("UID")
      }
      AccompanyService.insertAccompanyRegist(accompany);

      this.title=null;
      this.content=null;
      this.startDate = new Date();
      this.endDate = new Date();
      this.city = null;
      this.region = null;
      this.thema = [];
      this.max = null;
      this.hash = [];

      
    }
  }
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}
.profile-tabs /deep/ {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>