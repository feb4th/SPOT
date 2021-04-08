<template>
  <v-row>
    <v-col>
      <v-card elevation="0">
        <v-card-title class="headline justify-space-between">
          <v-row>
            <v-col cols="1" justify="left">
              <v-btn
                icon
                large
                @click="
                  () => {
                    this.$router.go(-1);
                  }
                "
              >
                <v-icon large>mdi-arrow-left</v-icon>
              </v-btn>
            </v-col>
            <v-col cols="10">
              <v-text-field
                style="font-size:30px;"
                justify="center"
                v-model="getCourseInfo[0].course_name"
                filled
              >
              </v-text-field
            ></v-col>
            <v-col cols="1" justify="end"> <v-spacer></v-spacer></v-col>
          </v-row>

          <!-- <v-btn icon large v-if="getIsLogined">
            <v-icon class="kakao-link" large @click="onShare()"
              >mdi-share-variant
            </v-icon>
          </v-btn> -->
        </v-card-title>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { mapGetters } from "vuex";
const CourseStore = "CourseStore";

export default {
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo"])
  },
  methods: {
    onShare() {
      // 현재 페이지 정보 공유
      let url =
        "https://j4a102.p.ssafy.io/course/detail/" +
        this.$route.params.courseid;
      let descr = " 코스 정보가 도착했어요~";
      console.log(url);
      window.Kakao.Link.createDefaultButton({
        container: ".kakao-link",
        objectType: "feed",
        content: {
          title: "SPOT!",
          description: descr,
          imageUrl:
            "https://cdn.crowdpic.net/list-thumb/thumb_l_76F8C1369ECF6B800FBE7BDD5ADE2FB1.jpg",
          link: {
            webUrl: url,
            mobileWebUrl: url
          }
        },
        social: {
          likeCount: 300,
          commentCount: 400,
          sharedCount: 500
        },
        buttons: [
          {
            title: "웹으로 이동",
            link: {
              mobileWebUrl: url,
              webUrl: url
            }
          },
          {
            title: "앱으로 이동",
            link: {
              mobileWebUrl: url,
              webUrl: url
            }
          }
        ]
      });
    }
  }
};
</script>

<style></style>
