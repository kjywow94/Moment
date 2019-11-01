<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            //배경
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-contacts">
        <div class="container">
          <div class="md-layout">
            <div>
    <div id="navigation-pills">
      <div class="title">
        <h3>Navigation Pills</h3>
      </div>
      <div class="title">
        <h3><small>With Icons</small></h3>
      </div>
      <div class="md-layout">
        <div>
          <tabs
            :tab-name="['USER', 'Schedule', 'Logs']"
            :tab-icon="['account_box', 'schedule', 'update']"
            plain
            nav-pills-icons
            color-button="primary"
          >
            <!-- here you can add your content for tab-content -->
            <template slot="tab-pane-1">
               <table class="table table-bordered table-head-label" style="word-break:break-all;table-layout:fixed;">
                                    <thead>
                                        <tr class="text-center">
                                            <th scope="col">Email</th>
                                            <th scope="col">이름</th>
                                            <th scope="col">닉네임</th>
                                            <th scope="col">지갑주소</th>
                                            <th scope="col">동행횟수</th>
                                            <th scope="col">평점</th>
                                            <th scope="col">권한</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <tr v-for="user in users" :key='user.id' class="text-center" >
                                            <td class="text-overflow">{{user['email']}}</td>
                                            <td class="text-center">{{user['userName']}}</td>
                                            <td class="text-center">{{user['nickname']}}</td>
                                            <td class="text-center">{{user['walletAddress']}}</td>
                                            <td class="text-center">{{user['companionCount']}}</td>
                                            <td class="text-center">{{user['companionGrade']}}</td>
                                            <td class="text-center">{{user['authority']}}</td>
                                        </tr>
                                    </tbody>
                                </table>
            </template>
            <template slot="tab-pane-2">
            </template>
            <template slot="tab-pane-3">
              {{log}}
            </template>
          </tabs>
        </div>
        <!-- <div class="md-layout-item md-size-50 md-small-size-100">
          <tabs
            :tab-name="['Dashboard', 'Schedule']"
            :tab-icon="['dashboard', 'schedule']"
            plain
            flex-column
            nav-pills-icons
            color-button="primary"
          > -->
            <!-- here you can add your content for tab-content --><!--
            <template slot="tab-pane-1">
              Collaboratively administrate empowered markets via plug-and-play
              networks. Dynamically procrastinate B2C users after installed base
              benefits.
              <br /><br />
              Dramatically visualize customer directed convergence without
              revolutionary ROI.
            </template>
            <template slot="tab-pane-2">
              Efficiently unleash cross-media information without cross-media
              value. Quickly maximize timely deliverables for real-time schemas.
              <br /><br />
              Dramatically maintain clicks-and-mortar solutions without
              functional solutions.
            </template>
          </tabs>
        </div> -->
      </div>
    </div>
  </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import { Tabs } from "@/components";
import UserService from "@/services/UserService.js";
import LogService from "@/services/LogService.js";
export default {
  components: {
    Tabs,
    UserService
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
      users:[{}],
      log:null
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
      this.users = await UserService.getAllUsers();
      this.users = this.users['data'];
      this.log = await LogService.getLog();
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
