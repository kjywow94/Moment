<template>
  <div class="wrapper">
    <div class="container">
      <div class="row">
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

        <div class="col-md-3">
          <button type="button" class="btn btn-success">동행 등록</button>
        </div>
      </div>

      <div class="row">
        <div>
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
              <tr v-for="item in accompanyList" v-bind:key="item.num">
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
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      region: "전체",
      accompanyList: [
        {
          num: "1",
          region: "유럽",
          content: "프라하 파티 구함",
          writer: "참치",
          date: "2019-10-19",
          views: "30"
        },
        {
          num: "2",
          region: "아시아",
          content: "방콕 파티 구함",
          writer: "참치",
          date: "2019-10-19",
          views: "30"
        },
        {
          num: "3",
          region: "북미",
          content: "벤쿠버 파티 구함",
          writer: "참치",
          date: "2019-10-19",
          views: "30"
        }
      ]
    };
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
label {
  margin-right: 10px;
  margin-left: 5px;
  margin-top: 0px;
  margin-bottom: 0px;
  padding-top: 6px;
}
</style>