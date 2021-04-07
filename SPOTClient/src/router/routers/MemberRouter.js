// Components import
import Signup from "../../views/SignUp";
import FindPw from "../../views/FindPw";
import MyPage from "../../views/MyPage";
import MemberModify from "../../views/MemberModify";
import AfterEmail from "../../views/AfterEmail";

// MemberRouter
const memberRoute = [
  {
    // 회원가입 route
    path: "/signup",
    name: "signup",
    component: Signup,
    props: false
  },
  {
    // ID, Password 찾기 route
    path: "/findpw",
    name: "findpw",
    component: FindPw,
    props: false
  },
  {
    // 마이페이지 route
    path: "/mypage/:memberid",
    name: "mypage",
    component: MyPage,
    props: false
  },
  {
    // 회원정보수정 route
    path: "/member/modify/:memberid",
    name: "membermodify",
    component: MemberModify,
    props: false
  },
  {
    // 이메일 인증 완료 route
    path: "/validated",
    name: "validated",
    component: AfterEmail,
    props: true
  }
];

export default memberRoute;
