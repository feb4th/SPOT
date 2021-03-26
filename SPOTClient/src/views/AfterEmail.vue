<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12" sm="2"> </v-col>

          <v-col cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg" class="align-center">
              <v-row justify="center" aligh="center" class="ma-10">
                <v-col cols="6">
                  <v-alert border="left" type="success" v-if="result">
                    <div class="title">회원가입이 완료되었습니다.</div>
                    <div>로그인 후 이용이 가능합니다.</div>
                    <div class="mt-10">
                      <v-btn rounded router-link to="/" color="warning"
                        >홈으로</v-btn
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
