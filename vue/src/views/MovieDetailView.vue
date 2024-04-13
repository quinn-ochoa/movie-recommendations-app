<template>
    <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  </head>

  <!-- commented header out because repeating from another view -->
    <header>
      <img alt="movie reel logo" src="../assets/grey-reel.png"/>
      <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>

    </header>
    <movie-detail-card v-bind:movie="movie" />

    <footer>
      <div>
        <i class="fa-brands fa-facebook social-icon" ></i><i class="fa-brands fa-instagram social-icon"></i>
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

