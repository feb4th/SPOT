// Components import
// 여기에 필요한 Components를 import 해주세요
// import General from '../../components/Board/General';
import SpotDetail from "../../views/SpotDetail";
// ExampleRouter 정의 부분
const spotRoute = [
  {
    // 회원가입 route
    path: "/spotdetail/:spotid",
    name: "spotdetail",
    component: SpotDetail,
    props: false
  }
];

export default spotRoute;
