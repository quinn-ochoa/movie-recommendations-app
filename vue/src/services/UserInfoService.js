import axios from 'axios';

export default {

  addUserInfo(info) {
    return axios.post('/user/profile/update/', info)
  }



}