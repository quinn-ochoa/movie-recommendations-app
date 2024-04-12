<template>
    <!-- display card -->
    <div class="movie-card" 
        v-bind:key="movie.id"
    >
        <br/>
        <div class="movie-title">{{ movie.title }}</div>
        <!-- <p>{{ movie.genre_id }}</p> -->
        <div class="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
        </div>
        
    </div>
</template>

<script>
    export default{
        name:'movie-card',
        props:{
            movie: Object
        },
        data(){
            return{
                flipped: false
            }
        }
    }
</script>

<style>
 .movie-card{
    position: relative;
    /* height: 200px;
    width: auto; */
    /* width: 152px;
    height: 250px;
    margin-bottom: 5px;
    margin-top: -10px; */
    /* border: 1px solid red; */
  }

  .movie-card img{
    display: block;
  /* height: 200px;
  width: auto; */
    /* height: 100%;
    width: 100%;
    object-fit: fill;
    padding-top: -10px; */
  }

  .movie-title {
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
  /* margin-left: 5px; */
  /* margin-top: 23px; */
  font-weight: bold;
  
}

.movie-title:hover {
  opacity: 1;
}

</style>