import { createStore as _createStore } from 'vuex';
import axios from 'axios';

// Timeout in ms. Notifications are removed after they timeout.
const NOTIFICATION_TIMEOUT = 3000;

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      // userProfile:{
      //   email:"",
      //   full_name:"",
      //   username: this.$store.state.user.username,
      //   birthday:"",
      //   favoriteGenres: {
      //       Action: false,
      //       Adventure: false,
      //       Horror: false,
      //       Romance: false,
      //       War: false,
      //       History: false,
      //       ScienceFiction: false,
      //       Western: false,
      //       Documentary: false,
      //       Drama: false,
      //       TVMovie: false,
      //       Thriller: false,
      //       Music: false,
      //       Crime: false,
      //       Fantasy: false,
      //       Animation: false,
      //       Family: false,
      //       Comedy: false,
      //       Mystery: false
      //   }
      // }
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      // SET_PROFILE(state, userProfile) {
      //   state.userProfile = userProfile;
      // }
      
    },
  });
  return store;
}
