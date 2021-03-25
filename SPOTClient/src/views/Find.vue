<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12" sm="2"> </v-col>

          <v-col cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <!--  -->
              <search-bar />
              <search-list v-if="getIsSearched" />
              <suggest-list v-else />
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="2"> </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import SearchBar from "../components/Find/SearchBar.vue";
import SearchList from "../components/Find/SearchList.vue";
import SuggestList from "../components/Find/SuggestList.vue";

import { mapActions, mapGetters } from "vuex";
const FindStore = "FindStore";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(FindStore, ["getIsSearched"])
  },
  components: {
    SearchBar,
    SearchList,
    SuggestList
  },
  methods: {
    ...mapActions(FindStore, ["reqSuggest"])
  }
};
</script>

<style scoped></style>
