<template>
  <v-container>
    <v-row class="mx-auto">
      <v-col>
        <h1>{{ getMemberInfo.nickname }}님 환영합니다</h1>
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
