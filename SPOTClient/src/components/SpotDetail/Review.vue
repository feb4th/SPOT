<template>
  <v-container>
    <v-row>
      <v-card elevation="0">
        <v-card-title style="margin-left: 2em;">
          <v-icon x-large>mdi-check</v-icon>
          <strong>리뷰</strong>
        </v-card-title>
      </v-card>
    </v-row>
    <v-card class="mx-auto" max-width="750" elevation="0">
      <v-row class="mt-2"
        ><v-col cols="auto"
          ><v-avatar>
            <v-img
              v-if="getMemberInfo.img == '' || getMemberInfo.img == null"
              src="../../assets/logo.png"
            />
            <v-img v-else :src="getMemberInfo.img" /> </v-avatar
        ></v-col>
        <v-col cols="auto">
          <v-rating
            v-model="score"
            color="yellow darken-3"
            background-color="grey darken-1"
            empty-icon="$ratingFull"
          ></v-rating>
        </v-col>
        <v-col>
          <v-text-field
            :counter="50"
            label="내용"
            name="context"
            v-model="context"
            maxlength="50"
          ></v-text-field>
        </v-col>
        <v-col cols="auto">
          <v-btn block color="primary" @click="onWrite()"> 등록 </v-btn>
        </v-col>
      </v-row>
    </v-card>
    <v-card class="mx-auto" max-width="750" elevation="0">
      <v-list-item v-for="(comment, idx) in getReviewList" :key="idx">
        <v-col cols="auto" class="align-center"
          ><v-avatar>
            <v-img
              v-if="comment.img == '' || comment.img == null"
              src="../../assets/logo.png"
            />
            <v-img v-else :src="getMemberInfo.img" /> </v-avatar
        ></v-col>
        <v-col cols="4">
          <v-list-item-content>
            <div class="overline">
              <span style="margin-left: 1em;">{{ comment.member_id }}</span>
              <v-rating
                :v-model="parseInt(comment.score)"
                color="yellow darken-3"
                background-color="grey darken-1"
                empty-icon="$ratingFull"
                half-increments
                readonly
              ></v-rating>
            </div>
          </v-list-item-content>
        </v-col>
        <v-col>
          {{ comment.content }}
        </v-col>
      </v-list-item>
    </v-card>
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
      score: 0,
      review_id: "",
      genderidx: "남",
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
    this.reqTourReviewList(this.$route.params.spotid);
    console.log(this.getReviewList);
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
      "reqDeleteReview",
      "reqTourReviewList",
      "reqCreateTourReview"
    ]),
    onWrite() {
      //관광지일때
      let date =
        new Date().toISOString().substr(0, 10) +
        " " +
        new Date().toTimeString().substr(0, 8);
      this.reqCreateTourReview({
        toursight_id: this.$route.params.spotid,
        user_id: this.getMemberInfo.user_id,
        content: this.context,
        score: this.score,
        date: date
      });
      this.score = 0;
      this.context = "";
      this.reqTourReviewList(this.$route.params.spotid);
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
