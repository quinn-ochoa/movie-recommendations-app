<template>
    <div class="movie-card"
        v-bind:key="movie.id"
    >
        <br/>
        <div v-on:click="flipped = !flipped">
            <!-- <div class="content" v-bind:key=flipped>
                <p class="movie-title">{{ movie.title }}</p>
                <p>{{ movie.genre_id }}</p>
            </div> -->
            
            <div class="poster" v-bind:key=flipped>
                <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/>
            </div>
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