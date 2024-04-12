<template>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <!-- <div class="detail-card"
        v-bind:key="movie.id"
    > -->
    <div class="detail-card" v-bind:movie="movie">
        <div class="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/> 
            <div class="rating">{{ movie.vote_average }}</div> 
            <div id="stars">
                <span v-show="movie.vote_average > 1" class="fa fa-star fa-lg checked"></span>
                <span v-show="movie.vote_average > 3" class="fa fa-star fa-lg checked"></span>
                <span v-show="movie.vote_average > 5" class="fa fa-star fa-lg checked"></span>
                <span v-show="movie.vote_average > 7" class="fa fa-star fa-lg checked" ></span>
                <span v-show="movie.vote_average > 8" class="fa fa-star fa-lg checked"></span>

                <span v-show="movie.vote_average < 1" class="fa fa-star fa-lg unchecked"></span>
                <span v-show="movie.vote_average < 3" class="fa fa-star fa-lg unchecked"></span>
                <span v-show="movie.vote_average < 5" class="fa fa-star fa-lg unchecked"></span>
                <span v-show="movie.vote_average < 7" class="fa fa-star fa-lg unchecked" ></span>
                <span v-show="movie.vote_average < 8" class="fa fa-star fa-lg unchecked"></span>
            </div> 
        </div>

        <section id="card-info">
        <div class="titles">{{ movie.title }}</div>
        
        <div class="genres">{{ movie.genre_names }}</div>
        <div class="overview">{{ movie.overview }}</div>
       
        </section>
      
  
    </div>
</template>

<script>
    export default{
        name:'detail-card',
        props:{
            movie: Object
        },
        // computed: {
        //     movieDetail() {
        //         const movieId = this.$rout.params.movieId;
        //         return movieId;
        //     }
            
        // }
    };
  
</script>

<style>
 .detail-card {
    margin: auto;
    margin-top: 20px;
    display: flex;
    gap: 20px;
    justify-content: space-evenly;
    
 }

#card-info{
    display: flex;
    flex-direction: column;
    gap: 20px;

}

.poster > img {
    height: 300px;
}

.genres {
    display: flex;

}

.checked {
  color: orange;
  
}






</style>