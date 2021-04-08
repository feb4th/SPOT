<template>
  <v-container>
    <v-sheet class="mx-auto" max-width="800" justify="center" align="center">
      <h1 v-if="getSearchList.length == 0">검색 결과가 없습니다.</h1>
      <v-row>
        <v-card
          v-for="(card, idx) in getSearchList"
          :key="idx"
          height="180"
          width="180"
          class="mx-auto my-3"
          @click="onSelect(card.id)"
        >
          <v-img
            contain
            aspect-ratio="1"
            :src="getSrc(card.img)"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          >
            <v-card-title> {{ card.name }} </v-card-title></v-img
          >
        </v-card>
      </v-row>
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
    ...mapActions(SpotInfoStore, ["reqSpot", "reqTourSight"]),
    onSelect(spot_id) {
      if (spot_id < 500000) {
        this.reqSpot(spot_id).then(response => {
          if (response.result == true) {
            this.$router.push("/spotdetail/" + spot_id);
          }
        });
      } else {
        this.reqTourSight(spot_id).then(response => {
          if (response.result == true) {
            this.$router.push("/spotdetail/" + spot_id);
          }
        });
      }
    },
    getSrc(order) {
      if (order == "" || order == "null" || order == undefined)
        return "@/assets/logo.png";
      // console.log(order);
      return order;
    }
  }
};
</script>

<style></style>
