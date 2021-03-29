<template>
  <v-container>
    <v-row class="mx-auto">
      <v-col>
        <h2>추천테마</h2>
      </v-col>
    </v-row>
    <v-sheet class="mx-auto" elevation="0" max-width="1000">
      <v-slide-group class="pa-4" active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getThemeList" :key="idx">
          <v-card
            class="ma-4"
            height="200"
            width="200"
            @click="onSelect(card.theme_id)"
          >
            <!-- 이미지 데이터가 없을 때 -->
            <v-img v-if="card.img == '' || car.img == null"> </v-img>
            <v-img v-else :src="card.img"> </v-img>

            <v-card-title class="text-no-wrap text-truncate">{{
              card.name
            }}</v-card-title>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const ThemeStore = "ThemeStore";

export default {
  created() {
    this.reqThemeList();
  },
  computed: {
    ...mapGetters(ThemeStore, ["getThemeList"])
  },
  methods: {
    ...mapActions(ThemeStore, ["reqThemeList"]),

    onSelect(theme_id) {
      this.$router.push("/spotdetail/" + theme_id);
    }
  }
};
</script>

<style></style>
