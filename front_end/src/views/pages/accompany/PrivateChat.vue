<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
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
        chat:[],
        id: this.$route.params.id
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
    
    var socket = new SockJS('http://192.168.100.49:9090/websocket');
    this.stompClient = Stomp.over(socket);
    this.stompClient.debug = function(str) {};
    // SockJS와 stomp client를 통해 연결을 시도.
    let scope = this;
    await this.stompClient.connect({}, function (frame) {
      scope.stompClient.subscribe('/topic/chat/' + scope.id, function (chat) {
        let msg = JSON.parse(chat.body);
        scope.chat.push(msg['context']);
      });
  });

    },
    send(){
      if(this.msg == ""){
        return;
      }
        let chat = {
            'UID' : 0,'timestamp':new Date(), 'context': this.$store.state.user.nickname + " : " + this.msg, 'roomNumber':this.id,
            'isRemoved' : 'N'}
        this.stompClient.send("/app/chat", {}, JSON.stringify(chat));
        this.msg = "";
    }
  }
};
</script>

