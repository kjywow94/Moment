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
                  <div class="card-footer bg-transparent border-primary">
                    <md-button class="md-info" @click="accompanyEnd(item)">동행 종료</md-button>
                    <md-button class="md-info">채팅방</md-button>
                  </div>
                </div>
              </div>
            </template>
            <!-- 동행 예정 -->
            <template slot="tab-pane-2">
              <div class="card-columns">
                <div
                  class="card border-success"
                  style="max-width: 18rem;"
                  v-for="item in toAccompany"
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
                  v-for="item in endAccompany"
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
import SmartContractService from "@/services/SmartContractService.js";
import EthereumService from "@/services/EthereumService.js";
import { Tabs } from "@/components";
import { async } from "q";

export default {
  components: { Tabs },
  data() {
    return {
      uid: 0,
      isProccessing: false,
      allAccompany: [],
      processAccompany: [],
      endAccompany: [],
      toAccompany: []
    };
  },
  methods: {
    accompanyStart(item) {
      var startDate = TimeConvertService.timeToUnix(new Date(item.startDate));
      var endDate = TimeConvertService.timeToUnix(new Date(item.endDate));
      var curDate = TimeConvertService.timeToUnix(new Date());
      if (endDate < curDate) {
        alert("이미 종료된 일정입니다.");
      } else if (curDate < startDate) {
        alert("아직 동행 시간이 아닙니다.");
      } else {
        // 위도 : latitude
        // 경도 : longitude
        this.isProccessing = true;
        // 서버와 통신, 리턴값으로 시작인지 이미 시작됫는지 파악하고 적절한 컨트랙트 함수 호출
        LocationService.getLocation((latitude, longitude) => {
          AccompanyService.getAccompanyByCid(item.tid).then(response => {
            if (response.data == "") {
              /**스마트 컨트랙트 배포*/
              /**  계약을 생성중입니다 로딩창 필요*/

              SmartContractService.deployContract(
                item.tid,
                this.uid,
                curDate,
                String(latitude),
                String(longitude),
                contractAddress => {
                  /** 계약 주소 디비에 저장 */
                  AccompanyService.insertAccompany({
                    cid: item.tid,
                    contractAddress: contractAddress
                  }).then(response => {
                    /** 내 동행 업데이트후 진행중으로 이동 */
                    this.toStart(item);
                  });
                }
              );
            } else {
              /** 시작된 동행의 경우 */
              /** 컨트랙트 접근 후 시작 등록 */
              let contractAddress = response.data.contractAddress;
              SmartContractService.startAccompany(
                contractAddress,
                this.uid,
                curDate,
                String(latitude),
                String(longitude),
                () => {
                  this.toStart(item);
                }
              );
            }
          });
        });
      }
    },
    accompanyEnd(item) {
      var startDate = TimeConvertService.timeToUnix(new Date(item.startDate));
      var endDate = TimeConvertService.timeToUnix(new Date(item.endDate));
      var curDate = TimeConvertService.timeToUnix(new Date());
      if (curDate < endDate) {
        alert("아직 종료예정 시간이 아닙니다.");
      } else {
        LocationService.getLocation((latitude, longitude) => {
          AccompanyService.getAccompanyByCid(item.tid).then(response => {
            let contractAddress = response.data.contractAddress;
            SmartContractService.endAccompany(
              contractAddress,
              this.uid,
              curDate,
              String(latitude),
              String(longitude),
              response => {
                console.log(response);
                /** isEnd */
              }
            );
          });
        });
      }
    },
    toStart(item) {
      MyAccompanyService.updateAccompanyParti({
        id: item.id,
        tid: item.tid,
        participateTime: item.startDate,
        realStartDate: new Date(),
        realEndDate: new Date(0),
        status: "시작"
      }).then(response => {
        /** 등록자가 완료변경을 안했을 수도 있으니 동행 등록을 완료로 변경 */
        AccompanyService.successAccompanyRegist(item.tid).then(response => {
          /** 화면 갱신 */
          this.initList();
          this.isProccessing = false;
          alert("동행이 시작되었습니다.");
        });
      });
    },
    initList() {
      this.allAccompany = [];
      this.processAccompany = [];
      this.endAccompany = [];
      this.toAccompany = [];
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
  },
  mounted() {
    this.uid = this.$store.state.user.id;
    this.initList();
  }
};
</script>
<style lang="scss" scoped>
// @import url("../../../assets/bootstrap/bootstrap.css");
</style>