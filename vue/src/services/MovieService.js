import axios from 'axios';

export default {

    // //CONFIRM THESE 2 ENDPOINTS
    // list(){
    //     return axios.get(`/movies`)
    // },

    // listMovie(movieId){
    //     return axios.get(`/movies/${movieId}`)
    // },

    //SEARCH TERM
    search(searchStr){
        return axios.get(`/search/${searchStr}`)
    }
}