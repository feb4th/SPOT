import axios from "../../axios/axios-common";

const MemberStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    isLogin: false, // 로그인 여부
    memberInfo: {}
  },

  getters: {
    getIsLogined(state) {
      //로그인상태 받아옴
      return state.isLogin;
    },
    getMemberInfo(state) {
      //멤버 정보 받아옴
      return state.memberInfo;
    }
  },
  mutations: {
    setIsLogined(state, isLogin) {
      //로그인상태 설정
      state.isLogin = isLogin;
    },

    setMemberInfo(state, memberInfo) {
      state.memberInfo = memberInfo;
    },
    logout(state) {
      //로그아웃 시 변수 초기화
      state.isLogin = false;
      state.memberInfo = {};
    }
  },

  actions: {
    /*
      Login Component
    */
    // 로그인
    Login(context, member) {
      return axios
        .post("/login", {
          //유저정보를 보냄
          email: member.email,
          pw: member.pw
        })
        .then((response) => {
          //성공 시 변수 재설정
          if (response.status === 200 && response.data.status === true) {
            //토큰생성 및 저장
            let token = response.data["access-token"];
            sessionStorage.setItem("access-token", token); //로컬스토리지에 토큰저장

            context.commit("setIsLogined", true); //로그인 상태 true로 변환
            context.commit("setMemberInfo", response.data.object); //멤버정보 저장

            return {
              result: true,
              msg: member.email + "님 환영합니다",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "로그인에 실패하였습니다.",
              color: "warning"
            };
          }
        })
        .catch((error) => {
          if (error.response.status === 401) {
            if (error.response.data.data === "email")
              return {
                result: false,
                msg: "이메일 인증 후 진행해주세요",
                color: "warning"
              };
            else if (error.response.data.data === "fail")
              return {
                result: false,
                msg: "아이디나 비밀번호가 틀립니다",
                color: "warning"
              };
          } else {
            console.log(error);
            return {
              result: false,
              msg: "아이디나 비밀번호가 틀립니다",
              color: "warning"
            };
          }
        });
    },
    // 로그아웃
    Logout(context) {
      context.commit("logout");
      sessionStorage.removeItem("access-token");
    },
    /*
      Signup Component
    */
    // 회원가입
    reqSignup(context, info) {
      return axios
        .post("/signup", {
          email: info.email,
          name: info.name,
          phone: info.phone,
          pw: info.pw
        })
        .then((response) => {
          console.log(response.data);
          if (response.status === 200 && response.data.status === true) {
            return true;
          }
        })
        .catch((error) => {
          // 이메일 중복
          if (error.response.status === 409) {
            return false;
          } else console.log(error);
        });
    },
    // 이메일 인증 요청
    reqSignupValidation(context, email) {
      return axios
        .post("/signup/validation", email)
        .then((response) => {
          if (response.status) return true;
          return false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 최종 회원 가입 완성(이메일 링크 누를 때)
    reqClickEmailLink(context, info) {
      return axios
        .post("/signup/validated", {
          email: info.email,
          authkey: info.authkey
        })
        .then((response) => {
          if (response.status) return true;
          return false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    /*
      FindIdPw Component
    */
    // ID 찾기
    reqFindId(context, info) {
      return axios
        .post("/member/id", {
          name: info.name,
          phone: info.phone
        })
        .then((response) => {
          if (response.data.status) {
            // 데이터 존재
            return {
              result: true,
              msg: "이메일은 " + response.data.data + " 입니다"
            };
          }

          // 데이터 존재 x
          return { result: false, msg: "회원정보가 존재하지 않습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 비밀번호 찾기
    reqFindPw(context, info) {
      return axios
        .post("/member/pw", {
          email: info.email,
          name: info.name,
          phone: info.phone
        })
        .then((response) => {
          if (response.data.status) {
            // 데이터 존재
            return {
              result: true,
              msg: "이메일로 임시비밀번호가 발송되었습니다"
            };
          }

          // 데이터 존재 x
          return { result: false, msg: "회원정보가 존재하지 않습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },

    reqMemberInfo(context, no) {
      return axios
        .get("/member/" + no)
        .then((response) => {
          if (response.data.status) {
            context.commit("setMemberInfo", response.data.object);
            return { result: true, msg: "회원정보조회 성공" };
          }
          return { result: false, msg: "회원정보가 존재하지 않습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
};

export default MemberStore;
