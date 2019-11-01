<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-80 md-small-size-100"
            style="margin: 0 auto; background: floralwhite;"
          >
            <tabs
              :tab-name="['기본 정보', '비밀번호 변경', '프로필 수정']"
              :tab-icon="['supervisor_account', 'enhanced_encryption', 'person_pin']"
              plain
              nav-pills-icons
              color-button="primary"
            >
              <template slot="tab-pane-1" style="background: white;">
                <div class="card" style="margin-bottom:16px;">
                  <div class="card-header" style="background: linen;">
                    <h4 style="color:black; font-weight: bold;">기본 정보</h4>
                  </div>
                  <div class="card-body">
                    <md-field>
                      <label>이름</label>
                      <md-input v-model="namelabel" type="text" :disabled="invalidform"></md-input>
                    </md-field>
                    <md-field>
                      <label>휴대폰</label>
                      <md-input v-model="phonelabel" type="text" :disabled="invalidform"></md-input>
                    </md-field>
                    <md-field>
                      <label>포인트</label>
                      <md-input v-model="money" type="text" :disabled="invalidform"></md-input>
                    </md-field>
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      v-on:click="chkbtn()"
                    >수정 활성화</button>
                    <button
                      type="button"
                      class="btn btn-outline-danger"
                      style="float: right;"
                      :disabled="invalidform"
                    >초기화</button>
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      style="float: right;  margin-right: 10px;"
                      :disabled="invalidform"
                      @click="userupdate()"
                    >수정</button>
                  </div>
                </div>
              </template>

              <template slot="tab-pane-2">
                <div class="card" style="margin-bottom:16px;">
                  <div class="card-header" style="background: linen;">
                    <h4 style="color:black; font-weight: bold;">비밀번호 변경</h4>
                  </div>
                  <div class="card-body">
                    <md-field>
                      <label>이전 비밀번호</label>
                      <md-input v-model="input.yesterpass" type="text"></md-input>
                    </md-field>
                    <md-field>
                      <label>신규 비밀번호</label>
                      <md-input v-model="input.newopass" type="text"></md-input>
                    </md-field>
                    <md-field>
                      <label>신규 비밀번호 번호</label>
                      <md-input v-model="input.chkpass" type="text"></md-input>
                    </md-field>
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      v-on:click="paswwordupdate()"
                    >비밀번호 변경</button>
                    <button
                      type="button"
                      class="btn btn-outline-danger"
                      v-on:click="stringclear()"
                      style="float: right;"
                    >초기화</button>
                  </div>
                </div>
              </template>
              <template slot="tab-pane-3">
                <div class="card" style="margin-bottom:16px;">
                  <div class="card-header" style="background: linen;">
                    <h4 style="color:black; font-weight: bold;">프로필 수정</h4>
                  </div>
                  <div class="card-body">
                    <label>사진등록</label>
                    <br />
                    <div class="ml-2 col-sm-6">
                      <img :src="img" id="preview" class="img-thumbnail" style="float: left;" />
                    </div>
                    <div class="ml-2 col-sm-6">
                      <div id="msg"></div>
                      <form method="post" id="image-form">
                        <input
                          type="file"
                          ref="inputRef"
                          name="img[]"
                          class="file"
                          accept="image/*"
                          @change="changeFile($event)"
                          style="display:none"
                        />
                        <div class="input-group my-3">
                          <input
                            type="text"
                            class="form-control"
                            disabled
                            placeholder="Upload File"
                            v-model="fileName"
                          />
                          <div class="input-group-append">
                            <button
                              type="button"
                              class="browse btn btn-primary"
                              :disabled="invalidpro"
                              v-on:click="uploadImg()"
                            >Browse...</button>
                          </div>
                        </div>
                      </form>
                    </div>
                    <md-field>
                      <label>소개글</label>
                      <md-textarea v-model="aboutme" :disabled="invalidpro"></md-textarea>
                    </md-field>
                    <md-field>
                      <label>인스타그램</label>
                      <md-input v-model="instagram" type="text" :disabled="invalidpro"></md-input>
                    </md-field>
                    <md-field>
                      <label>페이스북</label>
                      <md-input v-model="facebook" type="text" :disabled="invalidpro"></md-input>
                    </md-field>
                    <br />
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      v-on:click="profilebtn()"
                    >수정 활성화</button>
                    <button
                      type="button"
                      class="btn btn-outline-danger"
                      style="float: right;"
                      :disabled="invalidpro"
                    >초기화</button>
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      style="float: right;  margin-right: 10px;"
                      :disabled="invalidpro"
                      v-on:click="updateimage()"
                    >수정</button>
                  </div>
                </div>
              </template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { Tabs } from "@/components";
import { log } from "util";
import UserService from "@/services/UserService.js";
import EthereumService from "@/services/EthereumService.js";

