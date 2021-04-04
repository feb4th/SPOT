import axios from "../../axios/axios-common";

const SuggestStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    suggestList: [
      {
        spot_id: "1",
        name: "좋은 장소",
        lat: "33.450705",
        lng: "126.570677",
        img: ""
      }
    ],
    wishList: [
      {
        spot_id: "1",
        name: "좋은 장소",
        lat: "33.450705",
        lng: "126.570677",
        img: ""
      }
    ]
  },
  getters: {
    getSuggestList(state) {
      return state.suggestList;
    },
    getWishList(state) {
      return state.wishList;
    }
  },
  mutations: {
    setSuggestList(state, suggestList) {
      state.suggestList = suggestList;
    },
    setWishList(state, wishList) {
      state.wishList = wishList;
    }
  },

  actions: {
    reqWish(context, memberid) {
      return axios
        .get("/user/wish/" + memberid)
        .then(response => {
          if (response.status === 200 && response.data.status === true) {
            context.commit("setWishList", response.data.object.place_list); // 여기 데이터 리턴 변수명 확인해야함.
            return {
              result: true,
              msg: "위시리스트 가져왔습니다.",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "위시리스트가 없습니다.",
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
    reqSuggest(context, list) {
      return axios
        .post("/place", {
          list: list
        })
        .then(response => {
          if (response.status === 200 && response.data.status === true) {
            context.commit("setSuggestList", response.data.object.place_list);
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
