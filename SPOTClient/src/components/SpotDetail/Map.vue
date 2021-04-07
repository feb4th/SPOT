<template>
  <v-container>
    <v-row>
      <v-card elevation="0">
        <v-card-title style="margin-left: 2em;">
          <v-icon x-large>mdi-check</v-icon>
          <strong>주소</strong>
        </v-card-title>
      </v-card>
    </v-row>
    <v-row justify="center" class="ma-6">
      <div id="map" class="map"></div>
    </v-row>
    <v-row style="margin-left: 1.5em;">
      <v-card elevation="0">
        <v-card-title>
          <v-card-text v-if="this.$route.params.spotid < 500000">
            {{ getSpot.address }}
          </v-card-text>
          <v-card-text v-else> {{ getSpot.new_address }} </v-card-text>
        </v-card-title>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
const SpotInfoStore = "SpotInfoStore";

export default {
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  computed: {
    ...mapGetters(SpotInfoStore, ["getSpot"])
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
      var lat, lng;
      if (this.$route.params.spotid < 500000) {
        lat = this.getSpot.latitude;
        lng = this.getSpot.longitude;
      } else {
        lat = this.getSpot.lat;
        lng = this.getSpot.lng;
      }
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
        level: 4 //지도의 레벨(확대, 축소 정도)
      };
      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: new kakao.maps.LatLng(lat, lng), // 마커를 표시할 위치
        image: markerImage // 마커 이미지
      });
      var infowindow = new kakao.maps.InfoWindow({
        content: this.getSpot.name
      });
      infowindow.open(map, marker);
      marker.setMap(map);
    }
  }
};
</script>

<style></style>
