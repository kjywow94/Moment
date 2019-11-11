<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container">
      <div class="copyright" style="margin: 0 auto">
        &copy; {{ year }}, made by
        <a href="https://lab.ssafy.com/s1-final/s1p3151008" target="_blank">
        <i class="fab fa-github"></i>
        BoraCoin<md-tooltip md-direction="top">Git Page</md-tooltip>
        </a>
        for a better moment
        <h5 style="text-align : center">
          Since. Oct 2019
        </h5>
      </div>
      
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String
  },
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>
<style></style>
