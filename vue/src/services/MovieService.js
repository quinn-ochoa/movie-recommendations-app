import axios from 'axios';

export default {

   
    //SEARCH TERM
    search(searchStr){
        return axios.get(`/search/${searchStr}`)
    },

    getMovie(movieId) {
        return axios.get(`/movie/${movieId}/`)
      }

    //POST FAV GENRES

    // addGenres(genres){
    //     return axios.post('/user/profile/update/', genres);
    // }
}