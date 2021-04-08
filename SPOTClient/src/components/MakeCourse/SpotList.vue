<template>
  <v-container class="pa-auto">
    <!-- 카드들 -->
    <draggable v-model="list" class="container">
      <v-row justify="center" v-for="(course, idx) in list" :key="idx">
        <v-col cols="1" class="my-auto">
          <v-btn icon x-large>
            <v-icon>mdi-menu</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="6">
          <v-card class="my-3 rounded-tl-xl rounded-br-xl">
            <v-row class="px-10" align="center">
              <v-col cols="3">
                <!-- order에 따른 이미지로 수정 예정 -->
                <v-img :src="getSrc(idx)" contain></v-img>
              </v-col>
              <v-col cols="9">
                <v-btn
                  x-large
                  text
                  @click="onDetail(course.spot_id)"
                  class="text-start text-truncate"
                  ><h2 style="max-width:150px">{{ course.name }}</h2></v-btn
                ></v-col
              >
            </v-row>
          </v-card>
        </v-col>
      </v-row>
    </draggable>
    <!-- 버튼들 -->
    <v-row justify="center" class="mt-8 mb-3">
      <v-col cols="auto">
        <v-btn color="secondary" large @click="onSave()"
          ><h3 style="color:white;">코스 생성하기</h3></v-btn
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
const CourseStore = "CourseStore";
const MemberStore = "MemberStore";
const SpotInfoStore = "SpotInfoStore";

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
    ...mapMutations(CourseStore, ["setCourseInfo"]),
    ...mapActions(CourseStore, [
      "reqCourseInfo",
      "reqChangeStatus",
      "reqModifyCourse"
    ]),
    ...mapActions(SpotInfoStore, ["reqSpot", "reqTourSight"]),

    onDetail(spot_id) {
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
    },
    onSave() {
      //수정 정보 저장
      const tmpCourseName = this.getCourseInfo[0].course_name;
      //console.log(tmpCourseName);
      for (var i = 0; i < this.getCourseInfo.length; i++) {
        this.list[i].course_name = tmpCourseName;
        //console.log(this.list[i].course_name);
      }
      axios
        .put("/course/" + this.$route.params.courseid, this.list)
        .then(response => {
          console.log(response);
          this.setCourseInfo(this.list);
          alert("코스 등록 완료하였습니다!");

          this.$router.push("/mypage/" + this.getMemberInfo.user_id);
        })
        .catch(error => {
          console.log(error);
        });
    },
    getSrc(order) {
      return require("../../assets/numbers/" + order + ".png");
    }
  }
};
</script>

<style></style>
