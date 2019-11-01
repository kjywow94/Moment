<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container" style="width:60%; float:left">
          <div id="chat" style="overflowY:scroll; height:400px; padding:10px;">
            <div v-for="c in chat" :key="c.id" style="margin-top:5px;">
              <div :class="c['uid'] == uid ? 'right' : 'left'" >
                    <span>{{c['context']}}</span>
              </div>
              <div :class="c['uid'] == uid ? 'rightTime' : 'leftTime'" >
                  {{c['timestamp']}}
              </div>
              
            </div>
              <br><br>
            </div>
            <div class="md-layout">
              <md-field style="width:80%">
                <md-input v-model="msg" @keydown.enter="send" ></md-input>
              </md-field>
              <md-button @click="send">전송</md-button>
            </div>
          </div>
        </div>

        <div style="overflowY:scroll;  height:300px; width:30%;text-align:right">
      <div v-for="tmp in chatList">
      <router-link :to="/privateChat/ + tmp['uid']">
        <md-button class="md-rose md-just-icon md-round" aria-disabled="true">{{tmp['notification']}}</md-button>
        <md-button> {{tmp['nickname']}} </md-button>
        </router-link>
      </div>
      </div>
      <div style="overflowY:scroll;  height:300px;  width:30%;text-align:left">
      <div v-if="busy" class="loadingBox md-layout-item md-size-100 mx-auto">
            <img src="@/assets/img/loading.gif" class="loadingImg" />
          </div>
      <div  v-else v-for="tmp in userList">
        
        <router-link :to="/privateChat/ + tmp['id']">
        <img :src="tmp['profile']" alt="Avatar" class="Avatar_image" />
        <md-button> {{tmp['nickname']}} </md-button>
        </router-link>
      </div>
      </div>
      <div style="float:clear;"></div>

      
      </div>
      </div>
</template>

<script>
import ChatService from "@/services/ChatService.js";
import ChatListService from "@/services/ChatListService.js";
import UserService from "@/services/UserService.js";
import UserProfileService from "@/services/UserProfileService.js"
export default {
  components: {
    UserService,
    ChatService,
    ChatListService,
    UserProfileService
  },
  bodyClass: "landing-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg7.jpg")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/faces/kendall.jpg")
    }
  },
  data() {
    return {
        stompClient: null,
        msg:"",
        chat:[],
        id: parseInt(this.$route.params.id),
        uid: this.$store.state.user.id,
        partner: {},
        chatList: [],
        userList: [{profile:null}],
        userImgList: [],
        busy: true
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  mounted(){
    this.init();
    
  },

  methods : {
    async init(){
    let self = this;
      UserService.getUserById(this.id).then(function(data){
        self.partner = data;
      });
      this.getChatList();
      setInterval(() => {
        ChatListService.checkNotification(this.id, this.uid);
        this.getChatList();
      }, 1000);
      ChatListService.checkNotification(this.id, this.uid);
      let users = await UserService.getAllUsers();
      this.userList = users['data'];
      for(let i = 0 ; i < this.userList.length ; i++){
        let img = UserProfileService.getUserImage(this.userList[i]['email']).then(function(data){
        if(data['data'].length != 0){
          self.userList[i]['profile'] = data['data'][0]['imgData'];
          self.userImgList.push(data['data'][0]['imgData']);
        }
        if(i == self.userList.length - 1){
          self.busy = false;
        }
        });

      }
        
      // console.log(this.userList)

      this.chat = await ChatService.getPrivateChats(this.id, this.uid);
      this.chat = this.chat['data'];
      for(let i = 0 ; i < this.chat.length ; i++){
        let timestamp = new Date(this.chat[i]['timestamp']);
        timestamp = (timestamp.getYear()-100) + "/" + ("00" +(timestamp.getMonth() + 1)).slice(-2)+ "/" + ("00" + timestamp.getDate()).slice(-2) +" "+
        ("00" +(timestamp.getHours())).slice(-2) + ":" + ("00" +(timestamp.getMinutes())).slice(-2);
        this.chat[i]['timestamp'] = timestamp;
        if(this.chat[i]['uid'] == this.uid){

          let tmpContext = this.chat[i]['context'].split(" : ");
          let tmp = "";
          for(let i = 1; i < tmpContext.length ; i++){
            tmp += tmpContext[i];
          }
          this.chat[i]['context'] = tmp;

        }
        this.chat[i]['context']
      }
      var socket = new SockJS('http://192.168.100.49:9090/websocket');
      this.stompClient = Stomp.over(socket);
      this.stompClient.debug = function(str) {};
      // SockJS와 stomp client를 통해 연결을 시도.
      let scope = this;
      await this.stompClient.connect({}, function (frame) {
        scope.stompClient.subscribe('/topic/privateChat/' + scope.uid, function (chat) {
          let msg = JSON.parse(chat.body);
          // ChatListService.checkNotification(scope.id, scope.uid);
          let timestamp = new Date(msg.timestamp);
          chat.timestamp = (timestamp.getYear()-100) + "/" + ("00" +(timestamp.getMonth() + 1)).slice(-2)+ "/" + ("00" + timestamp.getDate()).slice(-2) +" "+
        ("00" +(timestamp.getHours())).slice(-2) + ":" + ("00" +(timestamp.getMinutes())).slice(-2);
          scope.chat.push(msg);
        });
      });
          this.setChatDivBottom();

      },
    async send(){
      if(this.msg == ""){
        return;
      }
        let chat = {
            'uid' : this.uid,'timestamp':new Date(), 'context': this.$store.state.user.nickname + " : " + this.msg,
            'isRemoved' : 'N', 'target' : this.id, type : "MSG"};
        this.stompClient.send("/app/privateChat", {}, JSON.stringify(chat));


        ChatListService.insertNotification(this.uid, this.id,this.partner.data.nickname, 0);
        let timestamp = new Date();
        timestamp = (timestamp.getYear()-100) + "/" + ("00" +(timestamp.getMonth() + 1)).slice(-2)+ "/" + ("00" + timestamp.getDate()).slice(-2) +" "+
        ("00" +(timestamp.getHours())).slice(-2) + ":" + ("00" +(timestamp.getMinutes())).slice(-2);
        

        let tmpChat = {
                'uid' : this.uid,'timestamp':timestamp, 'context': this.msg,
                'isRemoved' : 'N', 'target' : this.id, type : "MSG"};
        this.chat.push(tmpChat);
        ChatService.insertChat(chat);
        ChatListService.insertOrUpdateNotification(this.id, this.uid,this.$store.state.user.nickname);
        this.msg = "";
        this.setChatDivBottom();
    },
    setChatDivBottom(){
      document.getElementById("chat").scrollTop = document.getElementById("chat").scrollHeight; 
    },
    async getNickname(uid){
      let user = await UserService.getUserById(uid);
      return user['data']['nickname'];
    },
    async getChatList(){
      let tmp = await ChatListService.getNotificationList(this.uid);
      this.chatList = tmp['data'];
    }
  }
};
</script>

<style>

.right{
  margin-left: 60%;
  padding : 10px;
  text-align: left;
  background: #fafa95;
  border-radius: 1em;
      word-break: break-word;
}
.left{
  margin-left: 60%;
  padding : 10px;
  background: #8ad9d9;
  border-radius: 1em;
      word-break: break-word;
}

.rightTime{
  margin-left: 70%;
  margin-bottom: 5px;
  text-align: left;
  border-radius: 1em;
}
.leftTime{
  margin-right: 70%;
   margin-bottom: 5px;
  text-align: left;
  border-radius: 1em;
}

</style>
