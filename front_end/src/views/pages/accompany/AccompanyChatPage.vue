<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div id="chat" style="overflowY:scroll; height:400px; padding:10px;">
            <div v-for="c in chat" style="margin-top:5px;">

              <div v-if="c['type'] == 'SYSTEM'" class="system" >
                    <span>{{c['context']}}</span>
                  <br><span>{{c['timestamp']}}</span>
              </div>

              <div v-else :class="c['uid'] == uid ? 'right' : 'left'" >
                    <span>{{c['context']}}</span>
                  <br><span>{{c['timestamp']}}</span>
              </div>

            </div>
              <br><br>
            
</div>
<div class="md-layout">
                     <md-field>
                  <md-input v-model="msg" @keydown.enter="send"></md-input>
                </md-field>
                        <button @click="send">전송</button>
            </div>

          </div>
        </div>
      </div>
      </div>
</template>

<script>
import ChatService from "@/services/ChatService.js";
export default {
  components: {
    ChatService
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
        id: this.$route.params.id,
        uid: this.$store.state.user.id
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
  beforeDestroy(){
    this.exit();
  },
  methods : {
    async init(){
      this.chat = await ChatService.getChats(this.id);
      this.chat = this.chat['data'];
      
      var socket = new SockJS(API_BASE_URL+'/websocket');
      this.stompClient = Stomp.over(socket);
      this.stompClient.debug = function(str) {};
      // SockJS와 stomp client를 통해 연결을 시도.
      let scope = this;
      await this.stompClient.connect({}, function (frame) {
        scope.stompClient.subscribe('/topic/greetings/' + scope.id , function (chat) {
          let msg = JSON.parse(chat.body);
          scope.chat.push(msg);
      });
        scope.stompClient.subscribe('/topic/chat/' + scope.id, function (chat) {
          let msg = JSON.parse(chat.body);
          scope.chat.push(msg);
        });
        scope.stompClient.subscribe('/topic/goodbye/' + scope.id, function (chat) {
          let msg = JSON.parse(chat.body);
          scope.chat.push(msg);
        });
          let visit = {'uid' : scope.uid, 'timestamp':new Date(), 'context': scope.$store.state.user.nickname + "님이 입장하셨습니다.", 'roomNumber':scope.id, 'type' : 'SYSTEM'
          };
          scope.stompClient.send("/app/greetings", {}, JSON.stringify(visit));
          ChatService.insertChat(visit);
      });
          this.setChatDivBottom();

      },
    async send(){
      if(this.msg == ""){
        return;
      }
        let chat = {
            'uid' : this.uid,'timestamp':new Date(), 'context': this.$store.state.user.nickname + " : " + this.msg, 'roomNumber':this.id,
            'isRemoved' : 'N', 'type' : 'MSG'}
        this.stompClient.send("/app/chat", {}, JSON.stringify(chat));
        ChatService.insertChat(chat);
        this.msg = "";
        this.setChatDivBottom();
    },
    async exit(){
      let exit = {'uid' : this.uid, 'timestamp':new Date(), 'context': this.$store.state.user.nickname+ "님이 퇴장하셨습니다.", 'roomNumber':this.id, 'type' : 'SYSTEM'};
      await this.stompClient.send("/app/goodbye", {}, JSON.stringify(exit));
      ChatService.insertChat(exit);
    },
    setChatDivBottom(){
      document.getElementById("chat").scrollTop = document.getElementById("chat").scrollHeight; 
    }
  }
};
</script>

<style>

.right{
  margin-left: 70%;
  text-align: left;
  background: yellow;
  border-radius: 1em;
}
.left{
  margin-right: 70%;
  text-align: left;
  background: cyan;
  border-radius: 1em;
}
.system{
  text-align: center;
}
</style>
