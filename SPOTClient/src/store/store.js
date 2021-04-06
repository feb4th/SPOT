import Vue from "vue";
import Vuex from "vuex";

//store modeule import
import ExampleStore from "./modules/ExampleStore";
import MemberStore from "./modules/MemberStore";
import FindStore from "./modules/FindStore";
import SpotInfoStore from "./modules/SpotInfoStore";
import CourseStore from "./modules/CourseStore";
import WishStore from "./modules/WishStore";
import ThemeStore from "./modules/ThemeStore";
import ReviewStore from "./modules/ReviewStore";
import SuggestStore from "./modules/SuggestStore";

//yarn add vuex-persistedstate 필요
import createPersistedState from "vuex-persistedstate"; //새로고침해도 store 정보가 사라지지 않게 해줌

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    ExampleStore,
    MemberStore,
    FindStore,
    SpotInfoStore,
    CourseStore,
    WishStore,
    ThemeStore,
    ReviewStore,
    SuggestStore
  },
  plugins: [
    createPersistedState({
      //store 내부 정보가 날아가지 않도록 해줌
      paths: ["MemberStore"] //원하는 모듈별로 추가가능
    })
  ]
});
