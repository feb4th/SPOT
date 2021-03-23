<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12" sm="2"> </v-col>
          <v-col cols="12" sm="8">
            <v-card min-height="70vh" rounded="lg">
              <v-card-title class="headline justify-center"
                >로그인</v-card-title
              >
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="이메일"
                        placeholder="POT@email.com"
                        v-model="member.email"
                        required
                        clearable
                        :counter="50"
                        :rules="[
                          (v) => !!v || '이메일을 입력하세요',
                          (v) =>
                            /.+@.+\..+/.test(v) || '이메일 형식이 아닙니다',
                          (v) => v.length <= 50 || '이메일이 너무 깁니다'
                        ]"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="비밀번호"
                        type="password"
                        v-model="member.pw"
                        required
                        clearable
                        :rules="[
                          (v) => !!v || '비밀번호를 입력해 주세요',
                          (v) =>
                            (v && v.length > 7 && v.length <= 20) ||
                            '비밀번호는 8자리 이상 20자리 이하로 입력해야 합니다'
                        ]"
                        @keypress.enter="onSubmit()"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-btn color="success" block @click="onSubmit()"
                        >로그인</v-btn
                      >
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-col cols="7" class="text-end">아직 회원이 아니신가요?</v-col>
                <v-btn col-4 color="blue darken-1" text @click="signup()">
                  회원가입
                </v-btn>
              </v-card-actions>
              <v-card-actions class="pb-5">
                <v-col cols="7" class="text-end"
                  >아이디 / 비밀번호를 잊으셨나요?</v-col
                >
                <v-btn col-4 color="blue darken-1" text @click="findidpw()">
                  아이디/비밀번호 찾기
                </v-btn>
              </v-card-actions>
              <v-snackbar
                centered
                v-model="snackbar"
                timeout="2000"
                :color="color"
              >
                {{ msg }}
              </v-snackbar>
            </v-card>
          </v-col>

          <v-col cols="12" sm="2"> </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
const MemberStore = "MemberStore";

export default {
  data: () => {
    return {
      //멤버정보
      member: {
        email: "",
        pw: ""
      },
      //에러 메세지 표출
      snackbar: "",
      msg: "",
      color: ""
    };
  },
  methods: {
    ...mapActions(MemberStore, ["Login"]),

    onSubmit() {
      //로그인 버튼
      if (this.member.email == "" || this.member.pw == "") {
        this.msg = "공백이 존재합니다!";
        this.color = "warning";
        this.snackbar = true;
        return;
      }
      this.Login(this.member) //Login action 사용 (member를 payload로 보냄)
        .then((response) => {
          this.msg = response.msg;
          this.color = response.color;
          this.snackbar = true;

          if (response.result && sessionStorage.getItem("access-token") != "") {
            console.log("회원번호: " + this.memberid);
            this.$router.push("/");
            this.dialog = false;
          } else {
            this.email = "";
            this.pw = "";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    signup() {
      //this.$router.push("/signup");
    },
    findidpw() {
      //this.$router.push("/findidpw");
    }
  }
};
</script>

<style></style>
