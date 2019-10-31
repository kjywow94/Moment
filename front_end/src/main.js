import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import axios from 'axios'
import MaterialKit from "./plugins/material-kit";
import store from './store'
import VueMoment from "vue-moment";
import infiniteScroll from 'vue-infinite-scroll';

Vue.prototype.$Axios = axios;
Vue.config.productionTip = false;
Vue.use(MaterialKit);
Vue.use(VueMoment);
Vue.use(infiniteScroll);

const NavbarStore = {
  showNavbar: false
};

export const EventBus = new Vue();

Vue.mixin({
  data() {
    return {
      NavbarStore
    };
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
