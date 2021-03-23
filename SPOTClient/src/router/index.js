import Vue from "vue";
import VueRouter from "vue-router";

import Main from "../views/MainPage.vue";
import NotFound from "../components/error/NotFound.vue";
import Login from "../views/Login.vue";

//import exampleRoute from "./routers/ExampleRouter";
import memberRoute from "./routers/MemberRouter";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: Main,
    //메인페이지로 가기 전 로그아웃 상태면 login페이지로 이동
    beforeEnter(to, from, next) {
      if (!localStorage.getItem("access-token")) {
        next({ name: "login" });
      } else {
        next();
      }
    }
  },
  {
    path: "*",
    component: NotFound
  },
  {
    path: "/login",
    name: "login",
    component: Login
  }
  // concat을 통한 module화 한 routes 추가
] //.concat(exampleRoute)
  .concat(memberRoute);

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior() {
    return { x: 0, y: 0 };
  }
});

export default router;
