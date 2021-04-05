<template>
  <v-container class="pa-auto">
    <!-- 카드들 -->
    <v-row justify="center" v-for="(course, idx) in getCourseInfo" :key="idx">
      <v-col cols="6">
        <v-card class="my-5 rounded-tl-xl rounded-br-xl">
          <v-row class="px-10" align="center">
            <v-col cols="3">
              <!-- order에 따른 이미지로 수정 예정 -->
              <v-img :src="getSrc(idx)" contain></v-img>
            </v-col>
            <v-col cols="3">
              <v-btn x-large text @click="onDetail(course.spot_id)">
                <h2>{{ course.name }}</h2>
              </v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <v-row class="ma-auto" justify="center" v-if="getIsLogined">
      <v-col cols="auto">
        <v-btn x-large icon @click="onModify()"
          ><v-icon>mdi-pencil</v-icon></v-btn
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const CourseStore = "CourseStore";
const MemberStore = "MemberStore";

export default {
  data() {
    return {
      dialog: false
    };
  },
  created() {
    this.reqCourseInfo(this.$route.params.course_id);
  },
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo"]),
    ...mapGetters(MemberStore, ["getIsLogined"])
  },
  methods: {
    ...mapActions(CourseStore, ["reqCourseInfo"]),

    onDetail(spot_id) {
      this.$router.push("/spotdetail/" + spot_id);
    },
    onModify() {
      //this.$router.push("/makecourse/"+this.$route.params.course_id);
    },
    getSrc(order) {
      return require("../../assets/numbers/" + order + ".png");
    }
  }
};
</script>

<style></style>
