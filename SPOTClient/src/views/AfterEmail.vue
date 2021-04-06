<template>
  <v-app>
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col justify="center" align="center" cols="12">
            <v-sheet min-height="70vh" rounded="lg" class="align-center">
              <v-row
                class="align-center justify-center text-center"
                style="margin-top:0.15em;"
              >
                <v-col cols="auto">
                  <v-alert type="secondary" v-if="result">
                    <div class="title ma-10">
                      <h1 style="color:white;">회원가입이 완료되었습니다!</h1>
                    </div>
                    <div style="text-align: center;">
                      로그인 후 이용이 가능합니다.
                    </div>
                    <div class="mt-9" style="margin-bottom: 20px;">
                      <v-btn rounded router-link to="/" color="primary"
                        >메인으로</v-btn
                      >
                    </div>
                  </v-alert>

                  <v-alert border="left" type="error" v-else>
                    <div class="title">이메일 인증에 오류가 발생했습니다.</div>
                    <div class="mt-10">
                      <v-btn
                        rounded
                        @click.once="reqSignupValidation(email)"
                        color="warning"
                        >이메일 재인증</v-btn
                      >
                    </div>
                  </v-alert>
                </v-col>
              </v-row>
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
      result: false,
      email: ""
    };
  },
  created() {
    this.email = this.$route.query.email;
    this.result = this.reqClickEmailLink({
      email: this.email,
      authkey: this.$route.query.authkey
    });
  },
  methods: {
    ...mapActions(MemberStore, ["reqClickEmailLink", "reqSignupValidation"])
  }
};
</script>

<style></style>
