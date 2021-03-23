// Components import
// 여기에 필요한 Components를 import 해주세요
// import General from '../../components/Board/General';
import findpage from "../../views/Find";

// ExampleRouter 정의 부분
const findRoute = [
  /*
        하나의 라우터는 이런 형식으로 정의합니다
        {
            path: address 뒤에 붙을 path -> http://localhost:8080/{path}
            주의! : path는 절대경로로 작성을 부탁드립니다
            name: router의 name
            component: routing될 component
            props: 라우팅 될 곳에 props를 전달하면 true, 그렇지 않으면 false
        }
        ex)
        {
            path: '/board/general',
            name: 'general',
            component: General,
            props: true,
        },
        밑으로 이어 붙이면 됩니다
    */
  {
    // 검색페이지
    path: "/find",
    name: "findpage",
    component: findpage,
    props: false
  }
];

export default findRoute;