export default {
  components: {
    LoginCard,
    Tabs
  },
  bodyClass: "login-page",
  data() {
    return {
      namelabel: "",
      phonelabel: "",
      money: "",
      input: {
        yesterpass: "",
        newopass: "",
        chkpass: ""
      },
      invalidform: true,
      invalidpro: true,
      fileName: "프로필등록..",
      imgName: "",
      img: "https://placehold.it/150x150",
      extension: "",
      aboutme: "",
      instagram: "",
      facebook: ""
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  mounted() {
    this.namelabel = this.$store.state.userinfo.userName;
    this.phonelabel = this.$store.state.userinfo.phone;
    this.instagram = this.$store.state.userinfo.sns1;
    this.facebook = this.$store.state.userinfo.sns2;
    this.aboutme = this.$store.state.userinfo.about;
    UserService.getImage(this.$store.state.userinfo.email).then(data => {
      this.img = data.data[0].imgData;
      this.fileName = data.data[0].imgName;
    });
    var address = this.$store.state.userinfo.walletAddress;
    EthereumService.getBalance(address, money => {
      this.money = money;
    });
  },
  methods: {
    chkbtn() {
      if (this.invalidform === true) {
        this.invalidform = false;
        return;
      } else if (this.invalidform === false) {
        this.invalidform = true;
        return;
      }
    },
    profilebtn() {
      if (this.invalidpro === true) {
        this.invalidpro = false;
        return;
      } else if (this.invalidpro === false) {
        this.invalidpro = true;
        return;
      }
    },
    stringclear() {
      this.input.yesterpass = "";
      this.input.newopass = "";
      this.input.chkpass = "";
    },
    userupdate() {
      if (this.namelabel === "" || this.phonelabel === "") {
        alert("모든 항목에 입력바랍니다...");
        return;
      }

      UserService.userupdate(
        this.namelabel,
        this.phonelabel,
        this.$store.state.user
      ).then(data => {
        if (data.config !== "") {
          var temp = JSON.parse(data.config.data);
          this.$store.commit("update", temp);
          alert("사용자 정보가 변경되었습니다.");
          this.$router.go("/");
        } else {
          alert("다시 확인해주세요...");
        }
      });
    },
    paswwordupdate() {
      var scope = this;
      if (this.$store.state.userinfo.password !== this.input.yesterpass) {
        alert("입력하신 비밀번호가 일치하지 않습니다.");
        return;
      }

      // 비밀번호를 공백으로 입력했는지 확인한다.
      if (this.input.newopass === "") {
        alert("신규 비밀번호를 입력해주세요.");
        return;
      }
      // 신규비밀번호와 신규비밀번호 확인이 일치하지 않는 경우를 확인한다.
      if (this.input.newopass !== this.input.chkpass) {
        alert("신규 비밀번호와 신규 비밀번호 확인이 일치하지 않습니다.");
        return;
      }

      UserService.passwordupdate(
        this.input.newopass,
        this.$store.state.user
      ).then(data => {
        if (data.config !== "") {
          var temp = JSON.parse(data.config.data);
          this.$store.commit("update", temp);
          sessionStorage.setItem("PW", temp.password);
          scope.$router.go(-1);
          alert("비밀번호 변경아 되었습니다");
        } else {
          alert("다시 확인해주세요....");
        }
      });
    },
    uploadImg() {
      this.$refs.inputRef.click();
    },
    changeFile(event) {
      let eamilLen = this.$store.state.userinfo.email.lastIndexOf("@");
      let random = Math.floor(Math.random() * 100000);
      this.imgName =
        this.$store.state.userinfo.email.substring(0, eamilLen) + "_" + random;

      this.fileName = event.target.files[0].name;
      let fileLen = this.fileName.length;
      let lastDot = this.fileName.lastIndexOf(".");
      this.extension = this.fileName.substring(lastDot, fileLen).toLowerCase();

      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = e => {
        this.img = e.target.result;
      };
    },
    updateimage() {
      var scope = this;

      UserService.delteImage(scope.$store.state.userinfo.email).then(data => {
        var imgName = scope.imgName;
        var imgData = scope.img;
        var extension = scope.extension;

        UserService.uploadImage({
          imgName: imgName,
          imgData: imgData,
          extension: extension,
          email: scope.$store.state.userinfo.email
        }).then(uploadImgData => {
          UserService.userprofile(
            scope.aboutme,
            scope.instagram,
            scope.facebook,
            scope.$store.state.user
          ).then(data => {
            if (data.config !== "") {
              var temp = JSON.parse(data.config.data);
              this.$store.commit("update", temp);
            } else {
              alert("다시 확인해주세요...");
            }
          });
          alert("프로필 수정이 되었습니다.");
        });
      });
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="css">
@import url("../../../assets/bootstrap/bootstrap.css");
</style>
