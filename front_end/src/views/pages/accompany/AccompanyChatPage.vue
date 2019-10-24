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
                  <md-input v-model="msg" placeholder="default"></md-input>
                </md-field>
                        <button @click="send()">전송</button>
            </div>
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
        msg:""
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
  this.stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    scope.stompClient.subscribe('http://localhost:9090/topic/in', function (chat) {
      console.log("in!! :: " , JSON.parse(chat.body).content);
    });
    scope.stompClient.subscribe('http://localhost:9090/topic/out', function (chat) {
      console.log("out!! :: " , JSON.parse(chat.body).content);
    });
  });

    },
    send(){
        // UID
        // context
        // timestamp
        // room_number
        // is_remove
        let chat = {
            'UID' : 11, 'timestamp':null, 'context': this.msg, 'room_number':1
            }
        
        this.stompClient.send("http://localhost:9090/app/in", {}, JSON.stringify(chat));
    }
  }
};
</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
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
}

</style>
