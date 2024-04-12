<template>
    <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  </head>
  
  <div class="home">
    <header>
      <img alt="Glasses logo" src="../assets/logo.png"/>
      <div>Search Bar</div>
      <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>

    </header>
    <body>
      <button 
        class="btn-add" 
        v-on:click="$router.push({ name: 'EditGenresView' })">
          Create Profile
      </button>

      <button 
        class="btn-add" 
        v-on:click="$router.push({ name: 'UpdateGenresView' })">
          Update Profile
      </button>

      <h2>Based on popular demand</h2>
      <div id="popular-container">
        
          <div class="display-card" v-for="result in popular.results" v-bind:key="result.id">
                <div class="title">{{ result.title }}</div>
                <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path" />
          </div>
        <!-- <div>
          <movie-section title="Recommended by your favorite genre" v-bind:movies="recommendedByGenre" />
        </div> -->
      </div>

      <h2 v-if="selectFavoriteGenres() != 0">Based on favorite genres</h2>
      <div id="favorite-genres-container" v-if="selectFavoriteGenres() != 0">
        
        <div class="display-card" v-for="result in selectFavoriteGenres()" v-bind:key="result.id">
          <div class="title">{{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
        </div>
      </div>

      <h2>All classics</h2>
      <div id="all-time-greats-container">
        
        <!-- <div>{{ allTimeGreats.results }}</div> -->
        <div class="display-card" v-for="result in allTimeGreats.results" v-bind:key="result.id">
          <div class="title"> {{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
        </div>
      </div>

      <h2 v-if="recommended4u.results != 0" >Recommended for you</h2>
      <div id="recommended-container" v-if="recommended4u.results != 0">
        
        <div class="display-card" v-for="result in recommended4u.results" v-bind:key="result.id">
          <div class="title">{{ result.title }}</div>
              <img class="movie-poster" :src ="'https://image.tmdb.org/t/p/original' + result.poster_path"/>
        </div>
      </div>
      

      <!-- test -->
      
      <!-- {{ popular.results }} -->
      <!-- {{ $store.state.user }} -->
      <!-- {{ selectFavoriteGenres() }} -->
      <!-- test -->

    </body>

    <footer>
      <div>
        <i class="fa-brands fa-facebook social-icon" ></i><i class="fa-brands fa-instagram social-icon"></i>
      </div>
    </footer>

    
  </div>
</template>

<script>
// import MovieSection from '../components/MovieSection.vue';
import userInfoService from '../services/UserInfoService';

export default {
  components: {
    // MovieSection
  },

  data() {
    return{

      // movies: {          
      //     id: null,
      //     title: "",
      //     overview:"",
      //     poster_path:"",
      //     vote_average: null
      // }
      popular : {
        results : []
      },

      allTimeGreats : {
        results : []
      },

      recommended4u : {
        results : []
      },

      genres: {}

    };
  },   

  computed: {
    recommendedByGenre() {
      return this.movies
    }
  },

  methods: {
    selectFavoriteGenres(){
      let favGenres = this.genres;
      let resultArray = [];
      delete this.genres.popular;
      delete this.genres.allTimeGreats;
      delete this.genres.recommended4u;
      for (let key in favGenres){
        for (let item of favGenres[key].results){
          resultArray.push(item);
        }
      }
      return resultArray;
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
    })
  },
};
</script>

<style>
img {
  margin-left: 10px;
  height: 100px;
}

header {
  background-color: #FECE00;
  display: flex;
  justify-content: space-between;

}

footer {
  background-color: lightgrey;
  text-align: center;
  height: 100px;
}

h1 {
  font-size: medium;
  margin-right: 10px;
}

h2 {
  font-size: medium;
  height: 100px;
}

.social-icon {
  font-size: 20px;
  height: 20px;
  margin-top: 40px;
  margin-right: 20px;
}

#popular-container, #favorite-genres-container,
#all-time-greats-container, #recommended-container {
  display: flex;
  overflow: auto;
  border-top: solid;
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
  height: 200px;
  width: auto;
  /* width: 400px; */
  
}

.movie-poster{
  display: block;
  height: 200px;
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
