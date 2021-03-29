<template>
  <v-container>
    <div id="map" class="map"></div>
    <v-snackbar centered v-model="snackbar" timeout="2000" :color="color">
      {{ msg }}
    </v-snackbar>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

const FindStore = "FindStore";

export default {
  data() {
    return {
      snackbar: false,
      color: "",
      msg: ""
    };
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  computed: {
    ...mapGetters(FindStore, ["getSuggestList"])
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a2ef75e84ec5b6042fe36d22f21ed028";
      document.head.appendChild(script);
    },
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

      var options = {
        //지도를 생성할 때 필요한 기본 옵션

        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
      };

      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {
          var lat = position.coords.latitude, // 위도
            lng = position.coords.longitude; // 경도

          new kakao.maps.LatLng(lat, lng); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
          // 마커와 인포윈도우를 표시합니다
        });
      } else {
        alert(1);
      }

      // 마커 이미지의 이미지 주소입니다
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < this.getSuggestList.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        var lat = this.getSuggestList[i].lat;
        var lng = this.getSuggestList[i].lng;
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(lat, lng), // 마커를 표시할 위치
          title: this.getSuggestList[i].name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage // 마커 이미지
        });
        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: this.getSuggestList[i].name
        });
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          makeOverListener(map, marker, infowindow)
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          makeOutListener(infowindow)
        );

        marker.setMap(map);
      }
      // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
      function makeOverListener(map, marker, infowindow) {
        return function() {
          infowindow.open(map, marker);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(infowindow) {
        return function() {
          infowindow.close();
        };
      }
    }
  }
};
</script>

<style>
.map {
  width: 100%;
  height: 400px;
}
</style>
