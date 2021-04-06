<template>
  <!-- <v-app-bar color="#1DE9B6" height="100" max-height="100">
    <v-row>
      <v-col>

      </v-col>
    </v-row>
  </v-app-bar> -->
  <v-card>
    <v-tabs
      v-model="tab"
      background-color="#92A8D1"
      height="100px"
      centered
      dark
    >
      <v-tabs-slider></v-tabs-slider>

      <v-tab style="width:25%;" @click="clickTab('main')">
        <v-icon x-large>mdi-home</v-icon>
      </v-tab>

      <v-tab style="width:25%;" @click="clickTab('course')">
        <v-icon x-large>mdi-calendar-plus</v-icon>
      </v-tab>

      <v-tab style="width:25%;" @click="clickTab('search')">
        <v-icon x-large>mdi-magnify</v-icon>
      </v-tab>

      <v-tab style="width:25%;" @click="clickTab('mypage')">
        <v-icon x-large>mdi-account-circle-outline</v-icon>
      </v-tab>
    </v-tabs>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";
const MemberStore = "MemberStore";

export default {
  data() {
    return {
      tab: ""
    };
  },
  computed: {
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    clickTab(val) {
      if (val == "main") {
        this.$router.push("/main");
      } else if (val == "course") {
        this.$router.push("/course");
      } else if (val == "search") {
        this.$router.push("/find");
      } else {
        console.log(this.getMemberInfo);
        this.$router.push("/mypage/" + this.getMemberInfo.email);
      }
    }
  }
};
</script>
