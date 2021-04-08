<template>
  <v-container>
    <v-row justify="center" class="ma-4">
      <div id="map" class="map"></div>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
const CourseStore = "CourseStore";

export default {
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  computed: {
    ...mapGetters(CourseStore, ["getCourseInfo"])
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
      //console.log(this.getCourseInfo[0].latitude);
      var options = {
        //지도를 생성할 때 필요한 기본 옵션

        center: new kakao.maps.LatLng(
          this.getCourseInfo[0].latitude,
          this.getCourseInfo[0].longitude
        ), //지도의 중심좌표.
        level: 10 //지도의 레벨(확대, 축소 정도)
      };
      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < this.getCourseInfo.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(
            this.getCourseInfo[i].latitude,
            this.getCourseInfo[i].longitude
          ), // 마커를 표시할 위치
          image: markerImage // 마커 이미지
        });
        var infowindow = new kakao.maps.InfoWindow({
          content: this.getCourseInfo[i].name
        });
        infowindow.open(map, marker);
        marker.setMap(map);
      }
    }
  }
};
</script>

<style></style>
