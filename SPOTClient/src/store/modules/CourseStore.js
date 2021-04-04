import axios from "../../axios/axios-common";

const CourseStore = {
  namespaced: true,
  state: {
    courseList: [],
    courseInfo: [
      {
        course_id: "1",
        spot_id: "1",
        latitude: "37.570425",
        longitude: "126.991316",
        name: "스팟명",
        course_name: "기본코스"
      },
      {
        spot_id: "2",
        latitude: "37.571525",
        longitude: "126.991316",
        name: "두번째 스팟",
        course_name: "기본코스"
      }
    ],
    modify: false //현재 수정중인지 여부
  },
  getters: {
    getCourseList(state) {
      //멤버의 코스리스트 받아옴
      return state.courseList;
    },
    getCourseInfo(state) {
      //코스의 상세정보 받아오기
      return state.courseInfo;
    },
    getModify(state) {
      return state.modify;
    }
  },
  mutations: {
    setCourseList(state, payload) {
      state.courseList = payload;
    },
    setCourseInfo(state, payload) {
      state.courseInfo = payload;
    },
    setModify(state) {
      state.modify = !state.modify;
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
    reqCourseInfo(context, course_id) {
      return axios
        .get("/course/detail/" + course_id)
        .then(response => {
          console.log(response.data);
          if (response.message == "success") {
            context.commit("setCourseInfo", response.data.course);
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },
    reqChangeStatus(context) {
      return context.commit("setModify");
    },
    //코스 생성
    reqCreateCourse() {},

    //코스 정보 수정
    reqModifyCourse(context, info) {
      return axios
        .put("/course/detail/" + info.course_id, { info: info })
        .then(response => {
          console.log(response.data);
          if (response.message == "success") {
            context.commit("setModify");
            return true;
          } else return false;
        })
        .catch(error => {
          console.log(error);
        });
    },

    //코스 삭제
    reqDeleteCourse() {}
  }
};

export default CourseStore;
