<template>
  <v-container>
    <v-row class="mx-auto">
      <v-col style="margin-top : 20px;">
        <p style="font-size : 3em;">{{ getMemberInfo.nickname }}
          <span style="font-size : 20px;">님,</span>
          </p>
        <p style="font-size: 2.5em;">환영합니다!</p>
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
