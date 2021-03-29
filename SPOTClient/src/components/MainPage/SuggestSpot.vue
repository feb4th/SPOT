<template>
  <v-container>
    <v-row class="mx-auto">
      <v-col>
        <h2>추천SPOT</h2>
      </v-col>
    </v-row>
    <v-sheet class="mx-auto" elevation="0" max-width="1000">
      <v-slide-group class="pa-4" active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getSuggestList" :key="idx">
          <v-card
            class="ma-4"
            height="200"
            width="200"
            @click="onSelect(card.spot_id)"
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
import { mapActions, mapGetters } from "vuex";
const FindStore = "FindStore";
const SpotInfoStore = "SpotInfoStore";
const MemberStore = "MemberStore";

export default {
  created() {
    this.reqSuggest(this.getMemberInfo.email); // 이메일 정보를 통해서 추천 List 받아옴.
  },
  computed: {
    ...mapGetters(FindStore, ["getSuggestList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(SpotInfoStore, ["reqSpot"]),
    ...mapActions(FindStore, ["reqSuggest"]),

    onSelect(spot_id) {
      this.$router.push("/spotdetail/" + spot_id);
    }
  }
};
</script>

<style></style>
