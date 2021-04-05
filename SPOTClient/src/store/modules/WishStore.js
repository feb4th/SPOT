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
    reqWishList(context, email) {
      return axios
        .get("/user/wish/" + email)
        .then(response => {
          console.log(response.data);
          if (response.message == "success") {
            context.commit("setWishList", response.data.wishlist);
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
            context.commit("setWishId", response.data.result.wishlist_id);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    // 위시리스트 추가(관광지)
    reqAddWish(context, info) {
      // 식당일때
      if (info.type == "1") {
        return axios
          .post("/wishrestraunt", {
            id: info.spot_id,
            email: info.email
          })
          .then(response => {
            if (response.message == "success") {
              context.commit("setWishId", response.follow_id);
              return true;
            } else return false;
          })
          .catch(error => {
            console.log(error);
          });
      }
      // 관광지 일때
      else {
        return axios
          .post("/wishplace", {
            id: info.spot_id,
            email: info.email
          })
          .then(response => {
            if (response.message == "success") {
              context.commit("setWishId", response.follow_id);
              return true;
            } else return false;
          })
          .catch(error => {
            console.log(error);
          });
      }
    },

    // wish 취소
    reqDeleteWish(context, wish_id) {
      return axios
        .delete("/wish/" + wish_id)
        .then(response => {
          if (response.message == "success") {
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
