<template>
  <modal v-if="this.$store.state.myInfoModalOn" @close="classicModalHide">
    <template slot="body">
      <div>
        <md-button href="#/profile" @click="classicModalHide" class="md-rose">내 프로필</md-button>
      </div>
      <div>
        <md-button href="#/mypage/myuserinfo" @click="classicModalHide" class="md-rose">내 정보</md-button>
      </div>
      <div>
        <md-button href="#/login" v-on:click="isLogout" @click="classicModalHide" class="md-rose">로그아웃</md-button>
      </div>
      <div v-if="$store.state.user.authority === 'A'" @click="classicModalHide">
        <md-button href="#/admin">관리자 페이지</md-button>
      </div>
    </template>
  </modal>
</template>

<script>
import { Modal } from "@/components";

export default {
  components: {
    Modal
  },
  data() {
    return {};
  },
  mounted() {},
  methods: {
    isLogout() {
      this.$store.commit("logout");
      sessionStorage.clear();
      localStorage.clear();
      alert("정상적으로 로그아웃 되었습니다.");
    //   location.reload();
    },
    classicModalHide() {
      this.$store.state.myInfoModalOn = false;
    }
  }
};
</script>
<style scoped>
@media (min-width: 481px) {
}

/* 모바일*/
@media (max-width: 480px) {
}
</style>