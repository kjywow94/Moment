<template>
  <div id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <div>
        <router-view  :key="$route.fullPath"/>
        <ReviewWrite/>
      </div>
      <router-view name="footer" />
    </div>
  </div>
</template>

<script>
import ReviewWrite from "@/views/components/review/ReviewWrite";

export default {
  components :{
    ReviewWrite
  }
}
</script>
