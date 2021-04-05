// Components import
import AreaSelect from "../../views/AreaSelect";
import AreaSuggest from "../../views/AreaSuggest";
import MakeCourse from "../../views/MakeCourse";

const mapRoute = [
  {
    // 지도 선택 route
    path: "/areaselect",
    name: "areaselect",
    component: AreaSelect,
    props: false
  },
  {
    // 지도 선택 후 다음 페이지 route
    path: "/areasuggest",
    name: "areasuggest",
    component: AreaSuggest,
    props: false
  },
  {
    // 코스 수정
    path: "/makecourse",
    name: "makecourse",
    component: MakeCourse,
    props: true
  }
];

export default mapRoute;
