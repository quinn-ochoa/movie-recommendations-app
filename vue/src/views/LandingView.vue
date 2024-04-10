<!-- eslint-disable vue/no-use-v-if-with-v-for -->
<template>
  <div class="container">
    <div class="image">
      <img 
        v-if="movies.length == 0" 
        src="../../src/assets/movie_collage.jpg" 
        alt="Movie Poster Collage"
      />

      <!-- test -->

      <!-- <div 
        class="movie-container" 
        v-for="movie in movies" 
        v-bind:key="movie.id" 
        v-bind:movie="movie"
      > 
      <div class="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
        </div>     
    </div> -->
      <movie-card 
        class="movie-container" 
        v-for="movie in movies" 
        v-bind:key="movie.id" 
        v-bind:movie="movie"
      > 
              
      </movie-card>
      <!-- test -->
    </div>
  
    <div class="home">
        <div class="content">
            <img alt="Glasses logo" src="../assets/logo.png">
            <h1>WeWatchWhat</h1>
            <p>Discover Your Next Favorite Film: Your Personalized Movie Journey Begins Here!</p>
          
            <form class="search" @submit.prevent="getMovies">
              <input type="search" class="form-control" v-model="query" />
              <input type="submit" class="btn"/>
            </form>
            <br/>
            
            <button type="sign-in">
              <router-link v-bind:to="{ name: 'login' }">
                Sign in
              </router-link>
            </button>&nbsp;

            <button type="create-account">
              <router-link v-bind:to="{ name: 'register' }">
                Create Account
              </router-link>
            </button>
        </div>
    </div>
  </div>
</template>
  
  <script>
  import axios from 'axios';
  import MovieCard from '../components/MovieCard.vue';
  import movieService from '../services/MovieService';

  export default {
    components:{
      MovieCard
    },
    data(){
      return{
        query: null,
        results:[],
        filter:{
          genre_ids:[],
          id: null,
          title: "",
          overview:"",
          poster_path:"",
          vote_average: null

        },
        //placeholder movies db
        movies:[]
        //end of db
      }
    },
    computed:{
      // filteredMovies(){
      //   let filteredMoviesList = this.movies;
      //   if(this.filter.title != "") {
      //     filteredMoviesList = filteredMoviesList.filter((movie) => movie.title.toLowerCase().includes(this.filter.title.toLowerCase()));
      //   }
      //   return filteredMoviesList;
      // }
    },
    methods:{

      // getMovies(searchStr){
      //   // console.log(this.movies);
      //   movieService.search(searchStr).then((response) => {
      //     this.movies = response.data;
      //     console.log(this.movies);
      //   })
      // },
      async getMovies(){
        await axios.get(`http://localhost:9000/search/${this.query}/`)
        .then((response) => {
          this.movies = response.data.results;
          // console.log(this.movies);
          this.results = Object.values(this.movies);
          console.log(this.results);
        })
        
      }
    // created(){
    //   this.getMovie(this.$route.params.searchStr);
    // }

    }
  };
  </script>
  
  <style scoped>
  .container{
    background-color: #FECE00;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas: 
      "image home";
    height: 100vh;
    max-height: 100vh;
    margin: 0px -10px;
    /* justify-items: center; */
  }
  .image{
    grid-area: image;
    background-color: white;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
  }

  .image img{
      height: 100%;
      width: 100%;
      object-fit: fill;
  } 

  .home{
    grid-area: home;
    /* padding: 20px; */
    background-color: #FECE00;
    position: relative;
  }
  .content{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    /* padding: 10px; */
    text-align: center;
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
  
  </style>