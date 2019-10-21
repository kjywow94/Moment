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
        <div class="md-layout-item md-size-50 md-small-size-100">
          <tabs
            :tab-name="['Dashboard', 'Schedule', 'Tasks']"
            :tab-icon="['dashboard', 'schedule', 'list']"
            plain
            nav-pills-icons
            color-button="primary"
          >
            <!-- here you can add your content for tab-content -->
            <template slot="tab-pane-1">
               <table class="table table-bordered" style="word-break:break-all;table-layout:fixed;">
                                    <thead>
                                        <tr class="text-center">
                                            <th scope="col">작품명</th>
                                            <th scope="col">작품설명</th>
                                            <th scope="col">작품주인ID</th>
                                            <th scope="col">작품공개</th>
                                            <th scope="col">작품상태</th>
                                            <th scope="col">기타</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <tr class="text-center">
                                            <td class="text-overflow">1</td>
                                            <td class="text-center">2</td>
                                            <td class="text-center">3</td>
                                            <td class="text-center">4</td>
                                            <td class="text-center">5</td>
                                            <td class="text-center">5</td>
                                        </tr>
                                    </tbody>
                                </table>
            </template>
            <template slot="tab-pane-2">
              Efficiently unleash cross-media information without cross-media
              value. Quickly maximize timely deliverables for real-time schemas.
              <br /><br />
              Dramatically maintain clicks-and-mortar solutions without
              functional solutions.
            </template>
            <template slot="tab-pane-3">
              Completely synergize resource taxing relationships via premier
              niche markets. Professionally cultivate one-to-one customer
              service with robust ideas.
              <br /><br />
              Dynamically innovate resource-leveling customer service for state
              of the art customer service.
            </template>
          </tabs>
        </div>
        <div class="md-layout-item md-size-50 md-small-size-100">
          <tabs
            :tab-name="['Dashboard', 'Schedule']"
            :tab-icon="['dashboard', 'schedule']"
            plain
            flex-column
            nav-pills-icons
            color-button="primary"
          >
            <!-- here you can add your content for tab-content -->
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
        </div>
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
import AccompanyCalendar from "../../components/AccompanyCalendar";
import AccompanyBoard from "../../components/AccompanyBoard";
import { Tabs } from "@/components";
import { UserService } from "../../../services/AccompanyService.js";

export default {
  components: {
    AccompanyCalendar,
    AccompanyBoard,
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
      name: null,
      email: null,
      message: null
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
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
