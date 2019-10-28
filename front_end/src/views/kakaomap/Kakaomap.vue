<template>
 <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">지도
          </h1>
        <div class="md-layout"  style="background:white;">
          <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto; margin-top: 20px; margin-bottom: 20px;">
            <template>
                <div id="dmap">
                  <div id="map" style="width:100%; height:500px;"></div>
                </div>
            </template>
            <template>
                <div class="form-group">
                    <label for="exampleInputEmail1" style="color:black;">위도</label>
                    <input v-model="w" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="위도">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1" style="color:black;">경도</label>
                    <input v-model="h" class="form-control" id="exampleInputPassword1" placeholder="경도">
                </div>
                <button @click="onclick">클릭</button>
                {{this.w}}
                {{this.h}}
            </template>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false"></script>
<script type="text/javascript">
    kakao.maps.load(function() {
    // v3가 모두 로드된 후, 이 콜백 함수가 실행됩니다.
    var map = new kakao.maps.Map(node, options);
});
</script>
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
        w: "",
        h: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  mounted() {
    var container = document.getElementById("map");
    var mapOptions = {
      center: new kakao.maps.LatLng(35.204038, 126.810782),
      level: 5
    };
    var map = new kakao.maps.Map(container, mapOptions);

   
  },
  methods: {
    onclick(){
    var container = document.getElementById("map");
    var mapOptions = {
      center: new kakao.maps.LatLng(35.204038, 126.810782),
      level: 5
    };
    //35.199111, 126.834800
    var map = new kakao.maps.Map(container, mapOptions);
    if(this.w && this.h){
      console.log(this.w);
      console.log(this.h);
      
      
        // 마커가 표시될 위치입니다 
        var markerPosition  = new kakao.maps.LatLng(this.w, this.h); 

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
      }
      else{
        alert("비어있음")
      } 
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
    invalidForm(){
      return !this.userName || !this.phone
    }
  }
};
</script>

<style lang="css">
    @import url("../../assets/bootstrap/bootstrap.css");
</style>
