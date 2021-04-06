<template>
  <v-app>
    <router-view id="contents" />
    <!-- 로그인 시 푸터를 보여줌 -->
    <Footer id="footer" />
  </v-app>
</template>

<script>
import "./assets/style.css";
import Footer from "./views/Footer";
export default {
  name: "App",

  components: {
    Footer
  },

  data: () => ({
    //
  })
};
</script>

<style>
#contents {
  left: 0;
  right: 0;
  top: 0;
  margin-bottom: 6rem;
}
#footer {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 6rem;
  z-index: 99999;
}
</style>
