<template>
  <v-container>
    <v-card class="pu-10 px-10 mu-10" elevation="0">
      <v-card-title class="headline justify-end">
        <v-tooltip bottom nudge-bottom="20">
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon :color="color" v-bind="attrs" v-on="on">
              <v-icon
                v-if="member.follow"
                size="120"
                @click="unFollow()"
                color="red"
                >mdi-heart</v-icon
              >
              <v-icon v-else size="100" @click="setFollow()" color="gray"
                >mdi-heart</v-icon
              >
            </v-btn>
          </template>
          <span v-if="!member.follow">밴드 팔로우하기</span>
          <span v-else>밴드 팔로우취소</span>
        </v-tooltip>
      </v-card-title>
    </v-card>
  </v-container>
</template>

<script>
export default {};
</script>

<style></style>
