<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
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

                  <h2>비밀번호 찾기</h2>
                  <v-btn disabled text><v-spacer></v-spacer></v-btn>
                </v-card-title>
                <v-card-text class="mt-10">
                  <v-form ref="pw_form" v-model="pwvalid" lazy-validation>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          placeholder="이메일"
                          v-model="pw.email"
                          solo
                          required
                          clearable
                          :rules="[
                            v => !!v || '이메일을 입력하세요',
                            v =>
                              /.+@.+\..+/.test(v) || '이메일 형식이 아닙니다',
                            v => v.length <= 50 || '이메일이 너무 깁니다'
                          ]"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          placeholder="닉네임"
                          v-model="pw.nickname"
                          required
                          solo
                          clearable
                          :rules="[
                            v => !!v || '닉네임을 입력하세요',
                            v =>
                              (v && v.length > 1 && v.length <= 10) ||
                              '닉네임은 2자리 이상 10자리 이하로 입력해야 합니다'
                          ]"
                        ></v-text-field>
                      </v-col>
                      <v-col>
                        <!-- PW Modal -->
                        <v-dialog v-model="pwModal" max-width="500">
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn
                              primary
                              block
                              large
                              color="primary"
                              :disabled="!pwvalid"
                              :attr="attrs"
                              v-on="on"
                              @click="onFindPW()"
                              >비밀번호 찾기</v-btn
                            >
                          </template>
                          <!-- Modal content -->
                          <v-card>
                            <v-alert
                              border="top"
                              colored-border
                              :type="result ? 'success' : 'warning'"
                            >
                              비밀번호 찾기
                            </v-alert>
                            <v-card-text>
                              {{ msg }}
                            </v-card-text>

                            <v-divider></v-divider>

                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn
                                :color="result ? 'success' : 'warning'"
                                text
                                @click="pwModal = false"
                              >
                                확인
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-card-text>
              </v-card>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
const MemberStore = "MemberStore";

export default {
  data() {
    return {
      //비밀번호 찾기
      pw: {
        email: "",
        nickname: ""
      },
      result: "",
      msg: "",
      // 유효값 검증에 의한 toggle
      pwvalid: true,
      // Modal 활성화
      pwModal: false
    };
  },
  methods: {
    ...mapActions(MemberStore, ["reqFindPw"]),
    // PW찾기
    onFindPW() {
      // 입력값 조건 충족이 안될 때
      if (this.$refs.pw_form.validate() === false) return;

      this.reqFindPw(this.pw)
        .then(response => {
          this.result = response.result;
          this.msg = response.msg;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

<style></style>
