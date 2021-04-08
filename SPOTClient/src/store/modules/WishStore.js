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
      console.log("위시리스트" + user_id);
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
        .get("/wishcheck?spot_id=" + info.spot_id + "&user_id=" + info.user_id)
        .then(response => {
          if (response.data.message == "success") {
            console.log(response.data.result.wishlist_id);
            context.commit("setWishId", response.data.result.wishlist_id);
            return true;
          } else {
            context.commit("setWishId", "");
            return false;
          }
        })
        .catch(error => {
          context.commit("setWishId", "");
          console.log(error);
        });
    },

    // 위시리스트 추가(관광지)
    reqAddWish(context, info) {
      console.log(info);
      return axios
        .post("/wish", {
          id: info.spot_id,
          img: info.img,
          latitude: info.latitude,
          longitude: info.longitude,
          name: info.name,
          type: "1",
          user_id: info.user_id
        })
        .then(response => {
          if (response.data.message == "add wish") {
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
          if (response.data.message == "del wish success") {
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
