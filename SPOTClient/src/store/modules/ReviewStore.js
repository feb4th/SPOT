import axios from "../../axios/axios-common";
import axios2 from "axios";

const ReviewStore = {
  namespaced: true,
  state: {
    reviewlist: [
      {
        review_id: 1,
        img: "",
        member_id: "123",
        content: "아빠발국밥이 맛있어요!",
        score: 4.5
      },
      {
        review_id: 2,
        img: "",
        member_id: "123",
        content: "제 취향 아닙니다 우웩 ㅠㅠ",
        score: 2.0
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
    //맛집 리뷰리스트 조회
    reqReviewList(context, no) {
      return axios2
        .get("http://j4a102.p.ssafy.io:8000/store/" + no)
        .then(response => {
          if (response.data.message == "success") {
            console.log(response);
            context.commit("setReviewList", response.data.contents.review_list);
            return {
              result: true,
              msg: "세부 사항으로 이동"
            };
          } else {
            return {
              result: false,
              msg: "실패"
            };
          }
        })
        .catch(error => {
          console.log(error);
          return {
            result: false,
            msg: "에러 발생"
          };
        });
    },

    //관광지 리뷰리스트 조회
    reqTourReviewList(context, spot_id) {
      return axios
        .get("/review/" + spot_id)
        .then(response => {
          if (response.data.message == "success") {
            context.commit("setReviewList", response.data.result);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    //리뷰작성
    reqCreateReview(context, review) {
      return axios2
        .post("http://j4a102.p.ssafy.io:8000/review", {
          writer_info: {
            id: 1,
            gender: review.writer_info.gender,
            born_year: parseInt(review.writer_info.born_year)
          },
          review_info: {
            id: 1,
            score: parseInt(review.review_info.score),
            content: review.review_info.content,
            reg_time: review.review_info.reg_time
          },
          id: parseInt(review.id)
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

    //리뷰작성
    reqCreateTourReview(context, review) {
      return axios
        .post("/review", {
          content: review.content,
          date: review.date,
          score: review.score,
          tour_review_id: "0",
          toursight_id: review.toursight_id,
          user_id: review.user_id
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
