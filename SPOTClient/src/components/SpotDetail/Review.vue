<template>
  <v-container>
    <v-card class="pa-10" elevation="0">
      <v-row>
        <v-col cols="2">
          <v-banner>
            <strong>리뷰</strong>
          </v-banner>
        </v-col>
      </v-row>
      <!-- 리뷰 작성 -->
      <v-row class="mt-2"
        ><v-col cols="auto"
          ><v-avatar>
            <v-img
              v-if="getMemberInfo.img == '' || getMemberInfo.img == null"
              src="https://j4a102.p.ssafy.io/profile/LogoMini.png"
            />
            <v-img v-else :src="getMemberInfo.img" /> </v-avatar
        ></v-col>
        <v-col>
          <v-text-field
            :counter="50"
            label="내용"
            name="context"
            required
            v-model="context"
            maxlength="50"
          ></v-text-field>
        </v-col>
        <v-col cols="auto">
          <v-btn block color="info" @click="onWrite()"> 등록 </v-btn>
        </v-col>
      </v-row>

      <!-- 작성된 리뷰 목록-->
      <v-row class="mt-2">
        <v-list shaped color="rgba(255, 255, 255, 0)">
          <v-list-item
            v-for="comment in getReviewList"
            :key="comment.review_id"
          >
            <v-list-item-icon>
              <v-list-item-avatar>
                <v-img
                  v-if="comment.img == '' || comment.img == null"
                  src="https://j4a102.p.ssafy.io/profile/LogoMini.png"
                />
                <v-img :src="comment.img"></v-img>
              </v-list-item-avatar>
            </v-list-item-icon>
            <v-list-item-action>
              <v-rating
                v-model="comment.rating"
                color="yellow darken-3"
                background-color="grey darken-1"
                empty-icon="$ratingFull"
                half-increments
                hover
                large
              ></v-rating>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title v-text="comment.content"></v-list-item-title>
            </v-list-item-content>
            <v-list-item-action
              v-if="comment.member_id == getMemberInfo.member_id"
            >
              <v-btn icon color="blue" @click="openModify(comment)"
                ><v-icon>mdi-pencil</v-icon></v-btn
              >
            </v-list-item-action>
            <v-list-item-action
              v-if="comment.member_id == getMemberInfo.member_id"
            >
              <v-btn icon color="red" @click="openDelete(comment.review_id)"
                ><v-icon>mdi-minus</v-icon></v-btn
              >
            </v-list-item-action>
          </v-list-item>
        </v-list>
      </v-row>

      <!--리뷰수정 모달창 -->
      <v-dialog v-model="Dialog.modify" persistent max-width="700">
        <v-card style="opacity: 1">
          <v-card-title class="headline"> 리뷰수정 </v-card-title>
          <v-card-subtitle>
            <v-rating
              v-model="tmpComment.rating"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              half-increments
              hover
              large
            ></v-rating>
          </v-card-subtitle>
          <v-card-text>
            <v-text-field v-model="tmpComment.content" required></v-text-field
          ></v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="onModify()">
              수정
            </v-btn>
            <v-btn color="green darken-1" text @click="Dialog.modify = false">
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- 리뷰삭제 모달창 -->
      <v-dialog v-model="Dialog.delete" persistent max-width="290">
        <v-card style="opacity: 1">
          <v-card-title class="headline"> 리뷰삭제 </v-card-title>
          <v-card-text>정말 삭제하시겠습니까?</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="onDelete()"> 예 </v-btn>
            <v-btn color="green darken-1" text @click="Dialog.delete = false">
              아니오
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>
    <v-snackbar centered v-model="snackbar" timeout="2000" :color="color">
      {{ msg }}
    </v-snackbar>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const MemberStore = "MemberStore";
const ReviewStore = "ReviewStore";

export default {
  data() {
    return {
      context: "",
      score: "",
      review_id: "",
      tmpComment: {},
      Dialog: {
        modify: false,
        delete: false
      },
      color: "",
      snackbar: false,
      msg: ""
    };
  },
  created() {
    this.reqReviewList(this.$route.params.spotid);
  },
  computed: {
    ...mapGetters(ReviewStore, ["getReviewList"]),
    ...mapGetters(MemberStore, ["getMemberInfo"])
  },
  methods: {
    ...mapActions(ReviewStore, [
      "reqReviewList",
      "reqCreateReview",
      "reqModifyReview",
      "reqDeleteReview"
    ]),
    onWrite() {
      let formData = new FormData();
      formData.append("store_id", this.$route.params.spotid);
      formData.append("email", this.$route.params.spotid);
      formData.append("context", this.context);
      formData.append("score", this.score);
      this.reqCreateReview(formData);
    },
    openModify(comment) {
      this.review_id = comment.review_id;
      this.tmpComment = comment;
      this.Dialog.modify = true;
    },
    onModify() {
      let formData = new FormData();
      formData.append("review_id", this.review_id);
      formData.append("context", this.tmpComment.context);
      formData.append("score", this.tmpComment.score);
      this.reqModifyReview(formData);
      this.Dialog.modify = false;
    },
    openDelete(review_id) {
      this.review_id = review_id;
      this.Dialog.delete = true;
    },
    onDelete() {
      this.reqDeleteReview(this.review_id);
      this.Dialog.delete = false;
    }
  }
};
</script>

<style></style>
