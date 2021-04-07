<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row style="height:100%;">
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <div class="ml-3">
                <h1>당신에게 추천하는</h1>
                <span style="font-size: 3em;"><strong>SPOT</strong></span>
                <span style="font-size: 2.5em;">🚩</span>
              </div>
              <!-- 위시리스트 팝업 부분 -->
              <v-row class="justify-end mr-8">
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
                              height="200"
                              width="200"
                              @click="onSelect(card.spot_id)"
                            >
                              <!-- 이미지 데이터가 없을 때 -->
                              <v-img v-if="card.img == '' || card.img == null">
                              </v-img>
                              <v-img v-else :src="card.img"> </v-img>

                              <v-card-title
                                class="text-no-wrap text-truncate"
                                >{{ card.name }}</v-card-title
                              >
                            </v-card>
                            <v-row justify="center" class="ma-auto">
                              <v-tooltip bottom>
                                <template v-slot:activator="{ on, attrs }">
                                  <v-btn
                                    icon
                                    :color="color"
                                    v-bind="attrs"
                                    v-on="on"
                                  >
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
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>

              <!-- 추천 지역 list -->
              <v-row style="height:90%;">
                <v-col cols="1"></v-col>
                <v-col cols="10">
                  <v-sheet class="mx-auto" elevation="0" max-width="1000">
                    <v-slide-group
                      class="pa-4"
                      active-class="success"
                      show-arrows
                    >
                      <v-slide-item
                        v-for="(card, idx) in getSuggestTourList"
                        :key="idx"
                      >
                        <div>
                          <v-card
                            class="ma-4"
                            height="180"
                            width="200"
                            @click="onSelect(card.id)"
                          >
                            <!-- 이미지 데이터가 없을 때 -->
                            <v-img
                              v-if="card.img == '' || card.img == null"
                              contain
                              aspect-ratio="1.1"
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
                              aspect-ratio="1.1"
                              :src="getSrc(card.img)"
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
                                <v-btn
                                  icon
                                  :color="color"
                                  v-bind="attrs"
                                  v-on="on"
                                >
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
                        </div>
                      </v-slide-item>
                    </v-slide-group>
                  </v-sheet>
                </v-col>
                <v-col cols="1" class="ma-auto"
                  ><v-btn depressed icon v-if="btnNumber > 0" @click="onClick"
                    ><v-icon x-large>mdi-arrow-right</v-icon></v-btn
                  ></v-col
                >
                <!-- <v-col cols="1" class="ma-auto">
                  <v-btn depressed :disabled="btnNumber <= 0" @click="onClick">
                    다음
                  </v-btn></v-col
                > -->
              </v-row>
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

export default {
  data() {
    return {
      dialog: false,
      btnNumber: 0,
      suggestFlag: [],
      wishFlag: []
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

    onSelect(id) {
      this.$router.push("/spotdetail/" + id); // 상세 보기.
    },
    onClick() {
      // 위시리스트와 추천리스트에서 선택한 것만 리스트에 넣어줌.
      let formData = new FormData();
      let tmpIdx = 0;
      for (var i = 0; i < this.getSuggestTourList.length; i++) {
        if (this.suggestFlag[i] == true) {
          const tmp = this.getSuggestTourList[i];
          let formData2 = new FormData();
          formData2.append("course_id", 0);
          formData2.append("date", 0);
          formData2.append("memo", 0);
          formData2.append("name", tmp.name);
          formData2.append("orders", tmpIdx++);
          formData2.append("spot_id", tmp.spot_id);
          formData2.append("time", 0);
          formData2.append("type", 0);
          formData2.append("user_id", this.getMemberInfo.user_id);
          formData.append("", formData2);
        }
      }

      for (i = 0; i < this.getWishList.length; i++) {
        if (this.wishFlag[i] == true) {
          const tmp = this.wishFlag[i];
          let formData2 = new FormData();
          formData2.append("course_id", 0);
          formData2.append("date", 0);
          formData2.append("memo", 0);
          formData2.append("name", tmp.name);
          formData2.append("orders", tmpIdx++);
          formData2.append("spot_id", tmp.spot_id);
          formData2.append("time", 0);
          formData2.append("type", 0);
          formData2.append("user_id", this.getMemberInfo.user_id);
          formData.append("", formData2);
        }
      }

      // 여기서 선택한 곳만 리스트로 보내고, 코스 선택으로 넘겨야 함.
      // 여기부터 작업하면 됨. 코스로 보내는 곳

      this.reqCreateCourse(this.getMemberInfo.user_id).then(response => {
        // 아이디로 코스 만듦
        const courseNum = response.result;
        this.reqCreate(courseNum, formData).then(res => {
          if (res.result) {
            this.$router.push("/makecourse");
            console.log(res);
          } else alert(response.msg);
        });
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
      this.wish[idx] = true;
      this.btnNumber++;
    },
    getSrc(img) {
      return "http:/" + img;
    }
  }
};
</script>

<style></style>
