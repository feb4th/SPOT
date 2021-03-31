import axios from "../../axios/axios-common";

const ReviewStore = {
  namespaced: true,
  state: {
    reviewlist: [
      {
        review_id: 1,
        img: "",
        member_id: "123",
        content: "댓글 내용 1",
        rating: 4.5
      },
      {
        review_id: 2,
        img: "",
        member_id: "123",
        content: "댓글 내용 2",
        rating: 4.0
      }
    ]
  },
  getters: {
    getReviewList(state) {
      return state.reviewlist;
    }
  },
  mutations: {
    setReviewList(state, payload) {
      state.reviewlist = payload;
    }
  },
  actions: {
    //리뷰리스트 조회
    reqReviewList(context, spot_id) {
      return axios
        .get("/reviewlist/" + spot_id)
        .then(response => {
          if (response.message == "success") {
            context.commit("setReviewList", response.reviews);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    //리뷰작성
    reqCreateReview(context, review) {
      return axios
        .post("/review", {
          store_id: review.spot_id,
          email: review.email,
          context: review.context,
          score: review.score
        })
        .then(response => {
          if (response.message == "success") {
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    //리뷰 수정
    reqModifyReview(context, review) {
      return axios
        .put("/review", {
          review_id: review.review_id,
          context: review.context,
          score: review.score
        })
        .then(response => {
          if (response.message == "success") {
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    //리뷰 삭제
    reqDeleteReview(context, review_id) {
      return axios
        .delete("/review/" + review_id)
        .then(response => {
          if (response.message == "success") {
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};

export default ReviewStore;
