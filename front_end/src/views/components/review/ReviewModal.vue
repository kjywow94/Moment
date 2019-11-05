<template>
  <!-- Modal -->
  <modal v-if="modalSwitch" @close="detailModalHide">
    <template slot="header">
      <h4>{{this.detailModalData.location}}</h4>
      <md-button
        class="md-simple md-just-icon md-round modal-default-button"
        @click="detailModalHide"
      >
        <md-icon>clear</md-icon>
      </md-button>
    </template>

    <template slot="body">
      <div class="modal-body blockquote undefined">
        <img :src="this.detailModalData.imageData" class="modal-img img-raised rounded" />
        <h5>{{this.detailModalData.date | moment("YYYY.MM.DD , dddd")}}</h5>
        <p>{{this.detailModalData.content}}</p>
        <small>by {{this.detailModalData.nickname}}</small>
        <div class="my-hashtag-div">
          <small class="my-hashtag">{{this.detailModalData.hashtag}}</small>
        </div>
      </div>
    </template>

    <template slot="footer">
      <div>
        <md-button class="md-simple md-danger" v-if="isLike" @click="unLike()">
          <md-icon>favorite</md-icon>
          {{this.detailModalData.liked}}
        </md-button>

        <md-button class="md-simple" v-if="!isLike" @click="likeIt()">
          <md-icon>favorite</md-icon>
          {{this.detailModalData.liked}}
        </md-button>

        <md-button class="md-danger md-simple" @click="detailModalHide">Close</md-button>
      </div>
    </template>
  </modal>
  <!--Model end-->
</template>
<script>
import { EventBus } from "@/main.js";
import ReviewService from "@/services/ReviewService.js";
import UserService from "@/services/UserService.js";
import EthereumService from "@/services/EthereumService.js";
import { Modal } from "@/components";

export default {
  components: {
    Modal
  },
  data() {
    return {
      isLike: false,
      detailModalData: null,
      modalSwitch: false
    };
  },
  methods: {
    onModal() {
      EthereumService.read(this.detailModalData.hash, content => {
        this.detailModalData.content = content;
        ReviewService.isLike({
          uid: this.$store.state.user.id,
          rid: this.detailModalData.id
        }).then(response => {
          if (response.data == 0) this.isLike = false;
          else {
            this.detailModalData.like = response.data;
            this.isLike = true;
          }
          this.modalSwitch = true;
        });
      });
    },
    detailModalHide() {
      this.modalSwitch = false;
      this.$store.state.detailModalData = null;
      this.detailModalData = null;
      this.$store.state.isDetailOn = false;
    },
    likeIt() {
      ReviewService.likeIt({
        uid: this.$store.state.user.id,
        rid: this.detailModalData.id
      }).then(response => {
        var max = response.data.max;
        if (response.data.point) {
          let point = max / 10;
          UserService.getUserById(this.detailModalData.uid).then(userInfo => {
            let to = userInfo.data.walletAddress;
            EthereumService.sendPoint(to, point, recept => {});
          });
        }
        this.detailModalData.like = response.data.id;
        this.detailModalData.liked = this.detailModalData.liked + 1;
        this.isLike = true;
      });
    },
    unLike() {
      ReviewService.unLike({
        uid: this.$store.state.user.id,
        rid: this.detailModalData.id,
        id: this.detailModalData.like
      }).then(response => {
        this.detailModalData.liked = this.detailModalData.liked - 1;
        this.isLike = false;
      });
    }
  },
  computed: {
    isDetailOn() {
      return this.$store.state.isDetailOn;
    }
  },
  watch: {
    isDetailOn(isDetailOn) {
      if (isDetailOn) {
        this.detailModalData = this.$store.state.detailModalData;
        this.onModal();
      }
    }
  }
};
</script>
<style scoped>
</style>