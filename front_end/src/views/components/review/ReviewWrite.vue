<template>
  <div class="md-layout-item md-size-100 mx-auto review_button">
    <md-button class="md-info md-just-icon md-round" @click="doWrite">
      <md-icon>create</md-icon>
    </md-button>

    <modal v-if="classicModal" @close="classicModalHide">
      <template slot="header">
        <h4 class="modal-title">리뷰쓰기</h4>
        <md-button
          class="md-simple md-just-icon md-round modal-default-button"
          @click="classicModalHide"
        >
          <md-icon>clear</md-icon>
        </md-button>
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
        <img :src="imgData" id="preview" class="img-thumbnail img-size">
        <input-tag v-model="hashtag" placeholder="태그"></input-tag>
      </template>
      <template slot="footer">
        <md-button class="md-info md-simple" @click="submit">등록</md-button>
        <md-button class="md-danger md-simple" @click="classicModalHide">취소</md-button>
      </template>
    </modal>
  </div>
</template>

<script>
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
      classicModal: false,
      content: null,
      imgName: "이미지를 올려주세요",
      imgData: "https://placehold.it/80x80",
      location: null,
      latitude: null,
      longitude: null
    };
  },
  mounted() {},
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
        console.log(this.imgData);
      };
    },
    doWrite() {
      LocationService.getLocation((latitude, longitude) => {
        console.log(latitude);
        console.log(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
      });
      this.classicModal = true;
    },
    submit() {
      let title = this.content.substring(0, 20) + "...";
      console.log("title : ", title);
      console.log(this.img);
      let hashtagToSTring = "";
      this.hashtag.forEach(element => {
        hashtagToSTring = hashtagToSTring + element + " ";
      });
      console.log("hashtag : ", hashtagToSTring);
      // EthereumService.write(
      //   "0x1e5b2735b89ef05298bc18a92dbfdec7174beb60",
      //   "ssafy",
      //   data,
      //   receipt => {
      //     console.log("트랜잭션 주소 : ", receipt.transactionHash);
      //   }
      // );
      EthereumService.write(
        this.$store.state.user.walletAddress,
        "ssafy",
        this.content,
        receipt => {
          ReviewService.writeReview({
            date: new Date(),
            title: title,
            hash: receipt.transactionHash,
            hashtag: hashtagToSTring,
            location: this.location,
            latitude: this.latitude,
            longitude: this.longitude,
            distance: this.distance,
            uid: this.$store.state.user.id
          }).then(response => {
            console.log(response);
          });
        }
      );
    },
    classicModalHide() {
      this.classicModal = false;
      this.hashtag = [];
      this.content = null;
      this.img = null;
      this.location = null;
      this.imgName = null;
      this.imgData = null;
    }
  }
};
</script>
<style scoped>
.img-size{
    max-height: 50%;
    max-width: 50%;
}
.file {
    display:none;
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