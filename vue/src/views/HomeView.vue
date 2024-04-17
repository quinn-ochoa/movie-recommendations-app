<template>
  <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  </head>
  
  <div class="home">
    <header>
      <router-link v-bind:to="{ name: 'home' }">
        <img alt="Movie reel logo" src="../assets/grey-reel.png"/>
      </router-link>
      <div>
          <form class="search" @submit.prevent="getMovies">
              <input type="search" class="form-control" v-model="query" />
              <input type="submit" class="btn"/> 
              <!-- <i  type="submit" class=" fas fa-search btn"></i> -->
          </form>
      </div>
      <h1>
        <i class="fa-solid fa-circle-user"></i>&nbsp; Welcome {{ $store.state.user.username }} &nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </h1>
    </header>

    <body>
      <button v-if="selectFavoriteGenres() != 0"
        class="btn-add" 
        v-on:click="$router.push({ name: 'UpdateGenresView' })">
          Update Profile
      </button>

      <button v-else
        class="btn-add" 
        v-on:click="$router.push({ name: 'EditGenresView' })">
          Create Profile
      </button>

      <button
        class="btn-add" 
        v-on:click="$router.push({ name: 'UpdatePasswordView' })">
          Reset Password
      </button>

      <button 
        class="btn-add" 
        v-on:click="$router.push({ name: 'ReviewsDisplayView', params: { userId: $store.state.user.id } })">
          See Reviews
      </button>

      <!-- SEARCH RESULT -->
      <h2>Search results</h2>
      <!-- <div class="loading" v-if="isLoading">
          <img src="../assets/hourglass.gif" />
      </div> -->
      <!-- <div class="notLoading" v-else> -->
          <div id="search-container" v-if="searchMovies.length != 0">
            <div 
              class="display-card" 
              v-for="movie in searchMovies" 
              v-bind:key="movie.id"
              v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: movie.id } })"
            >
                  <div class="title">{{ movie.title }}</div>
                  <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
            </div>
          </div>
          <div v-else>
            <p>No search results</p>
          </div>
      <!-- </div> -->
      <!-- END OF SEARCH RESULT -->


      <!-- POPULAR MOVIES -->
      <h2>Based on popular demand</h2>
      <div id="popular-container">
        
          <div class="display-card" v-for="result in popular.results" v-bind:key="result.id" v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: result.id } })">
            <!-- <router-link v-bind:to="{name: 'MovieDetailView', params: {movieId: result.id}}" class="title" >{{ result.title }}</router-link> -->
            <div class="title">{{ result.title }}</div>
            <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path" />
          </div>
        <div>
          <movie-section title="Recommended by your favorite genre" v-bind:movies="recommendedByGenre" />
        </div>
      </div>

      <!-- LIKED MOVIES BY USER -->
      <h2 v-if="favorites.results != 0" >Movies you liked</h2>
      <div id="recommended-container" v-if="favorites.results != 0">
        
        <div class="display-card" v-for="result in favorites.results" v-bind:key="result.id" v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: result.id } })">
              <div class="title">{{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
        </div>
      </div>

      <!-- FAVORITE GENRES -->
      <div v-for="(genre, key) in genres" :key="key">
        <h2>{{ key }} movies you may enjoy</h2>
        <div id="favorite-genres-container" v-if="genre.results != 0">
        
          <div class="display-card" v-for="result in genre.results" v-bind:key="result.id" v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: result.id } })">
              <div class="title">{{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
          </div>
        </div>
      </div>

      <!-- CLASSIC MOVIES -->
      <h2>All time classic</h2>
      <div id="all-time-greats-container">
        
        <!-- <div>{{ allTimeGreats.results }}</div> -->
        <div class="display-card" v-for="result in allTimeGreats.results" v-bind:key="result.id" v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: result.id } })">
              <div class="title"> {{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
        </div>
      </div>

      <!-- RECOMMENDED MOVIES -->
      <h2 v-if="recommended4u.results != 0" >Recommended for you</h2>
      <div id="recommended-container" v-if="recommended4u.results != 0">
        
        <div class="display-card" v-for="result in recommended4u.results" v-bind:key="result.id" v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: result.id } })">
              <div class="title">{{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
        </div>
      </div>
    
      <!-- test -->
      
      <!-- {{ popular.results }} -->
      <!-- {{ $store.state.user.username }} -->
      <!-- {{ selectFavoriteGenres() }} -->
      <!-- test -->

    </body>

    <footer>
      <div>
        <i class="fa-brands fa-facebook social-icon" ></i>
        <i class="fa-brands fa-instagram social-icon"></i>
        <div class="tmdb-container">
        <img alt="tmdb logo" src="../assets/tmdb-logo.png"/>
        </div>
      </div>
    </footer>

    
  </div>
</template>

<script>
// import MovieSection from '../components/MovieSection.vue';
import { reactive } from 'vue';
import userInfoService from '../services/UserInfoService';
import axios from 'axios';

export default {
  

  components: {
    // MovieSection
  },

  data() {
    return{
      isLoading: true,
      query: null,
      searchMovies: [],

      popular : {
        results : []
      },

      allTimeGreats : {
        results : []
      },

      recommended4u : {
        results : []
      },

      favorites : {
        results : []
      },

      genres: {},

    };
  },   

  computed: {
    recommendedByGenre() {
      return this.movies
    }
  },

  methods: {

    //I don't think this is needed anymore -Devin
    selectFavoriteGenres(){
      let favGenres = this.genres;
      let resultArray = []
      delete this.genres.popular;
      delete this.genres.allTimeGreats;
      delete this.genres.recommended4u;
      delete this.genres.favorites;
      for (let key in favGenres){
        for (let item of favGenres[key].results){
          resultArray.push(item);
        }
      }
      return resultArray;
    },

    async getMovies(){
        await axios.get(`http://localhost:9000/search/${this.query}/user/${this.$store.state.user.id}/`)
        .then((response) => {
          // this.isLoading=false;
          this.searchMovies = response.data.results;
        })
    },

  },
  created() {
    let userId = this.$store.state.user.id;
    userInfoService.getRecommended(userId)
    .then(response => {
      this.genres = response.data;
      this.popular.results = response.data.popular.results;
      this.allTimeGreats.results = response.data.allTimeGreats.results;
      this.recommended4u.results = response.data.recommended4u.results;
      this.favorites.results = response.data.favorites.results;
    })
  },
};
</script>

<style scoped>
    img {
      margin-left: 10px;
      height: 100px;
    }

    header {
      background-color: #FECE00;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    footer {
        display: flex;
        background-color: lightgrey;
        text-align: center;
        justify-content: center;
        align-items: center;
        height: 200px;
        margin-top: 40px;
        flex-direction: column;
        gap: 15px
    }

    footer > div > img {
        height: 50px;
        width: 70px;
    }

    footer > :first-child {
        display: flex;
        gap: 10px
    }

    h1 {
      font-size: medium;
      margin-right: 10px;
    }

    h2 {
      font-size: medium;
      height: 100px;
    }
    .form-control{
      padding: 14px 20px;
      margin: 8px;
      border-radius:10px;
      border: none;
      height: 40px;
      text-align: left;
    }

    .btn {
      background-color: #012f6d;
      color: white;
      padding: 14px 20px;
      margin: 4px;
      border: none;
      cursor: pointer;
      border-radius: 10px;
    }

    .social-icon {
      font-size: 20px;
      height: 20px;
      margin-top: 40px;
      margin-right: 20px;
    }

    #popular-container, #favorite-genres-container,
    #all-time-greats-container, #recommended-container, #search-container {
      display: flex;
      overflow: auto;
      border-top: solid #012f6d;
      padding-top: 10px;
    }



    h2 {

      padding: 0px;
      margin-bottom: 5px;
      height: 20px;
      

    }

    button {
        background-color: #012f6d;
        color: white;
        padding: 14px 20px;
        margin: 8px;
        border: none;
        cursor: pointer;
        border-radius: 10px;
      }



    .display-card{
      position: relative;

      /* change width */
      height: 250px;
      width: auto;
      /* width: 400px; */
      
    }

    .movie-poster{
      display: block;
      height: 240px;
      width: auto;
      /* width: 100%; */

    }

    .title {
      text-align: center;
      /* transition: 0.8s; */
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(225, 225, 225, 0.8);
      color: black;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      opacity: 0;
      transition: opacity 0.25s;
      margin-left: 5px;
      font-weight: bold;
      
    }

    .title:hover {
      opacity: 1;
    }


    /* img:hover {
      opacity: 0.20;
      z-index: 0;
    } */


</style>
