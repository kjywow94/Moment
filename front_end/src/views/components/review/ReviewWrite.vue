<template>
  <modal v-if="this.$store.state.ReviewWriteOn" @close="classicModalHide">
    <template slot="header">
      <h4>리뷰쓰기</h4>
    </template>
    <template slot="body">
      <!-- 지도로 변경 -->
      <md-field class="md-form-group" slot="inputs">
        <md-icon>edit</md-icon>
        <label>위치</label>
        <md-input v-model="location"></md-input>
      </md-field>

      <md-field class="md-form-group" slot="inputs">
        <md-icon>edit</md-icon>
        <md-textarea v-model="content" md-autogrow placeholder="내용"></md-textarea>
      </md-field>
      <input
        type="file"
        ref="inputRef"
        name="img[]"
        class="file"
        accept="image/*"
        @change="changeFile($event)"
      />
      <md-field class="md-form-group" slot="inputs">
        <md-file v-model="imgName" accept="image/*" placeholder="사진" v-on:click="clickUpload()" />
      </md-field>
      <img :src="imgData" id="preview" class="img-thumbnail img-size" />
      <input-tag v-model="hashtag" placeholder="태그"></input-tag>
    </template>
    <template slot="footer">
      <md-button
        v-if="!busy"
        class="md-info md-simple"
        @click="submit"
        :class="{'btn-success' : !invalidForm}"
        :disabled="invalidForm"
      >등록</md-button>
      <md-button v-if="busy" class="md-info md-simple" disabled>등록중입니다</md-button>
      <md-button class="md-danger md-simple" @click="classicModalHide">취소</md-button>
    </template>
  </modal>
</template>

<script>
import { EventBus } from "@/main.js";
import ReviewService from "@/services/ReviewService.js";
import LocationService from "@/services/LocationService.js";
import EthereumService from "@/services/EthereumService.js";
import { Modal } from "@/components";
import InputTag from "vue-input-tag";

export default {
  components: {
    Modal,
    InputTag
  },
  data() {
    return {
      hashtag: [],
      content: null,
      imgName: "이미지를 올려주세요",
      imgData: "https://placehold.it/80x80",
      location: null,
      latitude: null,
      longitude: null,
      busy: false
    };
  },
  mounted() {
    this.ModalOn();
    // this.locationroad();
  },
  methods: {
    clickUpload() {
      event.preventDefault();
      this.$refs.inputRef.click();
    },
    changeFile(event) {
      this.imgName = event.target.files[0].name;
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);

      reader.onload = e => {
        this.imgData = e.target.result;
      };
    },

    doWrite() {
      LocationService.getLocation((latitude, longitude) => {
        this.latitude = latitude;
        this.longitude = longitude;
      });
      this.classicModal = true;
    },

    submit() {
      this.busy = true;
      let title = this.content.substring(0, 12);
      if (this.content.length > 12) {
        title = title + "...";
      }
      let hashtagToSTring = "";
      this.hashtag.forEach(element => {
        hashtagToSTring = hashtagToSTring + element + " ";
      });

      EthereumService.write(
        this.$store.state.user.walletAddress,
        this.$store.state.user.password,
        this.content,
        receipt => {
          var date = new Date();
          date.setHours(date.getHours() + 9);
          ReviewService.writeReview({
            date: date,
            title: title,
            hash: receipt.transactionHash,
            hashtag: hashtagToSTring,
            location: this.location,
            latitude: this.latitude,
            longitude: this.longitude,
            distance: this.distance,
            uid: this.$store.state.user.id
          }).then(response => {
            ReviewService.uploadImage({
              imageData: this.imgData,
              imageNum: response.data
            }).then(response => {
              this.busy = false;
              alert("등록되었습니다.");
              this.classicModalHide();
              /** 새로고침 이벤트 추가 필요 , 이벤트 버스 */
              EventBus.$emit("reloadReviewList");
            });
          });
        }
      );
    },
    classicModalHide() {
      this.$store.state.ReviewWriteOn = false;
      this.$store.state.latitude = null;
      this.$store.state.longitude = null;
      this.hashtag = [];
      this.content = null;
      this.img = null;
      this.location = null;
      this.imgName = null;
      this.imgData = null;
    },
    ModalOn() {
      if (this.$store.state.ReviewWriteOn) {
        this.latitude = this.$store.state.latitude;
        this.longitude = this.$store.state.longitude;
      }
    },
    locationroad() {
      var scope = this;
      LocationService.getLocation((latitude, longitude) => {
        this.latitude = latitude;
        this.longitude = longitude;
        var geocoder = new kakao.maps.services.Geocoder();

        var coord = new kakao.maps.LatLng(this.latitude, this.longitude);
        var callback = function(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            if (result[0].road_address !== null) {
              if (result[0].road_address.building_name !== "") {
                scope.location = result[0].address.address_name;
                return;
              }

              if (result[0].road_address.address_name !== "") {
                scope.location = result[0].road_address.address_name;
                return;
              }
            }

            if (result[0].address.address_name !== "") {
              scope.location = result[0].address.address_name;
              return;
            }
          }
        };
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
      });
    }
  },
  computed: {
    getLocation() {
      return this.$store.state.ReviewWriteOn;
    },
    invalidForm() {
      return !this.location || !this.imgName || !this.content;
    }
  },
  watch: {
    getLocation() {
      if (this.$store.state.ReviewWriteOn) {
        this.locationroad();
      }
    }
  }
};
</script>
<style scoped>
.img-size {
  max-height: 50%;
  max-width: 50%;
}
.file {
  display: none;
}
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

.review_button {
  text-align: center;
}

@media (min-width: 481px) {
  .mdquery-xs {
    display: none;
  }
}

/* 모바일*/
@media (max-width: 480px) {
  .mdquery-md {
    display: none;
  }
}
</style>