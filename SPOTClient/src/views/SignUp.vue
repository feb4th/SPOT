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

                  <h2>회원가입</h2>

                  <v-btn disabled text><v-spacer></v-spacer></v-btn
                ></v-card-title>
                <!-- 회원 가입 fields -->
                <v-card-text style="margin:auto;">
                  <v-form ref="form" v-model="valid" lazy-validation>
                    <v-row>
                      <v-col cols="10">
                        <v-text-field
                          v-model="email"
                          @change="emailcheck = false"
                          :rules="[
                            v => !!v || '이메일을 입력하세요',
                            v =>
                              /.+@.+\..+/.test(v) || '이메일 형식이 아닙니다',
                            v => v.length <= 50 || '이메일이 너무 깁니다'
                          ]"
                          label="이메일"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="2">
                        <v-btn
                          color="primary"
                          block
                          @click="checkEmail()"
                          :disabled="emailcheck"
                          >중복체크</v-btn
                        ></v-col
                      >
                    </v-row>
                    <v-row>
                      <v-col cols="10">
                        <v-text-field
                          v-model="nickname"
                          @change="nicknamecheck = false"
                          :counter="10"
                          :rules="[
                            v => !!v || '닉네임을 입력해 주세요',
                            v =>
                              (v && v.length > 1 && v.length <= 10) ||
                              '닉네임은 2자리 이상 10자리 이하로 입력해야 합니다'
                          ]"
                          label="닉네임"
                          required
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

                    <v-text-field
                      v-model="pw"
                      :rules="[
                        v => !!v || '비밀번호를 입력해 주세요',
                        v =>
                          (v && v.length > 7 && v.length <= 20) ||
                          '비밀번호는 8자리 이상 20자리 이하로 입력해야 합니다'
                      ]"
                      type="password"
                      label="비밀번호"
                      required
                      class="my-6"
                    ></v-text-field>

                    <v-text-field
                      v-model="pwvaild"
                      :rules="[
                        v => !!v || '비밀번호를 입력해 주세요',
                        v => v === pw || '입력한 비밀번호와 다릅니다'
                      ]"
                      type="password"
                      label="비밀번호 확인"
                      required
                      class="my-6"
                    ></v-text-field>

                    <v-row>
                      <v-col>
                        <v-select
                          :items="genders"
                          label="성별"
                          v-model="gender"
                          :rules="[v => !!v || '성별을 선택해주세요']"
                          required
                        ></v-select>
                      </v-col>
                      <v-col>
                        <v-text-field
                          label="출생년도"
                          placeholder="2021"
                          v-model="birth"
                          :rules="[
                            v => !!v || '출생년도를 입력해주세요',
                            v =>
                              (v >= 1900 && v <= 2021) ||
                              '네자리 연으로 입력해주세요'
                          ]"
                          required
                        ></v-text-field>
                      </v-col>
                    </v-row>

                    <v-expansion-panels tile class="mt-10">
                      <v-expansion-panel>
                        <v-expansion-panel-header>
                          SPOT 이용약관
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                          제1조 (목적)<br /><br />
                          본 약관은 SPOT 주식회사(이하 '회사')가 SPOT
                          사이트(이하 ‘사이트’)를 통하여 인터넷상에서 제공하는
                          서비스(이하 ‘서비스’)를 이용하는 고객 (이하 '고객')
                          간의 권리와 의무 및 책임 등 기타 제반사항을 규정함을
                          목적으로 합니다.<br /><br />

                          제2조 (용어의 정의)<br /><br />
                          이 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br />
                          가. '회원'이라 함은 이 약관에 동의하고 회원가입을
                          통하여 이용자ID(고유번호)와 비밀번호를 발급받은
                          자로서, 회사가 제공하는 서비스를 이용할 수 있는
                          이용자를 말합니다.<br />
                          나. '이용자ID'라 함은 회원의 식별 및 서비스 이용을
                          위하여 회원의 신청에 따라 회사가 회원 별로 부여하는
                          고유한 문자와 숫자의 조합을 말합니다.<br />
                          다. '비밀번호'라 함은 이용자ID로 식별되는 회원의 본인
                          여부를 검증하기 위하여 회원이 설정하여 회사에 등록한
                          고유의 문자와 숫자의 조합을 말합니다.<br />
                          라. ‘로그인’이라 함은 이용자ID와 비밀번호를 통하여
                          서비스 신청 및 사용 중 서비스의 세부정보를 확인할 수
                          있는 행위를 말합니다.<br />
                          마. '탈퇴'라 함은 회원이 서비스 이용을 해지하는 것을
                          말합니다.<br />
                          바. ‘휴면ID’ 라 함은 회사에서 정한 일정 기간 동안
                          사이트 접속 및 미 로그인 시 고객의 정보 악용방지를
                          위하여 보호되는 계정입니다.<br />
                          이 약관에서 사용하는 용어 중 제1항에서 정하지 아니한
                          것은 관계 법령 및 서비스 별 안내에서 정하는 바에
                          따르며, 그 외에는 일반 관례에 따릅니다.<br /><br />

                          제3조 (이용약관의 효력 및 개정)<br />
                          1. 이 약관은 사이트를 통해 온라인으로 공시하고 회원의
                          동의로 효력을 발생합니다. 본 약관의 공시는 사이트에
                          게시하는 방법으로 합니다.<br />
                          2. 회사는 합리적인 사유가 발생할 경우 관련 법령에
                          위배되지 않는 범위 내에서 약관을 개정할 수 있습니다.
                          개정된 약관은 사이트를 통해 공지하거나 기타의 방법으로
                          회원에게 공지함으로써 그 효력이 발생하며, 이용자의
                          권리 또는 의무에 관한 변경은 최소한 7일 전에
                          공지합니다.<br />
                          3. 변경된 약관에 대한 정보를 알지 못해 발생하는 회원의
                          피해는 회사에서 책임지지 않습니다.<br />
                          4. 회원은 변경된 약관에 동의하지 않을 경우 서비스
                          이용을 중단하고 회원 탈퇴를 요청할 수 있습니다. 단,
                          이의가 있음에도 불구하고 제 2항에서 정한 바에 따른
                          회사의 고지가 있은 후 30일 이내에 이용계약을 하지 않은
                          회원은 변경된 약관에 동의한 것으로 간주합니다.<br />
                        </v-expansion-panel-content>
                      </v-expansion-panel>
                    </v-expansion-panels>

                    <v-checkbox
                      v-model="check"
                      label="이용약관에 동의합니다"
                      class="my-5"
                      :rules="[v => v === true || '이용약관에 동의해주세요']"
                    ></v-checkbox>

                    <v-row class="my-1">
                      <v-col
                        ><v-btn
                          block
                          :disabled="!valid"
                          :loading="processing"
                          color="primary"
                          class="mr-4"
                          @click="onSignup"
                        >
                          가입하기
                        </v-btn></v-col
                      >
                    </v-row>
                  </v-form>
                </v-card-text>
              </v-card>
            </v-sheet>
          </v-col>
        </v-row>
        <v-snackbar centered v-model="snackbar" timeout="2000" :color="color">
          {{ msg }}
        </v-snackbar>
      </v-container>
      <!-- 회원가입완료 모달창 -->
      <v-dialog v-model="dialog" persistent max-width="290">
        <v-card>
          <v-alert border="top" colored-border type="success">
            회원가입완료
          </v-alert>
          <v-card-text>
            이메일 인증 후 로그인 해 주세요
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="secondary"
              text
              @click="dialog = false"
              router-link
              to="/login"
            >
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
const MemberStore = "MemberStore";

