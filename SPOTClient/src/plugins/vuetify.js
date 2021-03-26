import "material-design-icons-iconfont/dist/material-design-icons.css";
//yarn add material-design-icons-iconfont -D
import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  //아이콘 추가
  icons: {
    iconfont: "md"
  }
});
