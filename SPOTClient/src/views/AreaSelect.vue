<template>
  <v-app style="position:fixed; overflow:hidden; scroll:no">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <div class="ml-3">
                <h1>지역을</h1>
                <h1>선택해주세요!</h1>
              </div>
              <v-row style="height:90%;">
                <v-col cols="1"></v-col>
                <v-col cols="10"
                  ><div id="map-wrapper" class="map-wrapper"></div
                ></v-col>
                <v-col cols="1" class="ma-auto"
                  ><v-btn depressed icon v-if="btnNumber > 0" @click="onClick"
                    ><v-icon x-large>mdi-arrow-right</v-icon></v-btn
                  ></v-col
                >
              </v-row>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import * as d3 from "d3";
const MAP_GEOJSON = require("./map3.json");

import { mapActions } from "vuex";
const SuggestStore = "SuggestStore";

export default {
  components: {},
  props: {},
  data() {
    return {
      province: undefined,
      currentProvince: undefined,
      //mapArea: MAP_AREA,
      localSeatInfo: null,

      selectRoom: [],
      selectName: [
        "강원도",
        "경기도",
        "경상남도",
        "경상북도",
        "광주광역시",
        "대구광역시",
        "대전광역시",
        "부산광역시",
        "서울특별시",
        "세종특별자치시",
        "울산광역시",
        "인천광역시",
        "전라남도",
        "전라북도",
        "제주특별자치도",
        "충청남도",
        "충청북도"
      ],
      finalList: [],
      btnNumber: 0
    };
  },
  computed: {},
  created() {
    for (var i = 0; i < 17; i++) {
      this.selectRoom.push(0);
    }
  },
  mounted() {
    this.drawMap();
  },
  methods: {
    ...mapActions(SuggestStore, ["reqSuggest"]),
    onClick() {
      this.finalList = [];
      for (var i = 0; i < 17; i++) {
        if (this.selectRoom[i] == 1) {
          this.finalList.push(this.selectName[i]);
        }
      }
      if (this.finalList.length == 0) {
        alert("0이면 못보냄");
      }
      // console.log(this.finalList);
      const finalData = JSON.parse(JSON.stringify(this.finalList));
      console.log(finalData); // 요 형식으로 보내면 됨.

      // 여기서 추천 리스트를 받기 위한 요청을 보내고 라우터 처리를 해야할 듯.
      // 일단 백으로 추천 리스트를 받아와야함.
      this.reqSuggest(finalData).then(response => {
        // 정상적인 요청이라면,
        if (response) {
          // 느낌표가 맞나? 테스트 해봐야 할 듯.
          this.$router.push("/areasuggest");
        } else alert(response.msg);
      });
    },
    drawMap() {
      // 지도정보
      const geojson = MAP_GEOJSON;
      // 지도의 중심점 찾기
      const center = d3.geoCentroid(geojson);
      console.log(center);
      let centered = undefined;

      // 현재의 브라우저의 크기 계산
      const divWidth = document.getElementById("map-wrapper").clientWidth;
      const width = divWidth < 1000 ? divWidth * 0.9 : 1000;
      const height = width * 0.9;

      // 지도를 그리기 위한 svg 생성
      const svg = d3
        // .select('.d3')
        .select(".map-wrapper")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

      // 배경 그리기
      const background = svg
        .append("rect")
        .attr("class", "background")
        .attr("width", width)
        .attr("height", height);
      console.log(background);
      // 지도가 그려지는 그래픽 노드(g) 생성
      const g = svg.append("g");
      // const effectLayer = g.append('g').classed('effect-layer', true);
      // 지도가 그려지는 그래픽 노드
      const mapLayer = g.append("g").classed("map-layer", true);
      // 아이콘이 그려지는 그래픽 노드
      //const iconsLayer = g.append("g").classed("icons-layer", true);

      // 지도의 출력 방법을 선택(메로카토르)
      let projection = d3
        .geoMercator()
        .scale(1)
        .translate([0, 0]);

      // svg 그림의 크기에 따라 출력될 지도의 크기를 다시 계산
      const path = d3.geoPath().projection(projection);
      //console.log(path);
      const bounds = path.bounds(geojson);
      const widthScale = (bounds[1][0] - bounds[0][0]) / width;
      const heightScale = (bounds[1][1] - bounds[0][1]) / height;
      const scale = 0.95 / Math.max(widthScale, heightScale);
      const xoffset =
        width / 2 - (scale * (bounds[1][0] + bounds[0][0])) / 2 + 0;
      const yoffset =
        height / 2 - (scale * (bounds[1][1] + bounds[0][1])) / 2 + 0;
      const offset = [xoffset, yoffset];
      projection.scale(scale).translate(offset);

      const color = d3
        .scaleLinear()
        .domain([1, 20])
        .clamp(true)
        .range(["white", "white"]); // 지도색 바뀜.

      // Get province name length
      function nameLength(d) {
        const n = nameFn(d);
        return n ? n.length : 0;
      }

      // Get province name
      function nameFn(d) {
        return d && d.properties ? d.properties.name : null;
      }

      // Get province color
      //const _this = this;
      function fillFn(d) {
        return color(nameLength(d));
      }

      // data()에 선언해둔 변수 접근.
      let self = this;

      function clicked(d) {
        const selectIdx = d3.select(this)._groups[0][0].__data__.properties
          .select_idx; // 인덱스를 가져옴.
        //console.log(selectIdx);
        //console.log(self.selectRoom.length);
        // console.log(self.selectRoom[selectIdx]);
        if (self.selectRoom[selectIdx] == 0) {
          // 선택되지 않은 곳을 활성화
          d3.select(this).style("fill", "#f7cac9"); //활성화 시의 색
          self.selectRoom[selectIdx] = 1;
          self.selectName.push(
            d3.select(this)._groups[0][0].__data__.properties.CTP_KOR_NM // 선택한 지역 이름.
          );
          self.btnNumber++;
        } else {
          //선택 된 곳을 비활성화
          d3.select(this).style("fill", "white"); //비활성화 시의 색
          self.selectRoom[selectIdx] = 0;
          self.btnNumber--;
        }

        var x, y, k;

        // Compute centroid of the selected path
        if (d && centered !== d) {
          var centroid = path.centroid(d);
          console.log(centroid);
          x = centroid[0];
          y = centroid[1];
          k = 4;
          centered = d;
        } else {
          x = width / 2;
          y = height / 2;
          k = 1;
          centered = null;
        }
        console.log(x + y + k);
      }

      function mouseover() {
        // 마우스 올릴 시에 툴팁처럼 띄워줘야 함.
        tooltip
          .text(d3.select(this)._groups[0][0].__data__.properties.CTP_KOR_NM)
          .style("visibility", "visible");
      }

      function mouseout() {
        tooltip.style("visibility", "hidden");
      }

      function mousemove() {
        tooltip
          .style("top", event.pageY - 10 + "px")
          .style("left", event.pageX + 10 + "px");
      }

      let tooltip = d3
        .select("body")
        .append("div")
        .style("position", "absolute")
        .style("border", "0 none")
        .style("border-radius", "4px 4px 4px 4px")
        .style("background-color", "white")
        .style("padding", "5px")
        .style("text-align", "center")
        .style("font-size", "11px")
        .style("z-index", "10")
        .style("visibility", "hidden")
        .text("a simple tooltip");

      // 지도 그리기
      mapLayer
        .selectAll("path")
        .data(geojson.features)
        .enter()
        .append("path")
        .attr("d", path)
        .attr("vector-effect", "non-scaling-stroke")
        .style("fill", fillFn)
        .on("mouseover", mouseover)
        .on("mouseout", mouseout)
        .on("mousemove", mousemove)
        .on("click", clicked);
    }
  }
};
</script>

<style lang="scss">
.map-wrapper {
  position: relative;
  text-align: center;

  .background {
    fill: white;
    //fill: transparent;
    pointer-events: all;
  }

  .map-layer {
    fill: grey;
    stroke: grey;
    stroke-width: 2px;
  }
}
</style>
