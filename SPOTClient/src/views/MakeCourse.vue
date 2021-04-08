<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col class="mt-3">
            <v-sheet min-height="70vh" rounded="lg">
              <!--  -->
              <banner />
              <course-map />
              <spot-list />
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Banner from "../components/MakeCourse/CourseBanner.vue";
import CourseMap from "../components/MakeCourse/CourseMap.vue";
import SpotList from "../components/MakeCourse/SpotList.vue";

export default {
  components: {
    Banner,
    CourseMap,
    SpotList
  }
};
</script>

<style></style>
