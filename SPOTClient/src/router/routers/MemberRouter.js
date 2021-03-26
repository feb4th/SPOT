// Components import
import Signup from "../../views/SignUp";
import FindPw from "../../views/FindPw";
import MyPage from "../../views/MyPage";
import MemberModify from "../../views/MemberModify";
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
    path: "/mypage/:email",
    name: "mypage",
    component: MyPage,
    props: false
  },
  {
    // 회원정보수정 route
    path: "/member/modify/:email",
    name: "membermodify",
    component: MemberModify,
    props: false
  }
];

export default memberRoute;
