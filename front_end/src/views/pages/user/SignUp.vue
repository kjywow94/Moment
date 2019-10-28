<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
          <h1 class="title">회원가입</h1>
        <div class="md-layout"  style="background:white;">
            <div class="md-layout-item md-size-50 md-small-size-100" style="margin: 0 auto;">
            <tabs
                :tab-name="['회원등록', '프로필등록', '가입하기']"
                :tab-icon="['supervisor_account', 'person_pin', 'check']"
                plain
                nav-pills-icons
                color-button="primary"
            >
                <!-- here you can add your content for tab-content -->
                <template slot="tab-pane-1">
                     <div class="md-layout-item md-size-100 md-xsmall-size-100 md-small-size-100 md-medium-size-100">
                        <md-field>
                            <label>이메일</label>
                            <md-input v-model="email" type="text"></md-input>
                        </md-field>
                        <md-button class="md-primary md-round">이메일 인증</md-button>
                        <md-field>
                            <label>닉네임</label>
                            <md-input v-model="idlabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>이름</label>
                            <md-input v-model="namelabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>비밀번호</label>
                            <md-input v-model="passwordlabel" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>비밀번호 확인</label>
                            <md-input v-model="passwordcheck" type="text"></md-input>
                        </md-field>
                        <md-field>
                            <label>휴대폰</label>
                            <md-input v-model="phonelabel" type="text"></md-input>
                        </md-field>
                        
                        <md-datepicker v-model="selectedDate">
                            <label>생년월일</label>
                        </md-datepicker>
                        <md-radio v-model="radio" :value="true">남자</md-radio>
                        <md-radio v-model="radio" :value="false">여자</md-radio>
                    </div>
                </template>
             
                <template slot="tab-pane-2">
                    <!-- 사진 TEST START-->
                    <label>사진등록</label>
                    <br>
                    <div class="ml-2 col-sm-6">
                        <img :src="img[0]" id="preview" class="img-thumbnail" style="float: left;">
                      <!--  <img :src="img[1]" id="preview" class="img-thumbnail" style="float: right;">
                        <img :src="img[2]" id="preview" class="img-thumbnail"> -->
                    </div>
                    <div class="ml-2 col-sm-6">
                        <div id="msg"></div>
                        <form method="post" id="image-form">
                            <input type="file"  ref="inputRef" name="img[]" class="file" accept="image/*" @change="changeFile($event)" style="display:none">
                            <div class="input-group my-3">
                            <input type="text" class="form-control" disabled placeholder="Upload File" v-model="imgName">
                            <div class="input-group-append">
                                <button type="button" class="browse btn btn-primary" v-on:click="uploadImg()">Browse...</button>
                            </div>
                            </div>
                        </form>
                    </div>

                    <!-- SAVE -->
                    <input type="text"  v-model="email">
                    <button type="button" class="btn btn-primary" v-on:click="save()">사진 등록 test</button>


                    <!--  사진 TEST END-->  

                    <md-field>
                        <label>소개글</label>
                        <md-textarea v-model="aboutme"></md-textarea>
                    </md-field> 
                    <br>
                </template>
                <template slot="tab-pane-3">
                    <p>가입을 축하드립니다!!</p>
                    <br>
                    여러분들의 여행에 좋은 인연을 만나기를 기원합니다
                    <br>
                    <md-button type="submit" class="md-primary md-round" v-on:click="register">회원가입</md-button>
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
      idlabel: "",
      namelabel: "",
      passwordlabel: "",
      passwordcheck: "",
      phonelabel: "",
      email: "",
     
     ///test
      imgName: ["프로필등록.."],
      img:["https://placehold.it/50x50"],
      extension : [],


      selectedDate: new Date(),

      radio: null,
      options1 : "",
      options2 : "",
      options3 : "",
      options4 : "",
      options5 : "",
      aboutme: "",
      checked1: true,
      checked2: true,
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
      register: function () {
          console.log(1111);
          console.log("o1 : "+this.options1);
          console.log("o2 : "+this.options2);
          console.log("o3 : "+this.options3);
          console.log("o4 : "+this.options4);
          console.log("o5 : "+this.options5);
          console.log(this.radio);
          
            var scope = this;
            if (this.passwordlabel === this.passwordcheck) {
                userService.signUp(
                    this.user.email,
                    this.user.name,
                    hashingService.SHA256(this.user.password),
                    function (response) {
                        alert("회원가입이 완료되었습니다.");
                        scope.$router.push('/');
                    }
                );
            } else {
                alert('비밀번호가 일치하지 않습니다.');
            }
        },
        uploadImg() {
            this.$refs.inputRef.click();
        },
        changeFile(event) {
            console.log("event1 : ",event);

            this.img = [];
            this.imgName = [];
            let length = event.target.files.length;
            length = length > 3 ? 3 : length;
            this.imgLen = length;
            for(let i = 0; i < length; i++){
                // console.log("files : ", event.target.files[i]);

                let eamilLen = this.email.lastIndexOf('@');
                this.imgName[i] = this.email.substring(0, eamilLen)+'_'+(i+1);

                
                let filename = event.target.files[i].name;
                let fileLen = filename.length;
                let lastDot = filename.lastIndexOf('.');
                this.extension[i] = filename.substring(lastDot, fileLen).toLowerCase();
                var reader = new FileReader();
                reader.readAsDataURL(event.target.files[i]);
                reader.onload = e => {
                    this.img.push(e.target.result);
                        console.log(" this.imgName :",  this.imgName);
                        console.log("this.img :", this.img);
                        console.log("this.extension :", this.extension);
                };
            }
        },

        save() {
            var scope = this;
            UserService.signUp({
                "birthday": new Date(),
                "gender": "M",
                "nickname": "bora",
                "userName": "천보라",
                "phone": "010-6675-7777",
                "email": scope.email,
                "password": "M",
                "sns1": "인스타그램",
                "sns2": "페이스북",
                // "taste1": 1,
                // "taste2": 1,
                // "taste3": 1,
                // "taste4": 1,
                // "taste5": 1,
                // "taste6": 1,
                // "taste7": 1,
                "about":"about~~" 
            }).then(userdata => {
                console.log("data : ", userdata.data);
                // console.log("data : ", userdata.data);

                if(userdata.data == 1){
                    for(let i = 0; i < scope.imgLen; i++){

                        let imgName = scope.imgName[i];
                        let imgData = scope.img[i];
                        let extension = scope.extension[i];

                        UserService.uploadImage({
                            "imgName" : imgName,
                            "imgData" : imgData,
                            "extension" : extension,
                            "email" : scope.email
                        }).then(uploadImgData => {
                            console.log("image upload : ", uploadImgData);
                        }).catch(err => {
                            console.log(" error : ", err);
                        });
                       
                    }
                }

            })
            .catch(err => {
                console.log(" error : ", err);
            });
        }

        //사진 저장 TEST 
        // save: function () {
        //     var scope = this;
        //     workService.signUp({
        //         "about":"about~~"
        //     },
        //         function (response) {
        //             workService.findWorksInfoByOwner(scope.sharedStates.user.id, (workList) => {
        //                 var workId = workList[workList.length - 1].id;

        //                 var filename = scope.work.imgName;
        //                 var fileLen = filename.length;
        //                 var lastDot = filename.lastIndexOf('.');
        //                 var extension = filename.substring(lastDot, fileLen).toLowerCase();

        //                 workService.uploadImage({
        //                     "imgName": workId,
        //                     "extension": extension,
        //                     "imgData": scope.work.img
        //                 }, function () {
        //                     alert('작품이 등록되었습니다.');
        //                     scope.$router.push('/artworks');

        //                 }, function (error) {
        //                     alert("작품 이미지 업로드 중 에러가 발생했습니다.");
        //                     console.log(error);
        //                 });
        //             });
        //         },
        //         function (error) {
        //             alert("입력폼을 모두 입력해주세요. 또는 중복된 이메일입니다.");
        //         });
        // }
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
