<template>
  <v-container>
    <v-row class="mx-auto">
      <v-col>
        <h2>선택한 지역 SPOT</h2>
      </v-col>
    </v-row>
    <!-- 위시리스트 팝업 부분 -->
    <v-row justify="end">
      <v-btn color="primary" dark @click.stop="dialog = true">
        위시리스트에서 가져오기
      </v-btn>

      <v-dialog v-model="dialog" max-width="580">
        <v-card>
          <v-card-title class="headline">
            위시리스트
          </v-card-title>

          <v-card-text>
            <v-slide-group class="pa-4" active-class="success" show-arrows>
              <v-slide-item v-for="(card, idx) in getWishList" :key="idx">
                <div>
                  <v-card
                    class="ma-4"
                    height="200"
                    width="200"
                    @click="onSelect(card.spot_id)"
                  >
                    <!-- 이미지 데이터가 없을 때 -->
                    <v-img v-if="card.img == '' || car.img == null"> </v-img>
                    <v-img v-else :src="card.img"> </v-img>

                    <v-card-title class="text-no-wrap text-truncate">{{
                      card.name
                    }}</v-card-title>
                  </v-card>
                  <v-row justify="center" class="ma-auto">
                    <v-tooltip bottom>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn icon :color="color" v-bind="attrs" v-on="on">
                          <v-icon
                            v-if="wishFlag[idx] == true"
                            size="60"
                            @click="wishFlag[idx] = false"
                            color="red"
                            >mdi-heart</v-icon
                          >
                          <v-icon
                            v-else
                            size="40"
                            @click="wishFlag[idx] = true"
                            color="gray"
                            >mdi-heart</v-icon
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

            <v-btn color="green darken-1" text @click="dialog = false">
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- 추천 지역 list -->
    <v-sheet class="mx-auto" elevation="0" max-width="1000">
      <v-slide-group class="pa-4" active-class="success" show-arrows>
        <v-slide-item v-for="(card, idx) in getSuggestList" :key="idx">
          <div>
            <v-card
              class="ma-4"
              height="180"
              width="200"
              @click="onSelect(card.spot_id)"
            >
              <!-- 이미지 데이터가 없을 때 -->
              <v-img v-if="card.img == '' || car.img == null"> </v-img>
              <v-img v-else :src="card.img"> </v-img>

              <v-card-title class="text-no-wrap text-truncate">{{
                card.name
              }}</v-card-title>
            </v-card>
            <v-row justify="center" class="ma-auto">
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn icon :color="color" v-bind="attrs" v-on="on">
                    <v-icon
                      v-if="suggestFlag[idx] == true"
                      size="60"
                      @click="onSuggestClick(idx)"
                      color="red"
                      >mdi-heart</v-icon
                    >
                    <v-icon
                      v-else
                      size="40"
                      @click="onSuggestUnclick(idx)"
                      color="gray"
                      >mdi-heart</v-icon
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
    <v-row justify="center">
      <v-btn depressed :disabled="btnNumber <= 0" @click="onClick">
        다음
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const SuggestStore = "SuggestStore";
const MemberStore = "MemberStore";
const CourseStore = "CourseStore";

export default {
  data() {
    return {
      dialog: false,
      btnNumber: 0,
      suggestFlag: [],
      wishFlag: [],
      finalList: [],
      orders: []
    };
  },
  created() {
    this.reqWish(this.getMemberInfo.email); // 이메일 정보를 통해서 위시List 받아옴.
  },
  computed: {
    ...mapGetters(SuggestStore, ["getSuggestList", "getWishList"]), // 추천이랑 위시리스트 들고옴.
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(SuggestStore, ["reqWish"]),
    ...mapActions(CourseStore, ["reqCreateCourse", "reqCourseInfo"]),

    onSelect(spot_id) {
      this.$router.push("/spotdetail/" + spot_id); // 상세 보기.
    },
    onClick() {
      // 위시리스트와 추천리스트에서 선택한 것만 리스트에 넣어줌.
      for (var i = 0; i < this.getSuggestList.length; i++) {
        if (this.suggestFlag[i] == true) {
          this.finalList.push(this.getSuggestList.spot_id);
        }
      }

      for (i = 0; i < this.getWishList.length; i++) {
        if (this.suggestFlag[i] == true) {
          this.finalList.push(this.getWishList.spot_id);
        }
      }
      // console.log(this.finalList);
      const finalData = JSON.parse(JSON.stringify(this.finalList));
      console.log(finalData); // 요 형식으로 보내면 됨.

      for (i = 0; i < this.finalList.length; i++) {
        this.orders[i] = i;
      }
      // 여기서 선택한 곳만 리스트로 보내고, 코스 선택으로 넘겨야 함.
      // 여기부터 작업하면 됨. 코스로 보내는 곳
      let formData = new FormData();
      formData.append("email", this.getMemberInfo.email);
      formData.append("spot_id", finalData);
      formData.append("orders", this.orders);

      this.reqCreateCourse(formData).then(response => {
        // 정상적인 요청이라면,
        if (response) {
          // 느낌표가 맞나? 테스트 해봐야 할 듯.

          // 백에서 코스 이름 넘겨줘야함.
          const courseName = response.data.name;
          let formData2 = new FormData();

          formData2.append("name", courseName);
          formData2.append("user_id", this.getMemberInfo.email);

          this.reqCourseInfo(formData2).then(res => {
            this.$router.push("/makecourse");
            console.log(res);
          });
        } else alert(response.msg);
      });
    },
    onSuggestClick(idx) {
      this.suggestFlag[idx] = false;
      this.btnNumber--;
    },
    onSuggestUnclick(idx) {
      this.suggestFlag[idx] = true;
      this.btnNumber++;
    }
  }
};
</script>

<style></style>
