import axios from "../../axios/axios-common";
import axios2 from "axios";

const SuggestStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    suggestList: [],
    // 관광지 추천 정보 담김
    SuggestTourList: []
  },
  getters: {
    getSuggestList(state) {
      return state.suggestList;
    },
    getSuggestTourList(state) {
      return state.SuggestTourList;
    }
  },
  mutations: {
    setSuggestList(state, suggestList) {
      state.suggestList = suggestList;
    },
    setSuggestTourList(state, SuggestTourList) {
      state.SuggestTourList = SuggestTourList;
    }
  },

  actions: {
    reqSuggestTour(context, area) {
      return axios
        .get("/toursight/list/" + area)
        .then(response => {
          if (response.data.message == "success") {
            context.commit("setSuggestTourList", response.data.result); // 여기 데이터 리턴 변수명 확인해야함.
            return {
              result: true,
              msg: "관광지 가져왔습니다.",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "관광지가 없습니다.",
              color: "warning"
            };
          }
        })
        .catch(error => {
          console.log(error);
          return {
            result: false,
            msg: "에러 발생",
            color: "warning"
          };
        });
    },
    reqSuggest(context, user_id) {
      //console.log(user_id);
      return axios2
        .get("http://j4a102.p.ssafy.io:8000/recommendation/" + user_id)
        .then(response => {
          console.log(response);
          if (response.data.message == "success") {
            context.commit("setSuggestList", response.data.contents);
            return {
              result: true,
              msg: "추천 데이터 가져왔습니다.",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "추천 데이터가 없습니다.",
              color: "warning"
            };
          }
        })
        .catch(error => {
          console.log(error);
          return {
            result: false,
            msg: "에러 발생",
            color: "warning"
          };
        });
    }
  }
};

export default SuggestStore;
