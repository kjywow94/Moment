<template>
  <div class="wrapper">
    <div class="container">
      <div class="row list-top">
        <div class="col-md-5">
          <label for="region-select" class="float-left">지역</label>
          <select class="form-control float-left" id="region-select" v-model="region">
            <option selected="selected">전체</option>
            <option>국내</option>
            <option>아시아</option>
            <option>호주</option>
            <option>북미</option>
            <option>남미</option>
            <option>유럽</option>
            <option>아프리카</option>
          </select>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-3">
          <button type="button" class="btn btn-success">동행 등록</button>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <table class="table table-hover table-striped">
            <thead>
              <tr>
                <th>번호</th>
                <th>지역</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
                <th>조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in showList" v-bind:key="item.num">
                <td>{{item.num}}</td>
                <td>{{item.region}}</td>
                <td>{{item.content}}</td>
                <td>{{item.writer}}</td>
                <td>{{item.date}}</td>
                <td>{{item.views}}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="row">
        <div class="text-align-center">
          <pagination v-model="listPagination" :page-count="pageCount"></pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Pagination } from "@/components";
import AccompanyService from "@/services/AccompanyService.js";

export default {
  components: { Pagination },
  data() {
    return {
      region: "전체",
      listPagination: 1,
      pageCount: 1,
      itemCount: 10,
      allAccompanyList: [],
      regionList: [],
      showList: []
    };
  },
  methods: {
    swapPage: function() {
      this.showList = this.regionList.slice(
        this.itemCount * (this.listPagination - 1),
        this.itemCount * (this.listPagination - 1) + this.itemCount
      );
    }
  },
  mounted() {
    AccompanyService.getAccompanyList(result => {
      this.allAccompanyList = result;
      this.regionList = result;
      this.pageCount = Math.floor(this.regionList.length / 10 + 1);
      this.showList = this.regionList.slice(0, this.itemCount);
    });
  },
  watch: {
    listPagination: function() {
      this.swapPage();
    },
    region: function() {
      var scope = this;
      var selectRegion = [];
      if (this.region == "전체") {
        this.regionList = this.allAccompanyList;
      } else {
        this.allAccompanyList.forEach(function(element) {
          if (element.region == scope.region) {
            selectRegion.push(element);
          }
        });
        this.regionList = selectRegion;
      }
      this.listPagination = 1;
      scope.swapPage();
      this.pageCount = Math.floor(this.regionList.length / 10 + 1);
    }
  }
};
</script>
<style lang="scss" scoped>
@import url("../../assets/bootstrap/bootstrap.css");
#region-select {
  width: 60%;
}
.float-left {
  float: left;
}
.float-right {
  float: right;
}
label {
  margin-right: 10px;
  margin-left: 5px;
  margin-top: 0px;
  margin-bottom: 0px;
  padding-top: 7px;
}
.list-top {
  margin-bottom: 10px;
}
.text-align-center {
  margin: auto;
}
</style>