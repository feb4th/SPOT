<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12" sm="2"> </v-col>

          <v-col cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <v-row>
                <profile />
              </v-row>
              <v-row class="justify-center ma-2">
                <v-col cols="6"
                  ><v-btn large text block @click="ifcourse = true"
                    >코스 이력</v-btn
                  >
                  <v-divider v-if="ifcourse"></v-divider
                ></v-col>
                <v-col cols="6"
                  ><v-btn large text block @click="ifcourse = false"
                    >위시리스트</v-btn
                  >
                  <v-divider v-if="!ifcourse"></v-divider
                ></v-col>
              </v-row>
              <v-row>
                <course-list v-if="ifcourse" />

                <wish-list v-if="!ifcourse" />
              </v-row>
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="2"> </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Profile from "../components/MyPage/Profile.vue";
import CourseList from "../components/MyPage/CourseList.vue";
import WishList from "../components/MyPage/WishList.vue";

export default {
  data: () => {
    return {
      ifcourse: true
    };
  },
  components: {
    Profile,
    CourseList,
    WishList
  }
};
</script>

<style></style>
