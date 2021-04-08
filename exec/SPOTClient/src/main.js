import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store/store";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;

// 카카오 링크 사용
window.Kakao.init("a2ef75e84ec5b6042fe36d22f21ed028");

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
