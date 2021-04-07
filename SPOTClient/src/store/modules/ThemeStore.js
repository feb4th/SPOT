import axios from "../../axios/axios-common";

const ThemeStore = {
  namespaced: true,
  state: {
    themelist: [
      {
        id: "1",
        name: "홍대/신촌 야경 SPOT",
        img: "../../assets/images/theme1.png",
        web: "https://blog.naver.com/the_trip/222288335721"
      },
      {
        id: "2",
        name: "당신이 몰랐던 이천 SPOT",
        img: "../../assets/images/theme2.png",
        web: "https://blog.naver.com/the_trip/222288507779"
      },
      {
        id: "3",
        name: "경주 벚꽃 SPOT",
        img: "../../assets/images/theme3.png",
        web: "https://blog.naver.com/the_trip/222300482896"
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
