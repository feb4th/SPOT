<template>
  <v-row>
    <v-col>
      <v-card class="pu-10 px-10 mu-10" elevation="0">
        <v-card-title class="headline justify-space-between">
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

          {{ getCourseInfo[0].course_name }}
          <v-btn icon large v-if="getIsLogined">
            <v-icon class="kakao-link" large @click="onShare()"
              >mdi-share-variant
            </v-icon>
          </v-btn>
        </v-card-title>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { mapGetters } from "vuex";
const CourseStore = "CourseStore";
const MemberStore = "MemberStore";

export default {
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo"]),
    ...mapGetters(MemberStore, ["getIsLogined"])
  },
  methods: {
    onShare() {
      // 현재 페이지 정보 공유
      let url =
        "https://localhost:8080/course/detail/" + this.$route.params.course_id;
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
