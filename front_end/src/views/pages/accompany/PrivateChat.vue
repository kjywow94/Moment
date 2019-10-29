<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
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

  methods : {
    async init(){
      this.chat = await ChatService.getPrivateChats(this.id, this.uid);
      this.chat = this.chat['data'];
      console.log(this.uid)
      console.log(this.id)
      var socket = new SockJS('http://192.168.100.49:9090/websocket');
      this.stompClient = Stomp.over(socket);
      this.stompClient.debug = function(str) {};
      // SockJS와 stomp client를 통해 연결을 시도.
      let scope = this;
      await this.stompClient.connect({}, function (frame) {
        scope.stompClient.subscribe('/topic/privateChat/' + scope.uid, function (chat) {
          let msg = JSON.parse(chat.body);
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
            'isRemoved' : 'N', 'target' : this.id, type : "MSG"}
        this.stompClient.send("/app/privateChat", {}, JSON.stringify(chat));
        this.chat.push(chat);
        ChatService.insertChat(chat);
        this.msg = "";
        this.setChatDivBottom();
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
