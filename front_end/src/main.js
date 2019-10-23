import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import axios from 'axios'
import MaterialKit from "./plugins/material-kit";
import store from './store'
/* 테스트중
import Constants from "./assets/constants";
const BaseURL = Constants.BaseURL;
*/

Vue.prototype.$Axios = axios;
Vue.config.productionTip = false;

Vue.use(MaterialKit);

const NavbarStore = {
  showNavbar: false
};


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
