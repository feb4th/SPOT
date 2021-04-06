<template>
  <v-container>
    <v-card elevation="0">
      <v-card-title class="justify-end">
        <v-tooltip bottom nudge-bottom="20">
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon
                v-if="getWishId != ''"
                size="50"
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
    let formData = new FormData();
    formData.append("store_id", this.$route.params.storeid);
    formData.append("email", this.getMemberInfo.email);
    this.reqCheckWish(formData);
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
      let formData = new FormData();
      formData.append("store_id", this.$route.params.storeid);
      formData.append("email", this.getMemberInfo.email);
      formData.append("type", this.getSpot.type);
      this.reqCheckWish(formData);
    }
  }
};
</script>

<style></style>
