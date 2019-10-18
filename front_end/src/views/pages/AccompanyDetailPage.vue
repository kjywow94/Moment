<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1 class="title">동행 자세히보기 페이지</h1>
            <h4>현재 모집중인 동행을 자세히 보여주는 페이지 입니다.</h4>
            <br />
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
              <div class="tim-typo">
                <h2 class="title" v-text="title"></h2>
              </div>
              <div class="title">
                <h3>동행기간</h3>
              </div>
              <div class="tim-typo">
                <h2 class="title" v-text="period"></h2>
              </div>    
              <div class="title">
                <h3>최대참가인원</h3>
              </div>
              <div class="tim-typo">
                <h2 class="title" v-text="max"></h2>
              </div> 
              <div class="title">
                <h3>테마</h3>
              </div>
              <div class="tim-typo">
                <h2 class="title" v-text="thema"></h2>
              </div> 
              <div class="title">
                <h3>여행지역</h3>
              </div>
              <div class="tim-typo">
                <h2 class="title" v-text="region"></h2>
              </div> 
              <div class="title">
                <h3>여행도시</h3>
              </div>  
              <div class="tim-typo">
                <h2 class="title" v-text="city"></h2>
              </div>        
              <div class="title">
                <h3>해쉬태그</h3>
              </div>
              <div class="tim-typo" v-for="item in hash" v-bind:key="item.id">
                <h2 class="title">#{{item.tag}}</h2>
              </div>
              <div class="title">
                <h3>여행내용</h3>
              </div>
              <div class="tim-typo">
                <div class="blockquote undefined">
                  <p v-text="content">
                  </p>
                </div>
              </div>
              <div id = "inputButton">
                <div class="md-layout-item md-size-70 mx-auto">
                  <router-link
                    :to="{name:'accompanyRevise', params: { id: id }}"
                    class="my-table-content"
                  ><md-button class="md-primary"
                      ><md-icon>favorite</md-icon>동행수정
                    </md-button></router-link>
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
import AccompanyCalendar from "../components/AccompanyCalendar";
import AccompanyBoard from "../components/AccompanyBoard";

export default {
  components: {
    AccompanyCalendar,
    AccompanyBoard
  },
  bodyClass: "landing-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg7.jpg")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/faces/kendall.jpg")
    },     
  },
  data() {
    return {
      //dummy data
      id : this.$route.params.id,
      title : "this is dummy title", 
      period : "2019/10/11"+" ~ "+"2019/10/18", //추후 startDate, endDate 조회후 생성하는 방식으로 변경
      max : 10,
      thema : ["Picture","Restaurant"],
      region : "America",
      city : "paris",
      hash : [
        {id : 1,
        tag : "꾸르잼"},
        {id : 2,
        tag : "대존잼"},
        {id : 3,
        tag : "핵잼"},
      ],
      content : "이번 여행은 사진도찍고 영화도보고 연탄구이도 할겁니다. 정말 재미있는 여행이 될거에요. 함께해요 사람들아"
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  methods:{
    del(id){
      console.log(id)
    }
  }
};
</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}
</style>
