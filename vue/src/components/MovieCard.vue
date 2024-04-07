<template>
    <div class="movie-card"
        v-bind:key="movie.id"
    >
        <br/>
        <p>{{ movie.title }}</p>
        <p>{{ movie.genre_id }}</p>
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
            movie: Object
        }
    }
</script>

<style>
 .movie-card{
  /* border: 2px solid black; */
  border-radius: 3px;
  width: 130px;
  height: 160px;
  border: 1px solid red;
  /* background-color: brown; */
  /* margin: 20px; */
  /* position: relative; */

  }
</style>