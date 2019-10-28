import Vue from 'vue';
import Vuex from 'vuex';
import Axios from 'axios';
import router from './router';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
    plugins: [createPersistedState()],
    state: {
      isSigned: false,
      userinfo : null,
      user: {
          email: "",
          password: ""
      },
      isLogin: false,
      isLoginError: false
    },
    mutations: {
        // 로그인 성공했을때
        loginSuccess(state){
            state.isLogin = true
            state.isLoginError = false
        },
        // 로그인이 실패했을 때
        loginError(state){
            state.isLogin = false,
            state.isLoginError = true
        },
        login(state, payload){
            state.user = payload
            state.isLogin = true
            state.userinfo = payload
            
        },
        logout(state){
            state.isLogin = false
            state.user = ""
        },
        update(state, payload){
            state.user = payload
            state.userinfo = payload
        }
    },
    actions: {
        //로그인 시도
        login({state, commit}, signObj){
           let selectUser = null
           
        }
    }
  });
