<template>
  <div class="wrapper">
    <div id="navigation-pills">
      <div class="title">
        <h3>내 동행</h3>
      </div>
      <!-- <div class="title">
        <h3><small>With Icons</small></h3>
      </div>-->
      <div class="md-layout">
        <div class="md-layout-item md-size-100 md-small-size-100">
          <tabs
            :tab-name="['동행중', '예정', '종료']"
            :tab-icon="['dashboard', 'schedule', 'list']"
            plain
            nav-pills-icons
            color-button="primary"
          >
            <!-- 동행 시작 -->
            <template slot="tab-pane-1">
              <div class="card-columns">
                <div
                  class="card border-primary"
                  style="max-width: 18rem;"
                  v-for="item in processAccompany"
                  v-bind:key="item.id"
                >
                  <div class="card-header bg-transparent border-primary">
                    동행시작 : {{ item.realStartDate | moment("YYYY MM DD, dddd") }}
                    <br />
                    종료예정 : {{ item.endDate | moment("YYYY MM DD, dddd") }}
                  </div>
                  <div class="card-body">
                    <h5 class="card-title text-blue">{{item.title}}</h5>
                    <p class="card-text">지역 : {{item.region}}</p>
                    <p class="card-text">도시 : {{item.city}}</p>
                    <router-link
                      :to="{name:'accompanyDetail', params: { id: item.id }}"
                      class="my-table-content"
                    >
                      <md-button class="md-primary md-block">자세히 보기</md-button>
                    </router-link>
                  </div>
                  <div class="card-footer bg-transparent border-primary">종료, 채팅방</div>
                </div>
              </div>
            </template>
            <!-- 동행 예정 -->
            <template slot="tab-pane-2">
              <div class="card-columns">
                <div
                  class="card border-success"
                  style="max-width: 18rem;"
                  v-for="item in processAccompany"
                  v-bind:key="item.id"
                >
                  <div class="card-header bg-transparent border-success">
                    시작예정 : {{ item.startDate | moment("YYYY MM DD, dddd") }}
                    <br />
                    종료예정 : {{ item.endDate | moment("YYYY MM DD, dddd") }}
                  </div>
                  <div class="card-body">
                    <h5 class="card-title text-success">{{item.title}}</h5>
                    <p class="card-text">지역 : {{item.region}}</p>
                    <p class="card-text">도시 : {{item.city}}</p>
                    <router-link
                      :to="{name:'accompanyDetail', params: { id: item.id }}"
                      class="my-table-content"
                    >
                      <md-button class="md-primary md-block">자세히 보기</md-button>
                    </router-link>
                  </div>
                  <div class="card-footer bg-transparent border-success">
                    <md-button class="md-info" @click="accompanyStart(item)">동행 시작</md-button>
                    <md-button class="md-rose">채팅</md-button>
                  </div>
                </div>
              </div>
            </template>
            <!-- 종료한 동행 -->
            <template slot="tab-pane-3">
              <div class="card-columns">
                <div
                  class="card border-warning"
                  style="max-width: 18rem;"
                  v-for="item in processAccompany"
                  v-bind:key="item.id"
                >
                  <div class="card-header bg-transparent border-warning">
                    동행시작 : {{ item.realStartDate | moment("YYYY MM DD, dddd") }}
                    <br />
                    동행종료 : {{ item.realEndDate | moment("YYYY MM DD, dddd") }}
                  </div>
                  <div class="card-body">
                    <h5 class="card-title text-warning">{{item.title}}</h5>
                    <p class="card-text">지역 : {{item.region}}</p>
                    <p class="card-text">도시 : {{item.city}}</p>
                    <router-link
                      :to="{name:'accompanyDetail', params: { id: item.id }}"
                      class="my-table-content"
                    >
                      <md-button class="md-primary md-block">자세히 보기</md-button>
                    </router-link>
                  </div>
                  <div class="card-footer bg-transparent border-warning">후기작성, 채팅방</div>
                </div>
              </div>
            </template>
          </tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyAccompanyService from "@/services/MyAccompanyService.js";
import AccompanyService from "@/services/AccompanyService.js";
import LocationService from "@/services/LocationService.js";
import TimeConvertService from "@/services/TimeConvertService.js";
import { Tabs } from "@/components";
import { async } from "q";

export default {
  components: { Tabs },
  data() {
    return {
      uid: 2,
      allAccompany: [],
      processAccompany: [],
      endAccompany: [],
      toAccompany: []
    };
  },
  methods: {
    accompanyStart(item) {
      console.log(item.tid);
      var startTime = TimeConvertService.timeToUnix(new Date(item.startDate));
      var endTime = TimeConvertService.timeToUnix(new Date(item.endDate));
      var curDate = TimeConvertService.timeToUnix(new Date());
      if (endTime < curDate) {
        alert("이미 종료된 일정입니다.");
      } else if (curDate < startDate) {
        alert("아직 동행 시간이 아닙니다.");
      } else {
        LocationService.getLocation().then(location => {
          // 위도
          var latitude = location.coords.latitude;
          // 경도
          var longitude = location.coords.longitude;
          
          // 서버와 통신, 리턴값으로 시작인지 이미 시작됫는지 파악하고 적절한 컨트랙트 함수 호출
        });
      }
    }
  },
  mounted() {
    MyAccompanyService.getMyAccompanyListbyUid(this.uid)
      .then(response => {
        this.allAccompany = response.data;

        response.data.forEach(async element => {
          await AccompanyService.getAccompanyRegistById(element.tid).then(
            accompanyRegist => {
              element.title = accompanyRegist.data.title;
              element.startDate = accompanyRegist.data.startDate;
              element.endDate = accompanyRegist.data.endDate;
              element.region = accompanyRegist.data.region;
              element.city = accompanyRegist.data.city;
              if (element.status == "시작") {
                this.processAccompany.push(element);
              } else if (element.status == "종료") {
                this.endAccompany.push(element);
              } else {
                this.toAccompany.push(element);
              }
            }
          );
        });
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>
<style lang="scss" scoped>
// @import url("../../../assets/bootstrap/bootstrap.css");
</style>