<template>
  <v-container>
    <v-row class="ma-3">
      <v-col>
        <span style="font-size: 2em; font-weight: 900;">💫추천 테마💫</span>
      </v-col>
    </v-row>
    <v-carousel
      class="mx-auto"
      height="270"
      style="width:800px;"
      cycle
      show-arrows-on-hover
      hide-delimiter-background
    >
      <v-carousel-item v-for="(card, i) in getThemeList" :key="i">
        <v-sheet height="270">
          <v-row class="fill-height" align="center" justify="center">
            <v-img :src="getSrc(card.id)" @click="onSelect(card.web)"></v-img>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const ThemeStore = "ThemeStore";

export default {
  computed: {
    ...mapGetters(ThemeStore, ["getThemeList"])
  },
  methods: {
    ...mapActions(ThemeStore, ["reqThemeList"]),
    onSelect(web) {
      window.open(web);
    },
    getSrc(order) {
      return require("../../assets/images/theme/theme" + order + ".png");
    }
  }
};
</script>

<style></style>
