<template>
  <v-container>
    <v-row class="mx-2 mt-1 mb-2">
      <v-col cols="4"><v-img src="@/assets/spot.png"></v-img></v-col>
      <v-col align="end" class="mt-4">
        <span style="font-size : 2em;">{{ getMemberInfo.nickname }}</span>
        <span class="ma-1 mr-3" style="font-size : 15px;">님,</span>
        <v-spacer></v-spacer>
        <span class="mr-1" style="font-size : 1.5em;">
          환영합니다!
          <v-btn depressed icon @click="logout"
            ><v-icon>mdi-logout</v-icon></v-btn
          >
        </span>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const MemberStore = "MemberStore";

export default {
  computed: {
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(MemberStore, ["Logout"]),

    logout() {
      this.Logout();
      this.$router.push("/login");
    }
  }
};
</script>

<style></style>
