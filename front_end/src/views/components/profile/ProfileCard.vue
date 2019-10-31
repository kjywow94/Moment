<template>
  <v-popover offset="8" placement="left" class="my-card-title">
    <div style="display : inline-block">
                <div>
                  <img :src="info.userImgData" alt="Avatar" class="Avatar_image" />
                </div>
              </div>
              <div style="display : inline-block; position:absolute; left:80px">
                <div style="align-items: center;">
                  {{info.location}}
                  <!--장소-->
                </div >
                <div>
                  by {{info.nickname}}
                  <!--작성자-->
                </div>
              </div>
  <template slot="popover">
                  <img :src="userImg['imgData']" alt="Avatar" class="Avatar_image" />
                  <h3 class="popover-header">{{user.nickname}} </h3>
                  <h5 class="popover-header">{{user.userName}} / {{user.birthday}} / {{user.gender}}</h5>
                  <hr>
                  <div class="popover-body">
                    {{user.about}}
                    <hr>
                    <a :href="'https://'+user.sns1">{{user.sns1}}</a><br>
                    <a :href="'https://'+user.sns2">{{user.sns2}}</a><br>
                    <a :href="'#/PrivateChat/' + user.id"><md-button>메세지 보내기</md-button></a>
                  </div>
                </template>
</v-popover>

</template>
<script>
import UserService from "@/services/UserService.js"
import UserProfileService from "@/services/UserProfileService.js"
export default {
    components : {
        UserService,
        UserProfileService
    },
    props : {
      info:{
        uid : 0
      }
    },
    data() {
        return {
            user : {},
            userImg : {}
        };
    },
    mounted() {
        this.init();
    },
    methods : {
        async init(){
          let uid = this.info['uid'];
          this.user = await UserService.getUserById(uid);

          this.user = this.user['data'];
          this.setData();
         
          this.userImg = await UserProfileService.getUserImage(this.user['email']);
          this.userImg = this.userImg['data'][0];
          },

          setData(){
            let birth = new Date(this.user.birthday);
            this.user.birthday = (1900 + birth.getYear()) + "년 " + (birth.getMonth() + 1) + "월 " + birth.getDay() + "일"
            this.user.gender = (this.user.gender == 'M' ? "남" : "여");
          
          }
        }
  };
</script>
<style>
.md-button-content {
  margin-bottom: auto;
  display: flex;
  align-items: center;
}
.my-hashtag-div {
  margin-top: 10px;
}
.my-hashtag {
  float: left;
}
.my-card-content {
  margin: 0px;
}
.my-card-title {
  padding-top: 15px;
  padding-bottom: 0px;
  padding-left: 20px;
  padding-right: 20px;
}

.Avatar_image {
  max-width: 50px;
  max-height: 50px;
  border-radius: 50px;
}
.review-card-title {
  display: inline-block;
  padding: 5px;
  margin: 5px;
}
.modal-img {
  margin-bottom: 5px;
}
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.md-has-textarea + .md-layout {
  margin-top: 15px;
}


.popover-inner {
    background-color: white;
    max-width: 500px;
    width:500px;
}
.md-tooltip.popover .popover.popover{
    max-width: 500px !important;
    width:500px !important;
}

@media (min-width: 481px) {
  .mdquery-xs {
    display: none;
  }
}

/* 모바일*/
@media (max-width: 480px) {
  .mdquery-md {
    display: none;
  }
  .my-card-container {
    margin: auto;
    height: 380px;
  }
}
</style>