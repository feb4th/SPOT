import axios from "../../axios/axios-common";

const CourseStore = {
  namespaced: true,
  state: {
    courseList: [],
    courseInfo: {}
  },
  getters: {
    getCourseList(state) {
      //멤버의 코스리스트 받아옴
      return state.courseList;
    },
    getCourseInfo(state) {
      //코스의 상세정보 받아오기
      return state.courseInfo;
    }
  },
  mutations: {
    setCourseList(state, payload) {
      state.courseList = payload;
    },
    setCourseInfo(state, payload) {
      state.courseInfo = payload;
    }
  },
  actions: {
    //회원 코스 이력 조회
    reqCourseList(context, email) {
      return axios
        .get("/user/course/" + email)
        .then(response => {
          console.log(response.data);
          if (response.message == "success") {
            context.commit("setCourseList", response.data.courses);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },
    //코스 상세정보 조회
    reqCourseInfo() {},

    //코스 생성
    reqCreateCourse() {},

    //코스 정보 수정
    reqModifyCourse() {},

    //코스 삭제
    reqDeleteCourse() {}
  }
};

export default CourseStore;
