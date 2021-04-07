<template>
  <v-container>
    <!-- 코스이력이 없을 때 -->
    <v-row justify="center" v-if="getCourseList.length == 0">
      <v-col cols="9">
        <v-spacer />
        <v-alert
          align="center"
          border="bottom"
          colored-border
          type="warning"
          elevation="2"
        >
          <div class="title">생성된 코스가 없습니다.</div>
          <div>새로운 코스를 만들어 보세요!</div>
        </v-alert>
        <v-spacer />
      </v-col>
    </v-row>

    <!-- 코스이력이 존재할 때 -->
    <v-row
      justify="center"
      v-for="course in getCourseList"
      :key="course.course_id"
      v-else
    >
      <v-col cols="9">
        <v-card
          class="mb-5 rounded-tl-xl rounded-br-xl"
          color="rgba(255, 255, 255, 0.9)"
        >
          <v-btn x-large block text @click="onCourse(course.course_id)"
            ><h2>{{ course.course_name }}</h2></v-btn
          >
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const CourseStore = "CourseStore";
const MemberStore = "MemberStore";

export default {
  created() {
    this.reqCourseList(this.$route.params.memberid);
  },
  computed: {
    ...mapGetters(CourseStore, ["getCourseList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },

  methods: {
    ...mapActions(CourseStore, ["reqCourseList"]),

    onCourse(id) {
      this.$router.push("/course/detail/" + id);
    }
  }
};
</script>

<style></style>
