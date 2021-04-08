<template>
  <v-container class="pa-auto">
    <!-- 카드들 -->
    <draggable v-model="list" class="container">
      <v-row justify="center" v-for="(course, idx) in list" :key="idx">
        <v-col cols="1" class="my-auto mr-5">
          <v-btn icon x-large>
            <v-icon>mdi-menu</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="6">
          <v-card class="my-5 rounded-tl-xl rounded-br-xl">
            <v-row class="px-10" align="center">
              <v-col cols="3">
                <!-- order에 따른 이미지로 수정 예정 -->
                <v-btn>{{ idx }}</v-btn>
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
        <v-btn icon @click="onSave()"><v-icon x-large>mdi-check</v-icon></v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const CourseStore = "CourseStore";
const MemberStore = "MemberStore";

import draggable from "vuedraggable";
import axios from "../../axios/axios-common";

export default {
  data() {
    return {
      list: []
    };
  },
  created() {
    this.list = this.getCourseInfo;
  },
  components: {
    draggable
  },
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
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
    onSave() {
      //수정 정보 저장
      const tmpCourseName = this.getCourseInfo[0].course_name;
      for (var i = 1; i < this.getCourseInfo.length; i++) {
        this.getCourseInfo[i].course_name = tmpCourseName;
      }
      axios
        .put("/course/" + this.$route.params.courseid, this.list)
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

<style></style>
