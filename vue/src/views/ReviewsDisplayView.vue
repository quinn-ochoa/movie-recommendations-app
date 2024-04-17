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
    <h1>Your Reviews: </h1>
   
    <div v-for="(review, key) in reviews" v-bind:key="key" id="review-container">
        <!-- v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: review.id } }) -->

        <div>
            <div id="poster">
                <img  
                :src ="'https://image.tmdb.org/t/p/original' + review.poster_path"
                v-on:click="$router.push({ name: 'MovieDetailView', params: { movieId: review.id } })"
                /> 
            </div>
        </div>

        <div id="review-movie-title">
            <div>
              <h2>{{ review.title }}</h2>  
            </div>
            <div>
            <h2>Review: </h2>
            <div>{{ (key.split(" ").slice(0, ((key.split(" ")).length-1))).toString().replace(/[!.,]/g, ' ') }}</div>
            </div>
            
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

#review-container {
    /* margin-top: 5%; */
    margin-left: 10%;
    margin-bottom: 3%;
    display: flex;
    align-items: top;
    gap: 20px;
    /* padding-top: 10px; */
}

h1 {
    font-size: 20px;
    height: 50px;
    margin-left: 10%;
    padding-top: 10px;

}

#review-movie-title > :nth-child(1){
    color: #012F6D;
    border-bottom: solid #012f6d;
    width: 60vw;
    
}

#review-movie-title{
    display: flex;
    flex-direction: column;
    gap: 20px;
}

</style>