<!-- <template>
    <movie-detail-card v-bind:key="movie.id"
</template> -->

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
        created() {
            this.getMovie(this.$route.params.movieId)
        }
    }
}
</script>

