import axios from 'axios';

export default {

  addUserInfo(info) {
    return axios.post('/user/profile/update/', info)
  },

  getRecommended(userId) {
    return axios.get(`/user/${userId}/recommended/`)
  }



}