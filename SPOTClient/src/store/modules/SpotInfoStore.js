import axios from "../../axios/axios-common";

const SpotInfoStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    spot: {
      name: "엄마손 맛집",
      latitude: "37.570425",
      longitude: "126.991316",
      address: "서울시 종로구 종로3가",
      bhour: {},
      menulist: {},
      tel: "010-1234-1234",
      review_cnt: 1
    }
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
            context.commit("setSpot", response.data.object);
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
