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
    <v-row v-else>
      <v-col
        cols="3"
        justify="center"
        v-for="spot in getWishList"
        :key="spot.wishlist_id"
        class="pa-5"
      >
        <v-card>
          <!-- 이미지 데이터가 없을 때 -->
          <v-img
            v-if="spot.img == null || spot.img == ''"
            src="../../assets/logo.png"
            contain
            max-height="150"
            style="cursor: pointer"
            @click="onSpot(spot.id)"
          >
          </v-img>

          <!-- 이미지 데이터가 있을 때 -->
          <v-img
            v-else
            contain
            max-height="150"
            :src="spot.img"
            style="cursor: pointer"
            @click="onSpot(spot.id)"
          ></v-img>
          <v-btn block text
            ><h3>{{ spot.name }}</h3></v-btn
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
const SpotInfoStore = "SpotInfoStore";
export default {
  created() {
    this.reqWishList(this.$route.params.memberid);
  },
  computed: {
    ...mapGetters(WishStore, ["getWishList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },

  methods: {
    ...mapActions(WishStore, ["reqWishList"]),
    ...mapActions(SpotInfoStore, ["reqSpot", "reqTourSight"]),

    onSpot(spot_id) {
      if (spot_id < 500000) {
        this.reqSpot(spot_id).then(response => {
          if (response.result == true) {
            this.$router.push("/spotdetail/" + spot_id);
          }
        });
      } else {
        this.reqTourSight(spot_id).then(response => {
          if (response.result == true) {
            this.$router.push("/spotdetail/" + spot_id);
          }
        });
      }
    }
  }
};
</script>

<style></style>
