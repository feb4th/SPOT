// Components import
import AreaSelect from "../../views/AreaSelect";
import AreaSuggest from "../../views/AreaSuggest";

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
  }
];

export default mapRoute;
