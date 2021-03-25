import axios from "../../axios/axios-common";

const FindStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    isSearch: false,
    searchList: {},
    suggestList: {}
  },

  getters: {
    getIsSearched(state) {
      return state.isSearch;
    },
    getSearchList(state) {
      return state.searchList;
    },
    getSuggestList(state) {
      return state.suggestList;
    }
  },
  mutations: {
    setIsSearched(state, isSearch) {
      state.isSearch = isSearch;
    },
    setSearchList(state, searchList) {
      state.searchList = searchList;
    },
    setSuggestList(state, suggestList) {
      state.suggestList = suggestList;
    }
  },

  actions: {
    reqSearch(context, name) {
      return axios
        .post("/placelist", {
          name: name
        })
        .then(response => {
          if (response.status === 200 && response.data.status === true) {
            context.commit("setIsSearched", true); // 검색된 상태로 변경
            context.commit("setSearchList", response.data.object.stores);
            return {
              result: true,
              msg: "검색 되었습니다",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "검색에 실패하였습니다.",
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
    reqSuggest(context, email) {
      return axios
        .post("/recommend/place", {
          email: email
        })
        .then(response => {
          if (response.status === 200 && response.data.status === true) {
            context.commit(
              "setSuggestList",
              response.data.object.recommend_place_list
            );

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

export default FindStore;
