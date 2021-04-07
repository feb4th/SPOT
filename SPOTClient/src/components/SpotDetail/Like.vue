<template>
  <v-container>
    <v-card elevation="0">
      <v-card-title class="justify-end">
        <v-tooltip bottom nudge-bottom="20">
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon
                v-if="getWishId != ''"
                size="60"
                @click="unFollow()"
                color="red"
                >mdi-heart
              </v-icon>
              <v-icon v-else size="50" @click="setFollow()" color="gray"
                >mdi-heart
              </v-icon>
            </v-btn>
          </template>
          <span v-if="getWishId != ''">위시리스트 취소</span>
          <span v-else>위시리스트 추가하기</span>
        </v-tooltip>
      </v-card-title>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const WishStore = "WishStore";
const MemberStore = "MemberStore";
const SpotInfoStore = "SpotInfoStore";

export default {
  data() {
    return {
      color: ""
    };
  },
  created() {
    this.reqCheckWish({
      spot_id: this.$route.params.spotid,
      user_id: this.getMemberInfo.user_id,
      name: this.getSpot.name,
      type: "0"
    });
  },
  computed: {
    ...mapGetters(WishStore, ["getWishId"]),
    ...mapGetters(MemberStore, ["getMemberInfo"]),
    ...mapGetters(SpotInfoStore, ["getSpot"])
  },
  methods: {
    ...mapActions(WishStore, ["reqCheckWish", "reqAddWish", "reqDeleteWish"]),
    unFollow() {
      this.reqDeleteWish(this.getWishId);
    },
    setFollow() {
      this.reqAddWish({
        spot_id: this.$route.params.spotid,
        user_id: this.getMemberInfo.user_id,
        name: this.getSpot.name
      });
    }
  }
};
</script>

<style></style>
