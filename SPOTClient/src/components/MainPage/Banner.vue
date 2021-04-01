<template>
  <v-container>
    <v-row class="mx-auto">
      <v-col style="margin-top : 20px;">
        <h1>{{ getMemberInfo.nickname }}<span style="font-size : 20px;">님,</span></h1>
        <h2>환영합니다!</h2>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
const MemberStore = "MemberStore";

export default {
  computed: {
    ...mapGetters(MemberStore, ["getMemberInfo"])
  }
};
</script>

<style></style>
