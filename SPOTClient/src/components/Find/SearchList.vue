<template>
  <v-container>
    <v-sheet class="mx-auto" elevation="8" max-width="800">
      <template v-if="getSearchList.length == 0">
        <h1>검색 결과가 없습니다.</h1>
      </template>
      <v-slide-group class="pa-4" active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getSearchList" :key="idx">
          <v-card
            class="ma-4"
            height="200"
            width="200"
            @click="onSelect(card.id)"
          >
            <v-card-title> {{ card.name }} </v-card-title>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
const FindStore = "FindStore";
const SpotInfoStore = "SpotInfoStore";

export default {
  computed: {
    ...mapGetters(FindStore, ["getSearchList"])
  },
  methods: {
    ...mapActions(SpotInfoStore, ["reqSpot"]),
    onSelect(storeNum) {
      this.$router.push("/spotdetail/" + storeNum);
    }
  }
};
</script>

<style></style>
