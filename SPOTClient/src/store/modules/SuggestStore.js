import axios from "../../axios/axios-common";

const SuggestStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    suggestList: [
      {
        id: 33751,
        name: "공원 앞 커피",
        branch: null,
        area: "부평",
        tel: "070-8886-1500",
        address: "인천광역시 부평구 부평동 284-139",
        latitude: "37.488900",
        longitude: "126.712000",
        category_list: [{ category: "브런치카페" }, { category: "카페" }],
        menu_list: [{ menu: "파스타, 샐러드", price: 0 }],
        bhour_list: [
          {
            type: 1,
            week_type: 1,
            mon: 1,
            tue: 1,
            wed: 1,
            thu: 1,
            fri: 1,
            sat: 1,
            sun: 1,
            start_time: "11:00:00",
            end_time: "22:00:00",
            etc: ""
          }
        ],
        review_cnt: 0,
        review_list: []
      },
      {
        _id: {
          $oid: "605c39134635901e7097ce13"
        },
        id: 18,
        name: "진삼미 샌드위치",
        branch: "",
        area: "전주",
        tel: "063-221-9801",
        address: "전라북도 전주시 완산구 효자동1가 652 상산고등학교앞",
        latitude: "35.803488",
        longitude: "127.115123",
        category_list: [
          {
            category: "샌드위치"
          },
          {
            category: "쥬스"
          }
        ],
        menu_list: [],
        bhour_list: [],
        review_cnt: 1,
        review_list: [
          {
            writer_info: {
              id: 389728,
              gender: "여",
              born_year: "1993"
            },
            review_info: {
              id: 2,
              score: 5,
              content:
                "샌드위치 내용물도 알차게 들어있고 맛있었어요 가성비 추천",
              reg_time: "1970-01-01 00:00:00"
            }
          }
        ]
      }
    ],
    wishList: [
      {
        spot_id: "1",
        name: "좋은 장소",
        lat: "33.450705",
        lng: "126.570677",
        img: ""
      }
    ]
  },
  getters: {
    getSuggestList(state) {
      return state.suggestList;
    },
    getWishList(state) {
      return state.wishList;
    }
  },
  mutations: {
    setSuggestList(state, suggestList) {
      state.suggestList = suggestList;
    },
    setWishList(state, wishList) {
      state.wishList = wishList;
    }
  },

  actions: {
    reqWish(context, memberid) {
      return axios
        .get("/user/wish/" + memberid)
        .then(response => {
          if (response.status === 200 && response.data.status === true) {
            context.commit("setWishList", response.data.object.place_list); // 여기 데이터 리턴 변수명 확인해야함.
            return {
              result: true,
              msg: "위시리스트 가져왔습니다.",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "위시리스트가 없습니다.",
              color: "warning"
            };
          }
        })
        .catch(error => {
          console.log(error);
          return {
            result: false,
            msg: "에러 발생",
            color: "warning"
          };
        });
    },
    reqSuggest(context, list) {
      return axios
        .post("/place", {
          list: list
        })
        .then(response => {
          if (response.status === 200 && response.data.status === true) {
            context.commit("setSuggestList", response.data.object.place_list);
            return {
              result: true,
              msg: "추천 데이터 가져왔습니다.",
              color: "success"
            };
          } else {
            return {
              result: false,
              msg: "추천 데이터가 없습니다.",
              color: "warning"
            };
          }
        })
        .catch(error => {
          console.log(error);
          return {
            result: false,
            msg: "에러 발생",
            color: "warning"
          };
        });
    }
  }
};

export default SuggestStore;
