<template>
  <div class="wrapper">
    <parallax class="section page-header" style="background-color:#ed6c6b;"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <UsersProfile />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UsersProfile from "../../components/profile/OtherUserProfile";

export default {
  components: {
    UsersProfile
  }
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}
</style>
