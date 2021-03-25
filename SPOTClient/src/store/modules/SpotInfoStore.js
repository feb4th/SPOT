import axios from "../../axios/axios-common";

const SpotInfoStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    spot: {}
  },

  getters: {
    getSpot(state) {
      return state.spot;
    }
  },
  mutations: {
    setSpot(state, spot) {
      state.spot = spot;
    }
  },

  actions: {
    reqSpot(context, no) {
      return axios
        .get("/store/" + no)
        .then(response => {
          if (response.data.status === true) {
            context.commit("setSearchList", response.data.object);
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
    }
  }
};

export default SpotInfoStore;
