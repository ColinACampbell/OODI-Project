import { createStore } from 'vuex'

const store = new createStore({
  state: {
    userInfo: "",
    recipients: ""

  },
  getters: {
      userName: state => state.userInfo.user.name,
      userInfo: state => state.userInfo.user,
      members: state => state.userInfo.members,
      token: state => state.userInfo.token,
      position: state => state.userInfo.user.position
  },
  mutations: {
    updateUserInfo(state, payload){
        state.userInfo = payload.userInfo;
    }
  },
})
export default store