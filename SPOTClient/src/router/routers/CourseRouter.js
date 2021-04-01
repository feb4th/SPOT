// Components import
// 여기에 필요한 Components를 import 해주세요
// import General from '../../components/Board/General';
import courseDetail from "../../views/CourseDetail";

const CourseRoute = [
  {
    // 코스 상세정보
    path: "/course/detail/:course_id",
    name: "coursedetail",
    component: courseDetail,
    props: true
  }
];

export default CourseRoute;
