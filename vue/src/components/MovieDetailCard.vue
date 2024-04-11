<template>
    <div class="detail-card"
        v-bind:key="movie.id"
    >
        <div class="movie-title">{{ movie.title }}</div>
        <div class="">{{ movie.genre_names }}</div>
        <div class="">{{ movie.overview }}</div>
        <div class="">{{ movie.vote_average }}</div>
        <div class="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
        </div>
    </div>
</template>

<script>
    export default{
        name:'detail-card',
        props:{
            movie: Object
        }
    }
  
</script>

<style>

</style>