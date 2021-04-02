<template>
  <v-container>
    <v-card elevation="0">
      <!-- <v-card-title class="justify-end mx-auto"
        ><v-btn color="primary" text @click="toModify()"
          >프로필편집</v-btn
        ></v-card-title
      > -->
      <v-card-text>
        <v-row justify="center" class="ma-7">
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-hover v-slot="{ hover }" class="ma-auto">
                <v-card
                  v-bind="attrs"
                  v-on="on"
                  height="100"
                  width="100"
                  id="rounded-card"
                  :elevation="hover ? 8 : 1"
                  :class="{ 'on-hover': !hover }"
                >
                  <!-- 프로필 이미지가 없을 때 -->
                  <v-img
                    v-if="
                      getMemberInfo.img == null || getMemberInfo.img == ''
                    "
                    class="ma-auto mp-4 align-center"
                    height="100"
                    width="100"
                    style="border-radius: 50%"
                    src="../../assets/logo.png"
                    @click="onClickImageUpload"
                  >
                    <input
                      ref="imageInput"
                      accept="image/*"
                      type="file"
                      hidden
                      @change="onChangeImages"
                    />
                    <!-- <v-card-text>
                      <v-btn v-if="hover" @click="onClickImageUpload"
                        >Edit
                      </v-btn>
                    </v-card-text> -->
                  </v-img>

                  <!-- 프로필 이미지가 있을 때 -->
                  <v-img
                    v-else
                    class="ma-auto mp-4 align-center "
                    height="100"
                    width="100"
                    style="border-radius: 50%"
                    :src="getMemberInfo.img"
                    @click="onClickImageUpload"
                  >
                    <input
                      ref="imageInput"
                      accept="image/*"
                      type="file"
                      hidden
                      @change="onChangeImages"
                    />
                    <!-- <v-card-text class="justify-center">
                      <v-btn v-if="hover" @click="onClickImageUpload"
                        >Edit
                      </v-btn>
                    </v-card-text> -->
                  </v-img>
                </v-card>
              </v-hover>
            </template>
            <span>
              <div>100 X 100 px</div>
              <div>사이즈로 넣어주세요</div>
            </span>
          </v-tooltip>
        </v-row>
        <v-row justify="center">
          <p style="font-size: 40px;">{{ getMemberInfo.nickname }}</p>
          <v-icon style="margin: 0px -15px 15px 5px;" @click="toModify()">mdi-pencil-outline</v-icon>
        </v-row>
        <v-row justify="center">
          <p style="font-size: 15px;">{{ getMemberInfo.email }}</p>
        </v-row>
      </v-card-text>
      <v-snackbar centered v-model="snackbar" timeout="2000" :color="color">
        {{ msg }}
      </v-snackbar>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const MemberStore = "MemberStore";
import axios from "../../axios/axios-common";

export default {
  data: () => {
    return {
      snackbar: false,
      color: "",
      msg: ""
    };
  },
  created() {
    this.reqMemberInfo(this.$route.params.email);
  },
  computed: {
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(MemberStore, ["reqChangeImage", "reqMemberInfo"]),

    toModify() {
      //회원정보 수정 페이지로 이동
      this.$router.push("/member/modify/" + this.$route.params.email);
    },
    onClickImageUpload() {
      //이미지 업로드 버튼 클릭시
      this.$refs.imageInput.click(); //imageInput이름을 가진 refs를 찾아서 click
    },
    async onChangeImages(e) {
      //변화가 감지되었을 경우
      const file = e.target.files[0]; //해당 event가 호출된 파일
      this.member.imgdata = file; //이미지데이터에 file 기입
      this.image = window.URL.createObjectURL(file);
      await this.imgmodify(); //이미지 DB에 수정
    },
    imgmodify() {
      let formData = new FormData(); //정보 전달을 위해 formdata 생성
      formData.append("file", this.member.imgdata); //이미지 정보전달

      axios
        .post("/upload/member/" + this.getMemberInfo.email, formData, {
          headers: { "Content-Type": "multipart/form-data" }
        })
        .then(() => {
          this.color = "success";
          this.msg = "이미지 변경 성공";
          this.snackbar = true;
          this.getmember();
          this.member.img = this.image;
        })
        .catch(exp => {
          this.color = "success";
          this.msg = "이미지 변경 실패";
          this.snackbar = true;
          console.log(exp + "수정에 실패하였습니다.");
        });
    }
  }
};
</script>

<style scoped>
#rounded-card {
  transition: opacity 0.4s ease-in-out;
  border-radius: 50%;
  min-height: 50px;
  min-width: 50px;
}

#rounded-card:not(.on-hover) {
  opacity: 0.8;
}
</style>
