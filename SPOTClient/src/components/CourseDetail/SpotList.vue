<template>
  <v-container class="pa-auto">
    <!-- 카드들 -->
    <draggable v-model="getCourseInfo" class="container">
      <v-row justify="center" v-for="(course, idx) in getCourseInfo" :key="idx">
        <v-col cols="1" class="my-auto mr-5" v-if="getModify">
          <v-btn icon x-large>
            <v-icon>mdi-menu</v-icon>
          </v-btn>
        </v-col>
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
    </draggable>
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

import draggable from "vuedraggable";

export default {
  created() {
    this.reqCourseInfo(this.$route.params.course_id);
  },
  components: {
    draggable
  },
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo", "getModify"])
  },
  methods: {
    ...mapActions(CourseStore, [
      "reqCourseInfo",
      "reqChangeStatus",
      "reqModifyCourse"
    ]),
    onDetail(spot_id) {
      this.$router.push("/spotdetail/" + spot_id);
    },
    onModify() {
      //코스 정보 수정
      this.reqChangeStatus();
    },
    onSave() {
      //수정 정보 저장
      let formData = new FormData();
      formData.append("course_id", this.getCourseInfo[0].course_id);
      formData.append("course_name", this.getCourseInfo[0].course_name);
      this.reqModifyCourse(formData);
    }
  }
};
</script>

<style></style>
