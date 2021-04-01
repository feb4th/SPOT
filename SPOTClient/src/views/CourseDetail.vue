<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12" sm="2"> </v-col>

          <v-col cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <!--  -->
              <banner />
              <course-map />
              <spot-list />
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="2"> </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Banner from "../components/CourseDetail/Banner.vue";
import CourseMap from "../components/CourseDetail/Map.vue";
import SpotList from "../components/CourseDetail/SpotList.vue";

export default {
  components: {
    Banner,
    CourseMap,
    SpotList
  }
};
</script>

<style></style>
