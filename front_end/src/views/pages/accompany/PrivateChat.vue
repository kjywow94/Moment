<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container" style="width:60%; float:left">
          <div id="chat" style="overflowY:scroll; height:400px; padding:10px;">
            <div v-for="c in chat" style="margin-top:5px;">
              <div :class="c['uid'] == uid ? 'right' : 'left'" >
                    <span>{{c['context']}}</span>
                  <br><span>{{c['timestamp']}}</span>
              </div>
            </div>
              <br><br>
            </div>
            <div class="md-layout">
              <md-field >
                <md-input v-model="msg" @keydown.enter="send" ></md-input>
              </md-field>
              <button @click="send">전송</button>
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

      <div  v-for="tmp in userList">
        <router-link :to="/privateChat/ + tmp['id']">
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
export default {
  components: {
    UserService,
    ChatService,
    ChatListService
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
        chatList: [],
        userList: []
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
      this.getChatList();
      setInterval(() => {
        ChatListService.checkNotification(this.id, this.uid);
        this.getChatList();
      }, 1000);
      ChatListService.checkNotification(this.id, this.uid);
      let users = await UserService.getAllUsers();
      this.userList = users['data'];
      for(let i = 0 ; i < this.userList.length ; i++){
        this.userList[i]['nickname'] = await this.getNickname(this.userList[i]['id'])
      }
      
      

      this.chat = await ChatService.getPrivateChats(this.id, this.uid);
      this.chat = this.chat['data'];
      var socket = new SockJS('http://192.168.100.49:9090/websocket');
      this.stompClient = Stomp.over(socket);
      this.stompClient.debug = function(str) {};
      // SockJS와 stomp client를 통해 연결을 시도.
      let scope = this;
      await this.stompClient.connect({}, function (frame) {
        scope.stompClient.subscribe('/topic/privateChat/' + scope.uid, function (chat) {
          let msg = JSON.parse(chat.body);
          // ChatListService.checkNotification(scope.id, scope.uid);
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
        this.chat.push(chat);
        ChatService.insertChat(chat);
        ChatListService.insertOrUpdateNotification(this.uid, this.id,this.$store.state.user.nickname);
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
  margin-left: 70%;
  text-align: left;
  background: #fafa95;
  border-radius: 1em;
}
.left{
  margin-right: 70%;
  text-align: left;
  background: #8ad9d9;
  border-radius: 1em;
}

</style>
