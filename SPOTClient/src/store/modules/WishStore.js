import axios from "../../axios/axios-common";

const WishStore = {
  namespaced: true,
  state: {
    wishList: [],
    wishlist_id: ""
  },
  getters: {
    getWishList(state) {
      //위시리스트 가져오기
      return state.wishList;
    },
    getWishListId(state) {
      return state.wishlist_id;
    }
  },
  mutations: {
    setWishList(state, payload) {
      state.wishList = payload;
    },
    setWishListId(state, payload) {
      state.wishlist_id = payload;
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
        .post("/followcheck", {
          store_id: info.store_id,
          user_id: info.email
        })
        .then(response => {
          if (response.message == "success") {
            context.commit("setWishListId", response.follow_id);
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
              context.commit("setWishListId", response.follow_id);
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
              context.commit("setWishListId", response.follow_id);
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
            context.commit("setWishListId", "");
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
