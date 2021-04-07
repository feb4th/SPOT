<template>
  <v-container>
    <v-row class="mx-auto" style="margin: 1.5em;">
      <v-col style="margin: auto;">
        <span style="font-size: 2em;">💫추천 테마💫</span>
      </v-col>
    </v-row>
    <!-- <v-sheet class="mx-auto" elevation="0" max-width="1000">
      <v-slide-group class="pa-4" active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getThemeList" :key="idx">
          <v-card
            class="ma-4"
            height="200"
            width="200"
            @click="onSelect(card.theme_id)"
          >
            이미지 데이터가 없을 때 
            <v-img v-if="card.img == '' || car.img == null"> </v-img>
            <v-img v-else :src="card.img"> </v-img>

            <v-card-title class="text-no-wrap text-truncate">{{
              card.name
            }}</v-card-title>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet> -->
    <v-row>
      <v-window show-arrows class="ma-6" style="width: 95%;">
        <v-window-item v-for="(card, idx) in getThemeList" :key="idx">
          <v-card @click="onSelect(card.web)">
            <v-row align="center" justify="center">
              <v-img contain :src="getSrc(card.id)" height="300"></v-img>
            </v-row>
          </v-card>
        </v-window-item>
      </v-window>
    </v-row>
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
      return require("../../assets/images/theme" + order + ".png");
    }
  }
};
</script>

<style></style>
