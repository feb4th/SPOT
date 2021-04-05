<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <v-card class="pu-10 px-10 mu-10">
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

                  <h2>회원정보 수정</h2>

                  <v-btn disabled text><v-spacer></v-spacer></v-btn
                ></v-card-title>
                <v-card-text style="margin: auto;">
                  <v-row>
                    <v-col>
                      <!-- <v-text-field
                        v-model="getMemberInfo.email"
                        disabled
                        label="이메일"
                        solo
                      ></v-text-field> -->
                      <span>이메일</span>
                      <p></p>
                      <p style="font-size : 20px; margin-top: 1em; margin-bottom: 1em;">{{ getMemberInfo.email }}</p>
                    </v-col>
                  </v-row>
                  <v-form ref="form" v-model="NicknameValid" lazy-validation>
                    <v-row>
                      <v-col>
                        <span>닉네임</span>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="10">
                        <v-text-field
                          solo
                          v-model="nickname"
                          @keydown="nicknamecheck = false"
                          :counter="10"
                          :rules="[
                            v => !!v || '닉네임을 입력해 주세요',
                            v =>
                              (v && v.length > 1 && v.length <= 10) ||
                              '닉네임은 2자리 이상 10자리 이하로 입력해야 합니다'
                          ]"
                          label="닉네임"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="2">
                        <v-btn
                          color="primary"
                          block
                          @click="checkNickname()"
                          :disabled="nicknamecheck"
                          >중복체크</v-btn
                        >
                      </v-col>
                    </v-row>
                  </v-form>
                  <v-form ref="form" v-model="PWValid" lazy-validation>
                    <v-row>
                      <v-col>
                        <span>비밀번호</span>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                      <v-text-field
                        v-model="pw"
                        :counter="20"
                        :rules="[
                          v => !!v || '비밀번호를 입력해 주세요',
                          v =>
                            (v && v.length > 7 && v.length <= 20) ||
                            '비밀번호는 8자리 이상 20자리 이하로 입력해야 합니다'
                        ]"
                        type="password"
                        label="비밀번호"
                        solo
                      ></v-text-field>
                      </v-col>

                    </v-row>

                    <v-row>
                      <v-col>
                        <span>비밀번호 확인</span>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="pwvaild"
                          :rules="[
                            v => !!v || '비밀번호를 입력해 주세요',
                            v => v === pw || '입력한 비밀번호와 다릅니다'
                          ]"
                          type="password"
                          label="비밀번호 확인"
                          solo
                        ></v-text-field>
                      </v-col>

                    </v-row>

                  </v-form>
                  <!-- 회원탈퇴 모달창 -->
                  <v-row justify="end">
                    <v-col cols="auto">
                      <p style="color: #b71c1c;" @click="dialog = true;">회원 탈퇴</p>
                      <v-dialog v-model="dialog" persistent max-width="290">
                        <v-card style="opacity: 1">
                          <v-card-title class="headline">
                            회원탈퇴
                          </v-card-title>
                          <v-card-text>정말 탈퇴하시겠습니까?</v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="secondary"
                              text
                              @click="remove()"
                            >
                              예
                            </v-btn>
                            <v-btn
                              color="secondary"
                              text
                              @click="dialog = false"
                            >
                              아니오
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-col>
                  </v-row>
                  <v-row class="my-1">
                    <v-col
                      ><v-btn
                        block
                        color="primary"
                        class="mr-4"
                        @click="onModify"
                      >
                        수정하기
                      </v-btn></v-col
                    >
                    <!-- <v-col cols="4"
                      ><v-btn
                        block
                        color="warning"
                        class="mr-4"
                        @click="
                          () => {
                            this.$router.go(0);
                          }
                        "
                      >
                        다시쓰기
                      </v-btn></v-col
                    > -->
                  </v-row>
                </v-card-text>
              </v-card>
            </v-sheet>
          </v-col>
        </v-row>

        <v-snackbar centered v-model="snackbar" timeout="2000" :color="color">
          {{ msg }}
        </v-snackbar>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const MemberStore = "MemberStore";

