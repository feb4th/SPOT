import axios from "../../axios/axios-common";

const ThemeStore = {
  namespaced: true,
  state: {
    themelist: [
      {
        id: "1",
        name: "연인을 위한 코스",
        img: ""
      },
      {
        id: "2",
        name: "친구와 함께",
        img: ""
      },
      {
        id: "3",
        name: "1박2일 제주도여행",
        img: ""
      },
      {
        id: "4",
        name: "서울근교여행",
        img: ""
      },
      {
        id: "5",
        name: "템플스테이",
        img: ""
      }
    ],
    theme: {}
  },
  getters: {
    getThemeList(state) {
      return state.themelist;
    },
    getThemeInfo(state) {
      return state.theme;
    }
  },
  mutations: {
    setThemeList(state, payload) {
      state.themelist = payload;
    },
    setTheme(state, payload) {
      state.theme = payload;
    }
  },
  actions: {
    /*
      메인페이지
    */
    //테마리스트 조회
    reqThemeList(context) {
      return axios
        .get("/recommend/theme")
        .then(response => {
          if (response.status) {
            context.commit("setThemeList", response.recommend_theme_list);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    //테마정보 조회
    reqTheme(context, themeid) {
      return axios
        .get("/recommend/theme" + themeid)
        .then(response => {
          if (response.status) {
            context.commit("setTheme", response.themeinfo);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};

export default ThemeStore;
