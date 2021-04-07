<template>
  <v-container>
    <v-sheet class="mx-auto" elevation="8" max-width="800">
      <template v-if="getSuggestList.length == 0">
        <h1>추천 결과가 없습니다.</h1>
      </template>
      <v-slide-group class="pa-4" active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getSuggestList" :key="idx">
          <v-card
            class="ma-4"
            height="200"
            width="200"
            @click="onSelect(card.storeid)"
          >
            <v-img>
              :src = "card.img"
            </v-img>
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
    // this.reqSuggest(this.getMemberInfo.email); // 이메일 정보를 통해서 추천 List 받아옴.
  },
  computed: {
    ...mapGetters(FindStore, ["getSuggestList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(SpotInfoStore, ["reqSpot"]),
    ...mapActions(FindStore, ["reqSuggest"]),

    onSelect(storeNum) {
      this.reqSpot(storeNum).then(response => {
        if (!response) {
          this.$router.push("/spotdetail");
        } else alert(response.msg);
      });
    }
  }
};
</script>

<style></style>
