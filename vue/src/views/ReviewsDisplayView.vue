<template>

<header>
        <router-link v-bind:to="{ name: 'home' }">
            <img alt="Movie reel logo" src="../assets/grey-reel.png"/>
        </router-link>
        <h1>
            <i class="fa-solid fa-circle-user"></i>&nbsp; Welcome {{ $store.state.user.username }} &nbsp;|&nbsp;
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        </h1>
    </header>
    <h1>Reviews: </h1>
   
    <div v-for="(review, key) in reviews" v-bind:key="key">
        {{ (key.split(" ").slice(0, ((key.split(" ")).length-1))).toString().replace(/[!.,]/g, ' ') }}
        <h3>{{ review.title }}</h3>
        <div id="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + review.poster_path"/> 
        </div>
    </div>

        <!-- <p>{{created}}</p>
    <div class="review" v-for="review in reviews" v-bind:key="review">
        TO BE CONFIRMED
      
        <h3>{{ review.title }}</h3>
        <div id="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + review.poster_path"/> 
        </div>
        <p>{{ printReview }}</p> 
        {{ Object.keys(reviews) }}
        {{ reviews }}
        </div>  -->

    <!-- <review-list v-bind:reviews="reviews"/> -->


   
</template>

<script>
// import ReviewList from '../components/ReviewList.vue';
import reviewService from '../services/ReviewService.js';

export default {
    // components: {
    //     ReviewList
    // },
    data() {
        return {
            reviews: {
            }
        };
    },

    computed: {

            appendTitle( key ) {
                
                let textArray = key.split(" ", 2);
                return textArray;
            },

            printReview(){
                let text = ''
                let object = this.reviews;
                for (let [key] of Object.entries(object)) {
                    text += (`Key: ${key}`);
                }
                return text;
            }


            
        },


    
    methods: {
        getReviewsByUser() {
            // let userId = parseInt(this.$route.params.user.userId);
            // reviewService
            // .getReviewListByUser(userId)
            //     .then(response => {
            //         this.reviews = response.data;
            //     })
                
            }
        },
        appendText( key ) {
                
                let textArray = key.split(" ", 2);
                return textArray;
            },

        created() {
            let userId = parseInt(this.$store.state.user.id);
            reviewService
            .getReviewListByUser(userId)
                .then(response => {
                    this.reviews = response.data;
                    this.title = response.data.title;
                });
        }
};
    
</script>

<style scoped>

</style>