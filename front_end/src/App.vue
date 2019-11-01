<template>
  <div id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <div>
        <router-view :key="$route.fullPath" />
        <ReviewWrite />
        <MyInfoModal />
      </div>
      <router-view name="footer" />
    </div>
  </div>
</template>

<script>
import ReviewWrite from "@/views/components/review/ReviewWrite";
import MyInfoModal from "@/views/components/profile/MyInfoModal";

export default {
  components: {
    ReviewWrite,
    MyInfoModal
  }
};
</script>
