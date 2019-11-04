<template>
  <div class="wrapper">
    <parallax class="section page-header" style="background-color:#ed6c6b;"></parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-70 md-small-size-100">
              <md-toolbar class="md-info md-transparent">
                <div class="md-toolbar-row">
                  <div class="md-toolbar-section-start">
                    <div style="padding-right :5px;">
                      <md-avatar>
                        <img
                          v-if="partner.data.img == null"
                          src="@/assets/img/loading.gif"
                          alt="Avatar"
                          class="Avatar_image"
                        />
                        <img
                          v-else
                          :src="partner.data.img"
                          alt="Avatar"
                          class="Avatar_image md-card img"
                        />
                      </md-avatar>
                    </div>

                    <h3 class="md-title">{{partner.data.nickname}}</h3>
                  </div>
                </div>
              </md-toolbar>
              <md-content
                id="chat"
                style="overflowY:scroll; height:400px; padding:10px;"
                class="md-scrollbar"
              >
                <div v-for="c in chat" style="margin-top:5px;" :key="c.id">
                  <div :class="c['uid'] == uid ? 'right' : 'left'">
                    <span>{{c['context']}}</span>
                  </div>
                  <div :class="c['uid'] == uid ? 'rightDate' : 'leftDate'">
                    <span>{{c['timestamp']}}</span>
                  </div>
                </div>
                <br />
                <br />
              </md-content>
              <md-field class="md-layout-item">
                <md-input v-model="msg" @keydown.enter="send"></md-input>
                <md-button class="md-info" @click="send">전송</md-button>
              </md-field>
            </div>

            <template>
              <div class="md-layout-item md-size-30 md-small-hide">
                <md-toolbar class="md-info">
                  <div class="md-toolbar-row">
                    <div class="md-toolbar-section-start">
                      <i class="material-icons">person</i>
                      <h3 class="md-title">채팅 목록</h3>
                    </div>
                    <div class="md-toolbar-section-end">
                      <md-button class="md-just-icon md-simple md-toolbar-toggle"></md-button>

                      <div class="md-collapse">
                        <md-list>
                          <md-list-item href="#user">
                            <p class="hidden-lg hidden-md">Profile</p>
                          </md-list-item>
                        </md-list>
                      </div>
                    </div>
                  </div>
                </md-toolbar>
                <md-content class="md-scrollbar" style="height:450px; overflowY:auto">
                  <template>
                    <div>
                      <md-list class="md-triple-line">
                        <div v-for="tmp in chatList" :key="tmp.id">
                          <router-link :to="/privateChat/ + tmp['uid']">
                            <md-list-item>
                              <md-avatar>
                                <img
                                  v-if="tmp.img == null"
                                  src="@/assets/img/loading.gif"
                                  alt="People"
                                />
                                <img v-else :src="tmp.img" alt="People" />
                              </md-avatar>

                              <div class="md-list-item-text">
                                <span>{{tmp.nickname}}</span>
                              </div>
                              <md-badge
                                style="margin-right:18px"
                                :md-content="tmp['notification']"
                                type="danger"
                                v-if="tmp['notification']>0"
                              ></md-badge>
                            </md-list-item>
                          </router-link>
                          <md-divider class="md-inset"></md-divider>
                        </div>
                      </md-list>
                    </div>
                  </template>
                </md-content>
              </div>
            </template>

            <!-- to -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ChatService from "@/services/ChatService.js";
import ChatListService from "@/services/ChatListService.js";
import UserProfileService from "@/services/UserProfileService.js";
import UserService from "@/services/UserService.js";
import ProfileCard from "@/views/components/profile/ProfileCard.vue";
import { Tabs } from "@/components";
import Badge from "@/components/Badge";

