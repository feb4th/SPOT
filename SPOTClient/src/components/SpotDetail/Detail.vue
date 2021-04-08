<template>
  <v-container>
    <v-card
      elevation="0"
      style="margin-left: 1.5em;"
      v-if="this.$route.params.spotid < 500000"
    >
      <v-card-title>
        <v-icon x-large>mdi-check</v-icon>
        <strong>영업시간</strong>
        <v-card-text v-for="hours in getSpot.bhour_list" :key="hours.type">
          {{ hours.etc }}
        </v-card-text>
      </v-card-title>
    </v-card>

    <v-card
      elevation="0"
      style="margin-left: 1.5em;"
      v-if="this.$route.params.spotid < 500000"
    >
      <v-card-title>
        <v-icon x-large>mdi-check</v-icon>
        <strong>대표메뉴</strong>
        <v-card-text v-for="menus in getSpot.menu_list" :key="menus.menu">
          {{ menus.menu }} : {{ menus.price }} 원
        </v-card-text>
      </v-card-title>
    </v-card>

    <v-card elevation="0" style="margin-left: 1.5em;" v-else>
      <v-card-title>
        <v-icon x-large>mdi-check</v-icon>
        <strong>관광지정보</strong>
        <v-card-text>
          {{ getSpot.info }}
        </v-card-text>
      </v-card-title>
    </v-card>

    <v-card elevation="0" style="margin-left: 1.5em;">
      <v-card-title>
        <v-icon x-large>mdi-check</v-icon>
        <strong>전화번호</strong>
        <v-card-text> {{ getSpot.tel }} </v-card-text>
      </v-card-title>
    </v-card>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
const SpotInfoStore = "SpotInfoStore";

export default {
  computed: {
    ...mapGetters(SpotInfoStore, ["getSpot"])
  }
};
</script>

<style></style>
