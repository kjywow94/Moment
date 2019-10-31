<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout" >
            <div class="md-layout-item md-size-80 md-small-size-100" style="margin: 0 auto;">
            <tabs
                :tab-name="['사용자수정', '비밀번호변경', '프로필수정']"
                :tab-icon="['supervisor_account', 'enhanced_encryption', 'person_pin']"
                plain
                nav-pills-icons
                color-button="primary"
            >
                <!-- here you can add your content for tab-content -->
                <template slot="tab-pane-1" style="background: white;">
                    <div class="card" style="margin-bottom:16px;">
                        <div class="card-header" style="background: linen;">
                          <h4 style="color:black; font-weight: bold;">사용자 변경</h4>
                        </div>
                        <div class="card-body">
                            <md-field >
                                <label>이름</label>
                                <md-input v-model="namelabel" type="text" :disabled="invalidform"></md-input>
                            </md-field>
                            <md-field >
                                <label>휴대폰</label>
                                <md-input v-model="phonelabel" type="text" :disabled="invalidform"></md-input>
                            </md-field>
                        <button type="button" class="btn btn-outline-success" v-on:click="chkbtn()">수정 활성화</button>
                        <button type="button" class="btn btn-outline-danger" style="float: right;" :disabled="invalidform">초기화</button>
                        <button type="button" class="btn btn-outline-success" style="float: right;  margin-right: 10px;" :disabled="invalidform" @click="userupdate()">수정</button>
                        </div>
                    </div>


                </template>
             
                <template slot="tab-pane-2">
                    <div class="card" style="margin-bottom:16px;">
                        <div class="card-header" style="background: linen;">
                          <h4 style="color:black; font-weight: bold;">비밀번호를 변경해주세요</h4>
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
                        <button type="button" class="btn btn-outline-success" v-on:click="paswwordupdate()">비밀번호 변경</button>
                        <button type="button" class="btn btn-outline-danger" v-on:click="stringclear()" style="float: right;">초기화</button>
                        </div>
                    </div>
                </template>
                <template slot="tab-pane-3">
                    111
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
import { log } from 'util';
import UserService from '@/services/UserService.js';

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
      input: {
        yesterpass : "",
        newopass : "",
        chkpass : ""
      },
      invalidform: true
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  mounted() {
    console.log(this.$store.state.userinfo);
    this.emaillabel = this.$store.state.userinfo.email;
    this.namelabel = this.$store.state.userinfo.userName;
    this.phonelabel = this.$store.state.userinfo.phone;
    
  },
  methods: {
    chkbtn(){
        
        if(this.invalidform === true){
            this.invalidform = false;
            return;
        }
        else if(this.invalidform === false){
            this.invalidform = true;
            return;
        }
       
            
    },
    stringclear() {
      this.input.yesterpass = "";
      this.input.newopass = "";
      this.input.chkpass = "";
    },
    userupdate() {
        if(this.namelabel === "" || this.phonelabel === ""){
            alert("모든 항목에 입력바랍니다...");
            return;
        }
        
        UserService.userupdate(this.namelabel, this.phonelabel, this.$store.state.user)
            .then(data => {
                if(data.config !== ""){
                    var temp = JSON.parse(data.config.data);
                    this.$store.commit("update", temp);
                    alert("사용자 정보가 변경되었습니다.");
                    this.$router.go('/');
                }
                else{
                    alert("다시 확인해주세요...");
                }
            })
    },
    paswwordupdate() {
      var scope = this;
      if(this.$store.state.userinfo.password !== this.input.yesterpass){
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
      
      UserService.passwordupdate(this.input.newopass, this.$store.state.user)
        .then(data => {          
          if(data.config !== ""){
            var temp = JSON.parse(data.config.data)
            this.$store.commit("update", temp)
            sessionStorage.setItem('PW', temp.password);
            scope.$router.go(-1);
            alert("비밀번호 변경아 되었습니다");            
          }
          else {
            alert("다시 확인해주세요....")
          }
        })
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
