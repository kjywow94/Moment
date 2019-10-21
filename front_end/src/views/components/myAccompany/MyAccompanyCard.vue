<template>
  <div class="wrapper">
    <div class="container">
      <tabs
        :tab-name="['진행중', '진행예정', '진행종료']"
        :tab-icon="['camera', 'palette', 'favorite']"
        plain
        nav-pills-icons
        color-button="success"
      >
        <template slot="tab-pane-1">
          <div class="md-layout">
            <p>test</p>
          </div>
        </template>
        <template slot="tab-pane-2">
          <div class="md-layout">
            <p>test2</p>
          </div>
        </template>
        <template slot="tab-pane-3">
          <div class="md-layout">
            <p>test3</p>
          </div>
        </template>
      </tabs>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/components";
export default {
  components: { Tabs },
  data() {
    return {
      tabPane1: [],
      tabPane2: [],
      tabPane3: []
    };
  }
};
</script>
<style lang="scss" scoped>
@import url("../../../assets/bootstrap/bootstrap.css");
</style>