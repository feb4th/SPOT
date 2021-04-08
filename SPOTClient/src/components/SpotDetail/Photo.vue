<template>
  <v-container>
    <v-img
      :src="getImage[0]"
      height="400"
      contain
      v-if="this.$route.params.spotid >= 500000"
    ></v-img>
    <v-img :src="getSpot.img" height="400" contain v-else> </v-img>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
const SpotInfoStore = "SpotInfoStore";
export default {
  computed: {
    ...mapGetters(SpotInfoStore, ["getImage", "getSpot"])
  },
  methods: {
    setimage(ab) {
      console.log(ab);
      if (this.$route.params.spotid < 50000) return ab;
      else return "http:/" + ab;
    }
  }
};
</script>

<style></style>
