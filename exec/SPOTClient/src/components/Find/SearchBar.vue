<template>
  <v-container>
    <v-row justify="space-around">
      <v-col cols="11" align="center">
        <v-text-field
          v-model="msg"
          label="검색어"
          placeholder="원하는 검색어를 입력하세요."
          append-outer-icon="mdi-magnify"
          @keypress.enter="onSearch"
          @click:append-outer="onSearch"
          clear-icon="mdi-close-circle"
          clearable
          @click:clear="onReset"
        ></v-text-field>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";
const FindStore = "FindStore";
export default {
  data() {
    return {
      msg: ""
    };
  },
  methods: {
    ...mapActions(FindStore, ["reqSearch"]),
    onSearch() {
      //여기서 검색을 하고, 검색 대상을 Store에 선언해둔 녀석에 넣는건가?
      this.reqSearch(this.msg); // 검색
      // this.msg = "";
    },
    onReset() {
      this.msg = "";
    }
  }
};
</script>

<style></style>