export default {
  components: {
    UserService,
    ChatService,
    ChatListService,
    ProfileCard,
    Tabs,
    Badge
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
      msg: "",
      chat: [],
      id: parseInt(this.$route.params.id),
      uid: this.$store.state.user.id,
      chatList: [{ nickname: "", img: null }],
      partner: { data: { nickname: "", img: null } },
      interval: null
    };
  },
  mounted() {
    this.init();
  },

  beforeDestroy() {
    clearInterval(this.interval);
  },
  methods: {
    async init() {
      this.partner = await UserService.getUserById(this.id);
      ChatListService.insertNotification(
        this.id,
        this.uid,
        this.partner.data.nickname
      );
      let tmp = await UserProfileService.getUserImage(this.partner.data.email);
      this.partner.data.img = tmp.data[0].imgData;
      ChatListService.checkNotification(this.id, this.uid);
      this.getChatList();
      let self = this;
      ChatService.getPrivateChats(this.id, this.uid)
        .then(function(data) {
          for (let i = 0; i < data["data"].length; i++) {
            let date = new Date(data["data"][i]["timestamp"]);
            date.setHours(date.getHours() + 9);
            let dateFormat =
              date.getYear() -
              100 +
              "." +
              ("00" + (date.getMonth() + 1)).slice(-2) +
              "." +
              ("00" + date.getDate()).slice(-2) +
              " " +
              ("00" + date.getHours()).slice(-2) +
              ":" +
              ("00" + date.getMinutes()).slice(-2);
            data["data"][i]["timestamp"] = dateFormat;
            self.chat.push(data["data"][i]);
          }
        })
        .finally(function() {
          self.setChatDivBottom();
        });
      var socket = new SockJS("http://localhost:9090/websocket");
      this.stompClient = Stomp.over(socket);
      this.stompClient.debug = function(str) {};
      // SockJS와 stomp client를 통해 연결을 시도.
      let scope = this;
      await this.stompClient.connect({}, function(frame) {
        scope.stompClient.subscribe("/topic/privateChat/" + scope.uid, function(
          chat
        ) {
          // scope.getChatList();
          let msg = JSON.parse(chat.body);
          let flag = true;
          for (let i = 0; i < scope.chatList.length; i++) {
            if (msg.uid == scope.chatList[i].uid) {
              if (msg.uid != scope.id) scope.chatList[i].notification++;
              flag = false;
              break;
            }
          }
          if (flag) {
            let uid = msg["uid"];
            UserService.getUserById(uid).then(function(data) {
              let nickname = data["data"]["nickname"];
              UserProfileService.getUserImage(data["data"]["email"]).then(
                function(data) {
                  let imgData = data.data[0].imgData;
                  scope.chatList.push({
                    img: imgData,
                    nickname: nickname,
                    uid: uid,
                    notification: 1
                  });
                }
              );
            });
          }
          if (scope.uid == msg.target && scope.id == msg.uid) {
            ChatListService.checkNotification(scope.id, scope.uid);
            let date = new Date(msg.timestamp);
            let dateFormat =
              date.getYear() -
              100 +
              "." +
              ("00" + (date.getMonth() + 1)).slice(-2) +
              "." +
              ("00" + date.getDate()).slice(-2) +
              " " +
              ("00" + date.getHours()).slice(-2) +
              ":" +
              ("00" + date.getMinutes()).slice(-2);
            scope.chat.push(msg);
            scope.setChatDivBottom();
          }
        });
      });
    },
    async send() {
      if (this.msg == "") {
        return;
      }
      let chat = {
        uid: this.uid,
        timestamp: new Date(),
        context: this.msg,
        isRemoved: "N",
        target: this.id,
        type: "MSG"
      };
      let date = new Date();
      let dateFormat =
        date.getYear() -
        100 +
        "." +
        ("00" + (date.getMonth() + 1)).slice(-2) +
        "." +
        ("00" + date.getDate()).slice(-2) +
        " " +
        ("00" + date.getHours()).slice(-2) +
        ":" +
        ("00" + date.getMinutes()).slice(-2);
      let pushChat = {
        uid: this.uid,
        timestamp: dateFormat,
        context: this.msg,
        isRemoved: "N",
        target: this.id,
        type: "MSG"
      };
      this.stompClient.send("/app/privateChat", {}, JSON.stringify(chat));
      this.chat.push(pushChat);
      this.setChatDivBottom();
      ChatService.insertChat(chat);
      ChatListService.insertOrUpdateNotification(
        this.uid,
        this.id,
        this.$store.state.user.nickname
      );
      this.msg = "";
    },
    setChatDivBottom() {
      document.getElementById("chat").scrollTop = document.getElementById(
        "chat"
      ).scrollHeight;
    },
    async getNickname(uid) {
      let user = await UserService.getUserById(uid);
      return user["data"]["nickname"];
    },
    async getChatList() {
      let self = this;
      let tmpChatList = [];
      await ChatListService.getNotificationList(this.uid)
        .then(function(data) {
          let scope = self;
          for (let i = 0; i < data.data.length; i++) {
            let uid = data.data[i]["uid"];
            let nickname = data.data[i]["nickname"];
            let noti = data.data[i]["notification"];
            if (scope.id == uid) noti = 0;
            UserService.getUserById(uid).then(function(data) {
              UserProfileService.getUserImage(data["data"]["email"]).then(
                function(data) {
                  let imgData = data.data[0].imgData;
                  tmpChatList.push({
                    img: imgData,
                    nickname: nickname,
                    uid: uid,
                    notification: noti
                  });
                }
              );
            });
          }
        })
        .finally(function() {
          // tmpChatList.sort(self.sortByCount);
        });
      self.chatList = tmpChatList;
    },

    sortByCount(a, b) {
      if (a.notification < b.notification) return -1;
      else if (a.notification == b.notification) return this.sortByName(a, b);
      else return 1;
    },
    sortByName(a, b) {
      var a = a.nickname.toString().toLowerCase();
      var b = b.nickname.toString().toLowerCase();
      return a < b ? -1 : a == b ? 0 : 1;
    }
  }
};
</script>

<style>
.right {
  max-width: 50%;
  padding: 5px;
  background: #fafa95;
  border-radius: 1em;
  float: right;
  word-break: break-all;
}
.left {
  padding: 5px;
  max-width: 50%;
  background: #8ad9d9;
  border-radius: 1em;
  float: left;
  word-break: break-all;
}
.rightDate {
  text-align: right;
  border-radius: 1em;
  clear: both;
}
.leftDate {
  text-align: left;
  border-radius: 1em;
  clear: both;
}
.md-avatar img {
  height: auto !important;
}
</style>
