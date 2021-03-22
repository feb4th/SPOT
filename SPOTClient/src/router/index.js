import Vue from "vue";
import VueRouter from "vue-router";


import Main from "../views/MainPage.vue";
import NotFound from "../components/error/NotFound.vue";
import exampleRoute from "./routers/ExampleRouter";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: Main
  },
  {
      path: '*',
      component: NotFound
  }
   // concat을 통한 module화 한 routes 추가
].concat(exampleRoute);

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior () {
      return { x: 0, y: 0 }
    }
});

export default router;
