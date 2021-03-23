// Components import
import Signup from "../../views/SignUp";

// MemberRouter
const memberRoute = [
  {
    // 회원가입 route
    path: "/signup",
    name: "signup",
    component: Signup,
    props: false
  }
];

export default memberRoute;
