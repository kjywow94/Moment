<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
        Chat test

      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
                     <md-field>
                  <md-input v-model="msg" @keydown.enter="send"></md-input>
                </md-field>
                        <button @click="send">전송</button>
            </div>
            <div v-for="c in chat">{{c}}</div>


          </div>
        </div>
      </div>
      </div>
</template>

<script>
import TravelReviewService from "@/services/TravelReviewService.js";
import AccompanyService from "@/services/AccompanyService.js";
import { Tabs } from "@/components";
export default {
  components: {
    TravelReviewService,
    AccompanyService,
    Tabs
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
        chat:[]
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
    var socket = new SockJS('http://localhost:9090/websocket');
    this.stompClient = Stomp.over(socket);
    // SockJS와 stomp client를 통해 연결을 시도.
    let scope = this;
    await this.stompClient.connect({}, function (frame) {
      scope.stompClient.subscribe('/topic/greetings', function (chat) {
        let msg = chat.body
        scope.chat.push(msg);
        console.log("HELLO MSG" + msg);
    });
    scope.stompClient.subscribe('/topic/chat', function (chat) {
      let msg = JSON.parse(chat.body);
      console.log(msg['context']);
      scope.chat.push(msg['context']);
    });
      let visit = {'UID' : 11, 'timestamp':new Date(), 'context': scope.$store.state.user.nickname, 'room_number':1};
      scope.stompClient.send("/app/greetings", {}, JSON.stringify(visit));
  });

    },
    send(){
        let chat = {
            'UID' : 11, 'timestamp':new Date(), 'context': this.$store.state.user.nickname + " : " + this.msg, 'room_number':1
            }
        this.stompClient.send("/app/chat", {}, JSON.stringify(chat));
        this.msg = "";
    }
  }
};
</script>

