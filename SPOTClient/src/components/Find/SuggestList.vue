<template>
  <v-container>
    <v-sheet class="mx-auto" max-width="800">
      <h1 v-if="getSuggestList.length == 0" justify="center" align="center">
        추천 결과가 없습니다.
      </h1>
      <v-row v-else class="ml-12">
        <div>
          <h1>{{ getMemberInfo.nickname }}님!</h1>
          <h1>이 SPOT은 어때요?🤔</h1>
        </div></v-row
      >
      <v-row class="mx-12 my-5">
        <v-card
          v-for="(card, idx) in getSuggestList"
          :key="idx"
          height="150"
          width="150"
          class="mx-3 my-3"
          @click="onSelect(card.id)"
        >
          <v-img
            contain
            aspect-ratio="1"
            :src="getSrc(card.img)"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          >
            <v-card-title> {{ card.name }} </v-card-title></v-img
          >
        </v-card>
      </v-row>
    </v-sheet>
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
const FindStore = "FindStore";
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
    ...mapActions(FindStore, ["reqSuggest"]),

    onSelect(spot_id) {
      this.$router.push("/spotdetail/" + spot_id);
    },
    getSrc(order) {
      // console.log(order);
      // return require("../../assets/images/spot/" + order + ".png");
      return order;
    }
  }
};
</script>

<style></style>
