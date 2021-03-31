<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12" sm="2"> </v-col>

          <v-col cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <!--  -->
              <v-card class="pu-10 px-10 mu-10" elevation="0">
                <v-card-title class="headline justify-space-between">
                  <v-btn
                    class="ml-4"
                    icon
                    large
                    @click="
                      () => {
                        this.$router.go(-1);
                      }
                    "
                  >
                    <v-icon size="50">mdi-undo-variant</v-icon>
                  </v-btn>

                  {{ getSpot.name }}

                  <v-btn disabled text><v-spacer></v-spacer></v-btn
                ></v-card-title>
              </v-card>
              <photo />
              <spot-map />
              <like />
              <detail />
              <review />
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="2"> </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Detail from "../components/SpotDetail/Detail.vue";
import Like from "../components/SpotDetail/Like.vue";
import SpotMap from "../components/SpotDetail/Map.vue";
import Photo from "../components/SpotDetail/Photo.vue";
import Review from "../components/SpotDetail/Review.vue";

import { mapGetters, mapActions } from "vuex";
const SpotInfoStore = "SpotInfoStore";

export default {
  components: {
    Photo,
    SpotMap,
    Like,
    Detail,
    Review
  },
  created() {
    this.reqSpot(this.$route.params.spotid);
  },
  computed: {
    ...mapGetters(SpotInfoStore, ["getSpot"])
  },
  methods: {
    ...mapActions(SpotInfoStore, ["reqSpot"])
  }
};
</script>

<style></style>
