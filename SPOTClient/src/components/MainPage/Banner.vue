<template>
  <v-container>
    <v-row class="mx-4">
      <v-col clas="mt-2">
        <p style="font-size : 2.5em;">
          {{ getMemberInfo.nickname }}
          <span style="font-size : 20px; margin-left: -10px;">님,</span>
        </p>
        <p style="font-size: 2.5em;">
          환영합니다!
          <v-btn depressed icon @click="logout"
            ><v-icon>mdi-logout</v-icon></v-btn
          >
        </p>
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
