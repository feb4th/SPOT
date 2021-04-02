<template>
  <v-container class="pa-auto">
    <!-- 카드들 -->
    <v-row
      justify="center"
      v-for="course in getCourseInfo"
      :key="course.course_id"
    >
      <v-col cols="6">
        <v-card class="my-5 rounded-tl-xl rounded-br-xl">
          <v-row class="px-10" align="center">
            <v-col cols="3">
              <!-- order에 따른 이미지로 수정 예정 -->
              <v-btn>{{ course.spot_id }}</v-btn>
            </v-col>
            <v-col cols="3">
              <v-btn x-large text @click="onDetail(course.spot_id)"
                ><h2>{{ course.name }}</h2></v-btn
              ></v-col
            >
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <!-- 버튼들 -->
    <v-row justify="center">
      <v-col cols="auto">
        <v-btn icon v-if="!getModify" @click="onModify()"
          ><v-icon x-large>mdi-pencil</v-icon></v-btn
        >
        <v-btn v-else icon @click="onSave()"
          ><v-icon x-large>mdi-check</v-icon></v-btn
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const CourseStore = "CourseStore";

export default {
  created() {
    this.reqCourseInfo(this.$route.params.course_id);
  },
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo", "getModify"])
  },
  methods: {
    ...mapActions(CourseStore, ["reqCourseInfo", "setModify"]),
    onDetail(spot_id) {
      this.$router.push("/spotdetail/" + spot_id);
    },
    onModify() {
      //코스 정보 수정
      this.setModify();
    },
    onSave() {
      //수정 정보 저장
      return true;
    }
  }
};
</script>

<style></style>
