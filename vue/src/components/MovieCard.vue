<template>
    <div class="movie-card"
        v-bind:key="movie.id"
    >
        <br/>
        <p>Output:  </p>
        <p>{{ movie.title }}</p>
        <p>{{ movie.genre_id }}</p>
        <!-- <img class="poster" :src ="'../../src/assets/test' + movie.poster_path"/> -->
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
 .movie{
  /* border: 2px solid black; */
  border-radius: 10px;
  width: 250px;
  height: 350px;
  /* margin: 20px; */
  /* position: relative; */

  }
</style>