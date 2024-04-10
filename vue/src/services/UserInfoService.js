import axios from 'axios';

export default {

  addUserInfo(info) {
    return axios.post('/user/profile/create/', info)
  },

  updateUserInfo(info){
    return axios.put('/user/profile/update/', info)
  },

  getRecommended(userId) {
    return axios.get(`/user/${userId}/recommended/`)
  }
}