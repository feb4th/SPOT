<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row style="height:90%;">
          <v-col>
            <v-sheet min-height="70vh" rounded="lg" class="mt-3">
              <v-row class="ml-12">
                <div class="mt-5">
                  <h1>{{ getMemberInfo.nickname }}님에게 추천하는</h1>
                  <span style="font-size: 3em;"><strong>SPOT</strong></span>
                  <span style="font-size: 2.5em;">🚩</span>
                </div></v-row
              >
              <!-- 위시리스트 팝업 부분 -->
              <v-row class="justify-end my-8 mr-12">
                <v-btn color="grey" dark @click.stop="dialog = true">
                  나의 위시리스트
                </v-btn>

                <v-dialog v-model="dialog" max-width="580">
                  <v-card>
                    <v-card-title class="headline">
                      <h3 class="mt-2">나의 위시리스트</h3>
                    </v-card-title>

                    <v-card-text>
                      <v-slide-group
                        class="pa-4"
                        active-class="primary"
                        show-arrows
                      >
                        <v-slide-item
                          v-for="(card, idx) in getWishList"
                          :key="idx"
                        >
                          <div>
                            <v-card
                              class="ma-4"
                              height="100"
                              width="100"
                              @click="onSelect(card.id)"
                            >
                              <!-- 이미지 데이터가 없을 때 -->
                              <!-- 이미지 데이터가 없을 때 -->
                              <v-img
                                v-if="card.img == '' || card.img == null"
                                contain
                                aspect-ratio="1"
                                src="@/assets/logo.png"
                                class="white--text align-end"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                ><v-card-title
                                  class="text-no-wrap text-truncate"
                                  >{{ card.name }}</v-card-title
                                >
                              </v-img>
                              <v-img
                                v-else
                                contain
                                aspect-ratio="1"
                                :src="card.img"
                                class="white--text align-end"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                              >
                                <v-card-title
                                  class="text-no-wrap text-truncate"
                                  >{{ card.name }}</v-card-title
                                ></v-img
                              >
                            </v-card>
                            <v-row justify="center" class="ma-auto">
                              <v-tooltip bottom>
                                <template v-slot:activator="{ on, attrs }">
                                  <v-btn icon v-bind="attrs" v-on="on">
                                    <v-icon
                                      v-if="wishFlag[idx] == true"
                                      size="50"
                                      @click="onWishClick(idx)"
                                      color="primary"
                                      >mdi-playlist-check</v-icon
                                    >
                                    <v-icon
                                      v-else
                                      size="40"
                                      @click="onWishUnclick(idx)"
                                      color="gray"
                                      >mdi-check</v-icon
                                    >
                                  </v-btn>
                                </template>
                                <span v-if="wishFlag[idx] == true">취소</span>
                                <span v-else>추가</span>
                              </v-tooltip>
                            </v-row>
                            <!-- 왜 짤리는 거지... 세로로 크기 조정 필요 -->
                          </div>
                        </v-slide-item>
                      </v-slide-group>
                    </v-card-text>

                    <v-card-actions>
                      <v-spacer></v-spacer>

                      <v-btn color="secondary" text @click="dialog = false">
                        확인
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>

              <!-- 추천 지역 list -->
              <v-row style="height:80%;">
                <v-col cols="1"></v-col>
                <v-col cols="10">
                  <v-row>
                    <v-card
                      v-for="(card, idx) in getSuggestTourList"
                      :key="idx"
                      height="150"
                      width="150"
                      class="mx-auto my-8"
                    >
                      <v-img
                        contain
                        aspect-ratio="1"
                        :src="getSrc(card.img)"
                        class="white--text align-end"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        @click="onSelect(card.id)"
                      >
                        <v-card-title> {{ card.name }} </v-card-title></v-img
                      ><v-row justify="center" class="my-3">
                        <v-tooltip bottom>
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn icon v-bind="attrs" v-on="on">
                              <v-icon
                                v-if="suggestFlag[idx] == true"
                                size="50"
                                @click="onSuggestClick(idx)"
                                color="primary"
                                >mdi-playlist-check</v-icon
                              >
                              <v-icon
                                v-else
                                size="40"
                                @click="onSuggestUnclick(idx)"
                                color="gray"
                                >mdi-check</v-icon
                              >
                            </v-btn>
                          </template>
                          <span v-if="suggestFlag[idx] == true">취소</span>
                          <span v-else>추가</span>
                        </v-tooltip>
                      </v-row>
                    </v-card>
                  </v-row>
                </v-col>
                <v-col cols="1" class="ma-auto"
                  ><v-btn depressed icon v-if="btnNumber > 0" @click="onClick"
                    ><v-icon x-large>mdi-arrow-right</v-icon></v-btn
                  ></v-col
                >
              </v-row>

              <v-row style="height:50px;" class="mb-2"></v-row>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const SuggestStore = "SuggestStore";
