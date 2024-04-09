<template>
    <div class="movie-card"
        v-bind:key="movie.id"
    >
        <br/>
        <!-- <div class="movie-title">{{ movie.title }}</div> -->
        <!-- <p>{{ movie.genre_id }}</p> -->
        <div class="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
        </div>
        <br/>
    </div>
</template>

<script>
    export default{
        name:'movie-card',
        props:{
            movies: Object
        }
    }
</script>

<style>
 .movie-card{
    width: 152px;
    height: 250px;
    margin-bottom: 5px;
    margin-top: -10px;
    /* border: 1px solid red; */
  }

  .movie-card img{
    height: 100%;
    width: 100%;
    object-fit: fill;
    padding-top: -10px;
  }

</style>