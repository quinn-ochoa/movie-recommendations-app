<template>
    <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  </head>

    <movie-detail-card v-bind:movie="movie" />

    <footer>
      <div>
        <i class="fa-brands fa-facebook social-icon" ></i>
        <i class="fa-brands fa-instagram social-icon"></i>
      </div>
      <div class="tmdb-container">
        <img alt="tmdb logo" src="../assets/tmdb-logo.png"/>
      </div>
    </footer>
    
</template>

<script>
    import MovieDetailCard from '../components/MovieDetailCard.vue';
    import movieService from '../services/MovieService.js';

export default {
    components: {
        MovieDetailCard
    },
    data() {
        return {
            movie: {}
        }
    },
    methods: {
        getMovie(movieId) {
            movieService.getMovie(movieId)
            .then( response => {
                this.movie = response.data;
            })
        },

    },       
    created() {
            this.getMovie(this.$route.params.movieId)
        }
}
</script>

<style scoped>
    footer {
        display: flex;
        background-color: lightgrey;
        text-align: center;
        justify-content: center;
        align-items: center;
        height: 200px;
        margin-top: 40px;
        flex-direction: column;
        gap: 15px
    }

    footer > div > img {
        height: 50px;
        width: 70px;
    }

    footer > :first-child {
        display: flex;
        gap: 10px
    }

    .social-icon {
      font-size: 20px;
      height: 20px;
      margin-top: 40px;
      margin-right: 20px;
    }

</style>



