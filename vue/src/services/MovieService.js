import axios from 'axios';

export default {

    //CONFIRM THIS ENDPOINT
    list(movieId){
        return axios.get(`/movies/${movieId}`)
    },

    //SEARCH TERM
    search(searchStr){
        return axios.get(`/search/${searchStr}`)
        
    }
}