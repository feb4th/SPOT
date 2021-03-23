<template>
  <v-app>
    <!-- Content, Footer -->
    <router-view />

    <!-- 로그인 시 푸터를 보여줌 -->
    <Footer />
  </v-app>
</template>

<script>
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
