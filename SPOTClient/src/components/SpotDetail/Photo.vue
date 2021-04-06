<template>
  <v-container>
    <v-window show-arrows class="ma-10">
      <v-window-item v-for="(image, idx) in getImage" :key="idx">
        <v-card color="grey">
          <v-row align="center" justify="center">
            <v-img contain :src="setimage(image)" height="400"></v-img>
          </v-row>
        </v-card>
      </v-window-item>
    </v-window>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
const SpotInfoStore = "SpotInfoStore";
export default {
  created() {
    //console.log(this.getImage[0]);
  },
  computed: {
    ...mapGetters(SpotInfoStore, ["getImage"])
  },
  methods: {
    setimage(ab) {
      console.log(ab.substring(1));
      return ab.substring(1);
    }
  }
};
</script>

<style></style>
