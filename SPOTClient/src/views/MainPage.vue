<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <!-- <v-col cols="12" sm="2"> </v-col> -->
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <banner />
              <main-map />
              <suggest-spot />
              <suggest-theme />
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Banner from "../components/MainPage/Banner.vue";
import MainMap from "../components/MainPage/Map.vue";
import SuggestSpot from "../components/MainPage/SuggestSpot.vue";
import SuggestTheme from "../components/MainPage/SuggestTheme.vue";

export default {
  components: {
    Banner,
    MainMap,
    SuggestSpot,
    SuggestTheme
  }
};
</script>

<style>
</style>