const MemberStore = "MemberStore";
const CourseStore = "CourseStore";
const WishStore = "WishStore";
const SpotInfoStore = "SpotInfoStore";
export default {
  data() {
    return {
      dialog: false,
      btnNumber: 0,
      suggestFlag: [],
      wishFlag: [],
      courseRoom: []
    };
  },
  created() {
    this.reqWishList(this.getMemberInfo.user_id); // 유저 아이디를 통해서 위시List 받아옴.
  },
  computed: {
    ...mapGetters(SuggestStore, ["getSuggestTourList"]), // 추천이랑 위시리스트 들고옴.
    ...mapGetters(WishStore, ["getWishList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(WishStore, ["reqWishList"]),
    ...mapActions(CourseStore, ["reqCreateCourse", "reqCreate"]),
    ...mapActions(SpotInfoStore, ["reqSpot", "reqTourSight"]),

    onSelect(spot_id) {
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
    onClick() {
      // 위시리스트와 추천리스트에서 선택한 것만 리스트에 넣어줌.
      let tmpIdx = 1;
      for (var i = 0; i < this.getSuggestTourList.length; i++) {
        if (this.suggestFlag[i] == true) {
          const tmp = this.getSuggestTourList[i];
          this.courseRoom[tmpIdx - 1] = {
            course_id: "0",
            course_name: "새로운 코스",
            date: "0",
            memo: "0",
            name: tmp.name,
            orders: tmpIdx,
            spot_id: tmp.id,
            time: "0",
            type: "0",
            latitude: tmp.latitude,
            longitude: tmp.longitude,
            user_id: this.getMemberInfo.user_id
          };
          tmpIdx++;
        }
      }

      for (i = 0; i < this.getWishList.length; i++) {
        if (this.wishFlag[i] == true) {
          const tmp = this.getWishList[i];
          this.courseRoom[tmpIdx - 1] = {
            course_id: "0",
            course_name: "새로운 코스",
            date: "0",
            memo: "0",
            name: tmp.name,
            orders: tmpIdx,
            spot_id: tmp.id,
            time: "0",
            type: "0",
            latitude: tmp.latitude,
            longitude: tmp.longitude,
            user_id: this.getMemberInfo.user_id
          };
          tmpIdx++;
        }
      }

      // 여기서 선택한 곳만 리스트로 보내고, 코스 선택으로 넘겨야 함.
      // 여기부터 작업하면 됨. 코스로 보내는 곳

      console.log(this.courseRoom);
      const tt = JSON.parse(JSON.stringify(this.courseRoom));
      console.log(tt);
      this.reqCreate(tt).then(res => {
        if (res.msg) {
          console.log(res);
          this.$router.push("/makecourse1/" + res.result);
        } else alert(res.msg);
      });
    },
    onSuggestClick(idx) {
      this.suggestFlag[idx] = false;
      this.btnNumber--;
    },
    onSuggestUnclick(idx) {
      this.suggestFlag[idx] = true;
      this.btnNumber++;
    },
    onWishClick(idx) {
      this.wishFlag[idx] = false;
      this.btnNumber--;
    },
    onWishUnclick(idx) {
      this.wishFlag[idx] = true;
      this.btnNumber++;
    },
    getSrc(img) {
      return img;
    }
  }
};
</script>

<style></style>
