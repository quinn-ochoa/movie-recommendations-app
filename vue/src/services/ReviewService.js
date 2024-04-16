import axios from 'axios';

export default {
    getReviewListByUser(id) {
        return axios.get(`/user/${id}/reviews/`);
    }
}