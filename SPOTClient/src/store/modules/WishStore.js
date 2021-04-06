import axios from "../../axios/axios-common";

const WishStore = {
  namespaced: true,
  state: {
    wishList: [],
    wish_id: ""
  },
  getters: {
    getWishList(state) {
      //위시리스트 가져오기
      return state.wishList;
    },
    getWishId(state) {
      return state.wish_id;
    }
  },
  mutations: {
    setWishList(state, payload) {
      state.wishList = payload;
    },
    setWishId(state, payload) {
      state.wish_id = payload;
    }
  },
  actions: {
    //회원 위시리스트 조회
    reqWishList(context, user_id) {
      return axios
        .get("/wish/" + user_id)
        .then(response => {
          if (response.data.message == "success") {
            context.commit("setWishList", response.data.result);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    // 위시리스트 여부 조회
    reqCheckWish(context, info) {
      return axios
        .get(
          "/wishcheck?spot_id=" +
            info.get("spot_id") +
            "&user_id=" +
            info.get("user_id")
        )
        .then(response => {
          if (response.data.message == "success") {
            context.commit("setWishId", response.data.result.wishlist_id);
            console.log("조회성공" + response.data.result.wishlist_id);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    // 위시리스트 추가(관광지)
    reqAddWish(context, info) {
      return axios
        .post("/wish", {
          spot_id: info.spot_id,
          type: "0",
          user_id: info.user_id
        })
        .then(response => {
          if (response.data.message == "success") {
            context.commit("setWishId", response.data.result.wishlist_id);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    // wish 취소
    reqDeleteWish(context, wishlist_id) {
      return axios
        .delete("/wish/" + wishlist_id)
        .then(response => {
          if (response.data.message == "success") {
            context.commit("setWishId", "");
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};

export default WishStore;
