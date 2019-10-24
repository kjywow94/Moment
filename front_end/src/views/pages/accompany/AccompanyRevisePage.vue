<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1 class="title">동행 수정 페이지</h1>
            <h4>현재 동행정보를 수정하는 페이지 입니다.</h4>
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
                <md-input v-model="title"></md-input>
              </md-field>
              <div class="title" @click="dateInitialize">
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
              <div class="title">
                <h3>최대참가인원</h3>
              </div>
              <md-field>
                <md-input v-model="max"></md-input>
              </md-field>
              <div class="title">
                <h3>테마</h3>
              </div>
              <div class="flex-column">
                <md-checkbox value="Picture" v-model="thema">Picture</md-checkbox>
                <md-checkbox value="Food" v-model="thema">Food</md-checkbox>
                <md-checkbox value="Walking" v-model="thema">Walking</md-checkbox>
              </div>
              <div class="title">
                <h3>여행지역</h3>
              </div>
              <md-field>
                <md-input v-model="region" placeholder="여행지역"></md-input>
              </md-field>
              <div class="title">
                <h3>여행도시</h3>
              </div>
              <md-field>
                <md-input v-model="city" placeholder="여행도시"></md-input>
              </md-field>
              <div class="title inline_area">
                <h3>해쉬태그</h3>
                <md-button class="md-primary md-round">+</md-button>
              </div>
              <div v-for="item in hash" v-bind:key="item.id" class="inline_area">
                <md-button class="md-primary md-round" @click="del(item.id)">#{{item.tag}}</md-button>
              </div>
              <div class="title">
                <h3>여행내용</h3>
              </div>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>edit</md-icon>
                <md-textarea v-model="content" md-autogrow placeholder="여행내용"></md-textarea>
              </md-field>
              <div id = "inputButton">
                <div class="md-layout-item md-size-70 mx-auto">
                  <md-button class="md-primary"
                    ><md-icon>favorite</md-icon>동행수정
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
export default {
  components: {
    Tabs
  },
  bodyClass: "profile-page",
  data() {
    return {
      //dummy data
      id : this.$route.params.id,
      title : "this is dummy title",
      startDate : new Date("2019/10/11"),
      endDate : new Date("2019/10/18"),
      max : 10,
      thema: ["Picture","Food"],
      region : "America",
      city : "paris",
      hash : [
        {tag : "꾸르잼"},
        {tag : "대존잼"},
        {tag : "핵잼"},
      ],
      content : "이번 여행은 사진도찍고 영화도보고 연탄구이도 할겁니다. 정말 재미있는 여행이 될거에요. 함께해요 사람들아"
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
    dateInitialize(){
        var today = new Date();
        var year = today.getUTCFullYear();
        var month = today.getUTCMonth()+1;
        var date = today.getUTCDate();

        var target = year+"/"+month+"/"+date;

        this.startDate = new Date(target); 
        this.endDate = new Date(target);
    },
    del(id){
      if(this.hash.length == 1){
        alert("해쉬태그는 최소 1개입니다.");
        return;
      }
      console.log(id);
      this.hash.splice(id-1,1);
      console.log(this.hash);        
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
.inline_area{
  display: inline-block;
}
</style>