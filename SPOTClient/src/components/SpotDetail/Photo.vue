<template>
  <v-container>
    <v-window show-arrows class="ma-10">
      <v-window-item v-for="image in getImage" :key="image.image_id">
        <v-card color="grey">
          <v-row align="center" justify="center">
            <v-img contain src="../../assets/logo.png" height="200"></v-img>
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
  computed: {
    ...mapGetters(SpotInfoStore, ["getImage"])
  }
};
</script>

<style></style>
