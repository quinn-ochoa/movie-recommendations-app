<!-- eslint-disable vue/no-use-v-if-with-v-for -->
<template>

  

<div class="container">
  <div class="loading" v-if="isLoading">
          <img src="../assets/hourglass.gif" />
  </div>
  <div class="notLoading" v-else>
    <div class="image">
      <img 
        v-if="movies.length == 0" 
        src="../../src/assets/movie_collage.jpg" 
        alt="Movie Poster Collage"
      />

      <movie-card 
        
        v-for="movie in movies" 
        v-bind:key="movie.id" 
        v-bind:movie="movie"
      > 
              
      </movie-card>

    </div>
    <div v-if="noSearchResults">
      <p>No search results</p>
    </div>
  </div>

   

  
    <div class="home">
        <div class="content">
            <img alt="Grey reel logo" src="../assets/grey-reel.png">
            <p>Discover Your Next Favorite Film: <br/>
              Your Personalized Movie Journey Begins Here!</p>
          
            <form class="search" @submit.prevent="getMovies">
              <input type="search" class="form-control" v-model="query" />
              <input type="submit" class="btn" value="Search"/>
            </form>
            <br/>
            
            <button type="sign-in">
              <router-link class="router-button" v-bind:to="{ name: 'login' }">
                Sign in
              </router-link>
            </button>&nbsp;

            <button type="create-account">
              <router-link class="router-button" v-bind:to="{ name: 'register' }">
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
        noResults: false,
        //placeholder movies db
        isLoading: false,
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
        this.isLoading = true;

        await axios.get(`http://localhost:9000/search/${this.query}/`)
        .then((response) => {
          this.isLoading = false;
          this.movies = response.data.results;
          // console.log(this.movies);
          this.results = Object.values(this.movies);
          console.log(this.results);

          if (this.results.length === 0) {
            this.noResults = true;

          }

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
    background-color: white;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas: 
      "image home";
    height: 100vh;
    max-height: 100vh;

    /* margin: 0px -10px; */
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
 
  button, .btn {
    background-color: #012f6d;
    color: white;
    padding: 14px 20px;
    margin: 4px;
    border: none;
    cursor: pointer;
    border-radius: 10px;
  }
  .form-control{
    padding: 14px 20px;
    margin: 8px;
    border-radius:10px;
    border: none;
    height: 40px;
    text-align: left;
  }

  .router-button {
    color: white;
    text-decoration: inherit;
  }

  .content > img {
    height: 150px;

  }

  .container > .loading {
    text-align: center;
    justify-content: center;
    margin-top: 45%;
    color: #FECE00;

  }


  
  </style>