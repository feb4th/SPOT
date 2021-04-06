<template>
  <v-container>
    <v-row class="mx-4">
      <v-col clas="mt-2" align="end">
        <p style="font-size : 2.5em;">
          {{ getMemberInfo.nickname }}
          <span style="font-size : 20px; margin-left: -10px;">님,</span>
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
