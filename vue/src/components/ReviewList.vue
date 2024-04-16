<template>
    <p>testing reviews list display</p>
        <div class="review" v-for="review in reviews" v-bind:key="review.title">
        <!-- TO BE CONFIRMED -->
      
        <h3>{{ review.title }}</h3>
        <div id="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/> 
        </div>
        <p>{{ review.description }}</p>

        </div>  
</template>


<script>
export default {
    props: {
        reviews: {
            type: Object
        }
    }
};
</script>