export default {
  data() {
    return {
      NicknameValid: true,
      PWValid: true,
      nicknamecheck: true,
      nickname: "",
      pw: "",
      pwvaild: "",
      snackbar: false,
      color: "",
      msg: "",
      dialog: false
    };
  },
  created() {
    this.reqMemberInfo(this.$route.params.email);
    this.nickname = this.getMemberInfo.nickname;
  },
  computed: {
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(MemberStore, [
      "reqMemberInfo",
      "reqCheckNickname",
      "reqModifyMember",
      "reqRemoveMember"
    ]),
    onModify() {
      if (this.nickname != this.getMemberInfo.nickname) {
        //1.닉네임이 변경되었을 시
        if (!this.nicknamecheck) {
          //2.중복체크가 되지 않았다면
          this.msg = "닉네임 중복체크를 진행해 주세요";
          this.color = "error";
          this.snackbar = true;
          return;
        }
        if (this.pw == "" && this.pwvaild == "") {
          //2.비밀번호가 변경되지 않았다면 비밀번호에 0을 넣어 보내줌
          //닉네임만 변경될 때
          this.reqModifyMember({ nickname: this.nickname, pwd: "0" })
            .then(response => {
              if (response) {
                this.msg = "닉네임이 변경되었습니다!";
                this.color = "success";
                this.snackbar = true;
                return;
              } else {
                this.msg = "닉네임 변경에 실패하였습니다!";
                this.color = "error";
                this.snackbar = true;
                return;
              }
            })
            .catch(error => {
              console.log(error);
            });
        } else {
          //2.비밀번호가 변경되었을 때
          if (!this.PWValid) {
            //3.비밀번호가 일치하지 않으면
            this.msg = "비밀번호를 확인해 주세요";
            this.color = "error";
            this.snackbar = true;
            return;
          }
          //3.회원정보 수정
          //닉네임과 비밀번호가 변경될 때
          this.reqModifyMember({ nickname: this.nickname, pwd: this.pw })
            .then(response => {
              if (response) {
                this.msg = "닉네임, 비밀번호가 변경되었습니다!";
                this.color = "success";
                this.snackbar = true;
                return;
              } else {
                this.msg = "닉네임, 비밀번호 변경에 실패하였습니다!";
                this.color = "error";
                this.snackbar = true;
                this.pw = "";
                this.pwvaild = "";
                return;
              }
            })
            .catch(error => {
              console.log(error);
            });
        }
      } else {
        //1.닉네임이 바뀌지 않았다면
        if (!this.PWValid) {
          //2.비밀번호가 일치하지 않으면
          this.msg = "비밀번호를 확인해 주세요";
          this.color = "error";
          this.snackbar = true;
          return;
        } else {
          //2.닉네임 그대로 회원 정보 수정
          //비밀번호만 변경될 때
          this.reqModifyMember({ nickname: this.nickname, pwd: this.pw })
            .then(response => {
              if (response) {
                this.msg = "비밀번호가 변경되었습니다!";
                this.color = "success";
                this.snackbar = true;
                return;
              } else {
                this.msg = "비밀번호 변경에 실패하였습니다!";
                this.color = "error";
                this.snackbar = true;
                this.pw = "";
                this.pwvaild = "";
                return;
              }
            })
            .catch(error => {
              console.log(error);
            });
        }
      }
    },
    checkNickname() {
      //닉네임 중복 확인
      this.reqCheckNickname(this.nickname)
        .then(response => {
          if (response) {
            //사용 가능한 닉네임일 때
            this.msg = "사용가능한 닉네임입니다";
            this.snackbar = true;
            this.color = "success";
            this.emailcheck = true;
          } else {
            //중복값이 존재할 때
            this.msg = "이미 존재하는 닉네임입니다";
            this.snackbar = true;
            this.color = "error";
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    remove() {
      //회원탈퇴
      this.reqRemoveMember(this.getMemberInfo.member_id);
    }
  }
};
</script>

<style></style>
