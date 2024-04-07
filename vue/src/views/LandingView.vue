<!-- eslint-disable vue/no-use-v-if-with-v-for -->
<template>
  <div class="container">
    <div class="image">
      <img 
        v-if="filteredMovies.length == 0 || filteredMovies.length == movies.length " 
        src="../../src/assets/movie_collage.jpg" 
        alt="Movie Poster Collage"
      />

      <!-- test -->
      <movie-card 
        class="movie-container" 
        v-else v-for="movie in filteredMovies" 
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
          
            <div class="search">
              <input type="text" id="search" v-model="filter.title" required autofocus />
            </div><br/>
            
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
  import MovieCard from '../components/MovieCard.vue';

  export default {
    components:{
      MovieCard
    },
    data(){
      return{
        filter:{
          genre_ids:[],
          id: null,
          title: "",
          overview:"",
          poster_path:"",
          vote_average: null

        },
        //placeholder movies db
        movies:[
          {
              genre_ids: [],
              id: 869732,
              title: "Man",
              overview: "",
              poster_path: "/4.jpg",
              vote_average: 6.7
          },
          {
              genre_ids: [
                  10752,
                  18
              ],
              id: 435577,
              title: "The 12th Man",
              overview: "After a failed anti-Nazi sabotage mission leaves his eleven comrades dead, a Norwegian resistance fighter finds himself fleeing the Gestapo through the snowbound reaches of Scandinavia.",
              poster_path: "/3.jpg",
              vote_average: 7.173
          },
          {
              genre_ids: [
                  27,
                  9648
              ],
              id: 1035982,
              title: "Hell House LLC Origins: The Carmichael Manor",
              overview: "A group of cold case investigators stay at the Carmichael Manor, site of the grisly and unsolved murders of the Carmichael family back in the eighties. After four nights, the group was never heard from again. What is discovered on their footage is even more disturbing than anything found on the Hell House tapes.",
              poster_path: "/1.jpg",
              vote_average: 6.375
          },
          {
              genre_ids: [
                  28,
                  18
              ],
              id: 658009,
              title: "Ip Man: Kung Fu Master",
              overview: "Ip Man’s promising career as a Policeman is ruined after he is framed for murder and targeted by a mob boss’s daughter.",
              poster_path: "/2.jpg",
              vote_average: 6.6
          }
        ]
        //end of db
      }
    },
    computed:{
      filteredMovies(){
        let filteredMoviesList = this.movies;
        if(this.filter.title != "") {
          filteredMoviesList = filteredMoviesList.filter((movie) => movie.title.toLowerCase().includes(this.filter.title.toLowerCase()));
        }
        return filteredMoviesList;
      }
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
    padding: 10px;
    text-align: center;
  }

  .movie-container {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
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