export default {
  data() {
    return {
      valid: true,
      email: "",
      emailcheck: false,
      nickname: "",
      nicknamecheck: false,
      pw: "",
      pwvaild: "",
      gender: "",
      genders: ["남", "여"],
      birth: "",
      check: false,
      processing: false,
      snackbar: false,
      msg: "",
      color: "",
      dialog: false
    };
  },
  methods: {
    ...mapActions(MemberStore, [
      "reqSignup",
      "reqSignupValidation",
      "reqCheckEmail",
      "reqCheckNickname"
    ]),
    reset() {
      (this.email = ""),
        (this.pw = ""),
        (this.pwvaild = ""),
        (this.phone = ""),
        (this.name = "");
    },
    onSignup() {
      // form 검증
      if (this.$refs.form.validate() === false) return;

      // 이메일 중복체크 검증
      if (!this.emailcheck) {
        this.msg = "이메일 중복체크를 진행해 주세요";
        this.color = "error";
        this.snackbar = true;
        return;
      }

      // 이메일 중복체크 검증
      if (!this.nicknameheck) {
        this.msg = "닉네임 중복체크를 진행해 주세요";
        this.color = "error";
        this.snackbar = true;
        return;
      }

      this.processing = true;

      this.reqSignup({
        email: this.email,
        pw: this.pw,
        phone: this.phone,
        name: this.name
      })
        .then(response => {
          if (response) {
            this.reqSignupValidation(this.email).then(response => {
              if (response) this.dialog = true;
              else {
                this.msg = "이메일 전송이 실패했습니다";
                this.snackbar = true;
                this.color = "error";
                this.processing = false;
              }
            });
          } else {
            // 이메일 중복
            this.msg = "이미 등록된 이메일입니다";
            this.snackbar = true;
            this.color = "error";
            this.processing = false;

            this.email = "";
            this.pw = "";
            this.pwvaild = "";
          }
        })
        .catch(error => {
          console.log(error);
        });
    },

    checkEmail() {
      this.reqCheckEmail(this.email)
        .then(response => {
          if (response) {
            //사용 가능한 이메일일 때
            this.msg = "사용가능한 이메일입니다";
            this.snackbar = true;
            this.color = "success";
            this.emailcheck = true;
          } else {
            //중복값이 존재할 때
            this.msg = "이미 존재하는 이메일입니다";
            this.snackbar = true;
            this.color = "error";
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    checkNickname() {
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
    }
  }
};
</script>

<style></style>
