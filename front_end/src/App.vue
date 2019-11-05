<template>
  <div id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <div>
        <router-view :key="$route.fullPath" />
        <ReviewWrite />
        <ReviewModal />
        <MyInfoModal />
      </div>
      <router-view name="footer" />
    </div>
  </div>
</template>

<script>
import ReviewWrite from "@/views/components/review/ReviewWrite";
import ReviewModal from "@/views/components/review/ReviewModal";
import MyInfoModal from "@/views/components/profile/MyInfoModal";

export default {
  components: {
    ReviewWrite,
    ReviewModal,
    MyInfoModal
  }
};
</script>
