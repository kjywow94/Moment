<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <br />
        <br />
        <br />
        <div class="md-layout">
          <div
            class="md-layout-item md-size-100 md-small-size-100"
            style="margin: 0 auto; margin-top: 20px; margin-bottom: 20px;"
          >
            <div class="main main-raised">
              <div
                class="section section-contacts"
                style="padding-top: 10px; padding-bottom: 10px; padding-right: 10px; padding-left: 10px;"
              >
                <div id="dmap">
                  <div id="map" style="width:100%; height:600px;"></div>
                </div>
              </div>
            </div>
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
import { Modal } from "@/components";
import LocationService from "@/services/LocationService.js";
import ReviewService from "@/services/ReviewService.js";
import EthereumService from "@/services/EthereumService.js";

export default {
  components: {
    LoginCard,
    Tabs,
    Modal
  },
  bodyClass: "login-page",
  data() {
    return {
      distance: 9999999,
      reviewList: [{}]
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      LocationService.getLocation((latitude, longitude) => {
        ReviewService.getReviewListByLocation({
          start: 0,
          end: 100,
          latitude: latitude,
          longitude: longitude,
          distance: this.distance
        }).then(reviewList => {
          this.reviewList = reviewList.data;

          var container = document.getElementById("map");
          var mapOptions = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: (5 - parseInt(screen.width/300)) < 1 ? 1 : (5 - parseInt(screen.width/300))
          };
          var map = new kakao.maps.Map(container, mapOptions);

          for (var i = 0; i < this.reviewList.length; i++) {
            var marker = new kakao.maps.Marker({
              map: map,
              position: new kakao.maps.LatLng(
                this.reviewList[i].latitude,
                this.reviewList[i].longitude
              )
            });

            var content =
              '<div class="wrap1">' +
              '    <div class="info1">' +
              '        <div class="title1 colorfont">' +
              this.reviewList[i].title +
              "        </div>" +
              '        <div class="body">' +
              '            <div class="img my-map-img-box">' +
              '                <img  class="my-map-img" src="' +
              this.reviewList[i].imageData +
              '" width="73" height="70">' +
              "            </div>" +
              '            <div class="desc">' +
              '                <div class="jibun ellipsis">' +
              this.reviewList[i].location +
              "</div>" +
              '<div class="jibun ellipsis">' +
              this.convertDate(this.reviewList[i].date) +
              "</div>" +
              '                <div class="elipsis colorfont"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Coraz%C3%B3n.svg/174px-Coraz%C3%B3n.svg.png" width="13" height="13"> ' +
              this.reviewList[i].liked +
              "</div>" +
              "            </div>" +
              "        </div>" +
              "    </div>" +
              "</div>";

            // 마커 위에 커스텀오버레이를 표시합니다
            // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
            var overlay = new kakao.maps.CustomOverlay({
              content: content,
              position: marker.getPosition()
            });

            kakao.maps.event.addListener(
              marker,
              "mouseover",
              makeOverListener(map, marker, overlay)
            );

            kakao.maps.event.addListener(
              marker,
              "mouseout",
              makeOutListener(overlay)
            );

            kakao.maps.event.addListener(
              marker,
              "click",
              clip(map, marker, overlay)
            );

            kakao.maps.event.addListener(map, "click", close(overlay));

            function relayout() {
              map.relayout();
            }
          }

          let flag = false;
          function makeOverListener(map, marker, overlay) {
            return function() {
              overlay.setMap(map, marker);
            };
          }
          function makeOutListener(overlay) {
            return function() {
              if (!flag) overlay.setMap(null);
            };
          }

          function close(overlay) {
            return function() {
              overlay.setMap(null);
              flag = false;
            };
          }

          function clip(date) {
            return function() {
              flag = true;
            };
          }
        });
      });
    },
    convertDate(date) {
      var jsDate = new Date(date);
      var year = jsDate.getFullYear();
      var month = jsDate.getMonth() + 1;
      var date = jsDate.getDate();
      var result = year + "-" + month + "-" + date;
      return result;
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url("https://images.unsplash.com/photo-1516738901171-8eb4fc13bd20?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")`
        // backgroundImage: `url("https://images.unsplash.com/photo-1541967363726-9ca08d515ccd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")`
      };
    },
    invalidForm() {
      return !this.userName || !this.phone;
    }
  }
};
</script>

<style lang="css">
@import url("../../assets/bootstrap/bootstrap.css");
.wrap1 {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap1 * {
  padding: 0;
  margin: 0;
}
.wrap1 .info1 {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap1 .info1:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info1 .title1 {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info1 .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info1 .close:hover {
  cursor: pointer;
}
.info1 .body {
  position: relative;
  overflow: hidden;
}
.info1 .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap1;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info1 .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info1:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info1 .link {
  color: #5085bb;
}
.colorfont {
  color: #000000;
}
.my-map-img-box {
  position: relative;
  overflow: hidden;
}
.my-map-img {
  position: absolute;
  left: 50%;
  top: 50%;
  height: 100%;
  width: auto;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>

