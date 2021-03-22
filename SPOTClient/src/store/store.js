import Vue from "vue";
import Vuex from "vuex";

//store modeule import
import ExampleStore from './modules/ExampleStore';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    ExampleStore
  },
  plugins: [
    
  ]
});
