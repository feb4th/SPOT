<template>
  <v-container>
    <!-- 위시리스트가 없을 때 -->
    <v-row justify="center" v-if="getWishList.length == 0">
      <v-col cols="9">
        <v-spacer />
        <v-alert
          align="center"
          border="bottom"
          colored-border
          type="warning"
          elevation="2"
        >
          <div class="title">저장된 SPOT이 없습니다</div>
          <div>SPOT을 저장해 보세요!</div>
        </v-alert>
        <v-spacer />
      </v-col>
    </v-row>

    <!-- 위시리스트가 존재할 때 -->
    <v-row
      justify="center"
      v-for="spot in getWishList"
      :key="spot.wishlist_id"
      v-else
    >
      <v-col cols="2">
        <v-card class="ma-5">
          <!-- 이미지 데이터가 없을 때 -->
          <v-img
            v-if="spot.imgdata == null || spot.imgdata == ''"
            src="../../assets/logo.png"
            style="cursor: pointer"
            @click="onSpot(spot.spot_id)"
          >
          </v-img>

          <!-- 이미지 데이터가 있을 때 -->
          <v-img
            v-else
            :src="spot.imgdata"
            style="cursor: pointer"
            @click="onSpot(spot.spot_id)"
          ></v-img>
          <v-btn x-large block text
            ><h2>{{ spot.name }}</h2></v-btn
          >
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const WishStore = "WishStore";
const MemberStore = "MemberStore";

export default {
  created() {
    this.reqWishList(this.$route.params.email);
  },
  computed: {
    ...mapGetters(WishStore, ["getWishList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },

  methods: {
    ...mapActions(WishStore, ["reqWishList"]),

    onSpot(id) {
      this.$router.push("/spot/detail/" + id);
    }
  }
};
</script>

<style></style>
