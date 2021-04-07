<template>
  <v-container>
    <v-row class="ma-3">
      <v-col>
        <span style="font-size: 2em; font-weight: 900;">🔥추천 SPOT🔥</span>
      </v-col>
    </v-row>
    <v-sheet class="mx-auto" elevation="0" max-width="1000">
      <v-slide-group active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getSuggestList" :key="idx">
          <v-card
            class="ma-4"
            height="200"
            width="200"
            @click="onSelect(card.id)"
          >
            <!-- 이미지 데이터가 없을 때 -->
            <!-- <v-img v-if="card.img == '' || car.img == null"> </v-img> -->
            <v-img
              contain
              aspect-ratio="1"
              :src="card.img"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            >
              <v-card-title>{{ card.name }}</v-card-title>
            </v-img>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const SpotInfoStore = "SpotInfoStore";
const MemberStore = "MemberStore";
const SuggestStore = "SuggestStore";

export default {
  created() {
    this.reqSuggest(this.getMemberInfo.user_id); // 이메일 정보를 통해서 추천 List 받아옴.
  },
  computed: {
    ...mapGetters(SuggestStore, ["getSuggestList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(SpotInfoStore, ["reqSpot"]),
    ...mapActions(SuggestStore, ["reqSuggest"]),

    onSelect(spot_id) {
      this.$router.push("/spotdetail/" + spot_id);
    },
    getSrc(order) {
      return require("../../assets/images/spot/" + order + ".png");
    }
  }
};
</script>

<style></style>
