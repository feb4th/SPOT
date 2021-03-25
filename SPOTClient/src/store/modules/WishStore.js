import axios from "../../axios/axios-common";

const WishStore = {
  namespaced: true,
  state: {
    wishList: []
  },
  getters: {
    getWishList(state) {
      //위시리스트 가져오기
      return state.wishList;
    }
  },
  mutations: {
    setWishList(state, payload) {
      state.wishList = payload;
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
    }
  }
};

export default WishStore;
