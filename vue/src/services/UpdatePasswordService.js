import axios from 'axios';

export default {

updatePassword(resetInfo){
    return axios.put('/forgotPassword/', resetInfo);
}

}
