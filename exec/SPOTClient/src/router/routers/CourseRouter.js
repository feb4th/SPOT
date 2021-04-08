// Components import
// 여기에 필요한 Components를 import 해주세요
// import General from '../../components/Board/General';
import courseDetail from "../../views/CourseDetail";
import makeCourse from "../../views/AreaSelect";

const CourseRoute = [
  {
    // 코스 상세정보
    path: "/course/detail/:course_id",
    name: "coursedetail",
    component: courseDetail,
    props: true
  },
  {
    // 코스 상세정보
    path: "/course",
    name: "makecourse",
    component: makeCourse,
    props: true
  }
];

export default CourseRoute;
