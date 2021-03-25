// Components import
import Signup from "../../views/SignUp";
import FindPw from "../../views/FindPw";

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
  }
];

export default memberRoute;
