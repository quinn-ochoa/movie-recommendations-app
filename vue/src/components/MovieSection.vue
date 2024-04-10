<template>
    <h1>Category Of Movie Section</h1>
    <div class = "movies">
        <div class="movie" v-for="movie in movies" v-bind:key="movie.id">
         <!-- v-on:click=""viewMovieDetails(movie) -->
        <div class="movie-title">{{ movie.title }}</div>
         <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
        </div>
       
    </div>

</template>

<script>

export default {
    name: 'movie-section',
    props: ['title']
        // movie: Object
        
    

}
</script>