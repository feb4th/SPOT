// Components import
// 여기에 필요한 Components를 import 해주세요
// import General from '../../components/Board/General';
import SpotDetail from "../../views/SpotDetail";
import MainPage from "../../views/MainPage";
import MakeCourse from "../../views/MakeCourse";
// ExampleRouter 정의 부분
const spotRoute = [
  {
    // 회원가입 route
    path: "/spotdetail/:spotid",
    name: "spotdetail",
    component: SpotDetail,
    props: false
  },
  {
    path: "/main",
    name: "mainpage",
    component: MainPage,
    props: false
  },
  {
    path: "/course",
    name: "course",
    component: MakeCourse,
    props: false
  }
];

export default spotRoute;
