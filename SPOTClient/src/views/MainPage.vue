<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <banner />
              <suggest-theme />
              <suggest-spot />
              <main-map class="mt-8" />
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
