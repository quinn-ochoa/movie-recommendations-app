<template>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
    </head>
      <!-- commented header out because repeating from another view -->
    <header>
        <router-link v-bind:to="{ name: 'home' }">
            <img alt="Movie reel logo" src="../assets/grey-reel.png"/>
        </router-link>

        <h1>
            <i class="fa-solid fa-circle-user"></i>&nbsp; Welcome {{ $store.state.user.username }} &nbsp;|&nbsp;
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        </h1>

    </header>
    <!-- <div class="detail-card"
        v-bind:key="movie.id"
    > -->
    <body >
    <div class="detail-card" v-bind:movie="movie">
        <div id="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/> 
            <!-- <div class="rating">{{ fiveStarRating }}</div>  -->
            <div id="stars" >
                <span v-show="fiveStarRating > 1" class="fa fa-star checked"></span>
                <span v-show="fiveStarRating > 2" class="fa fa-star checked"></span>
                <span v-show="fiveStarRating > 3" class="fa fa-star checked"></span>
                <span v-show="fiveStarRating > 4" class="fa fa-star checked" ></span>
                <span v-show="fiveStarRating > 5" class="fa fa-star checked"></span>

                <span v-show="fiveStarRating < 1" class="fa fa-star unchecked"></span>
                <span v-show="fiveStarRating < 2" class="fa fa-star unchecked"></span>
                <span v-show="fiveStarRating < 3" class="fa fa-star unchecked"></span>
                <span v-show="fiveStarRating < 4" class="fa fa-star unchecked" ></span>
                <span v-show="fiveStarRating < 5" class="fa fa-star unchecked"></span>
                <!-- <span v-show="fiveStarRating < 6" class="fa fa-star fa-lg unchecked"></span> -->
            </div> 

        </div>

        <section id="card-info">
            <div id="title-info">
                <div class="titles">{{ movie.title }}</div>
            </div>
            <div id="genre-info">
                <h2>Genre: </h2>
                <div class="genres">{{ genreText }}</div> 
            </div>
            <div>
               <h2>Overview:</h2>
                <div class="overview">{{ movie.overview }}</div>
            </div>
            <div>
                <button v-on:click="$router.push({ name: 'home' })">
                    Back 
                </button>
                <button v-if="!collectFavMoviesId().includes(movie.id)" v-on:click="$router.push({ name: 'AddReviewView' })">
                    Add Review
                </button>
                <button v-else v-bind="addCard.liked = false" v-on:click.prevent="updateFavorite()">
                    Unlike
                </button>
                

                <!-- v-bind:id="'favorite_' + review.id" v-bind:checked="review.favorited"
                    v-on:click="onFavoritedChange(review)" -->

                <!-- <i onclick="flipLikeButton(this)" class="fa fa-thumbs-up fa-lg" id="fas"></i> -->
                <!-- <i onclick="flipLikeButton(this)" class="fa fa-thumbs-down" aria-hidden="true" id="fas"></i> -->
            
                <!-- PRINT OUT MOVIE DETAIL -->
                <!-- {{ movie.id }} -->
                <!-- {{ collectFavMoviesId() }} -->
                <!-- {{ addCard }}
                {{ $store.state.user.id }} -->
                <!-- {{ currReview }} -->
                <!-- {{ removeFavorite(collectFavMoviesId()) }} -->
            </div>
           
        </section>
    </div>
    </body>
</template>



<script>  
    
    import axios from 'axios';
    import userInfoService from '../services/UserInfoService';

    export default{
        name:'detail-card',
        data(){
            return{
                currReview: null,
                favorites : {
                    results : []
                },
                addCard:{
                    movie_id: this.$route.params.movieId,
                    user_id: this.$store.state.user.id,
                    liked: true,
                    review: this.currReview
                }, 
            }
        },
        props:{
            movie: Object
        },
        computed: {

            genreText(movie) {
                let genreArray = this.movie.genre_names;
                let text = "";
                for (let i = 0; i < genreArray.length; i++){
                   i !== (genreArray.length-1) ? text += genreArray[i] + ", " : text += genreArray[i];
                }
                return text;
            },

            fiveStarRating(movie) {
                let movieRating = this.movie.vote_average
                let convertedRating = parseFloat((movieRating / 2));
                return convertedRating;
            },
        },
        methods:{
            async getReview(){
                await axios.get(`http://localhost:9000/user/${this.$store.state.user.id}/movie/${this.movie.id}/review/`)
                .then((response) => {
                    // let string = response.data;
                    this.currReview = response.data;
                    // console.log("current review:" + string.toString());
                })
        
            },
            collectFavMoviesId(){
                const resultArr = [];
                for (let i = 0; i < this.favorites.results.length; i++ ){
                    resultArr.push(this.favorites.results[i].id);
                }
                return resultArr;
            },
            updateFavorite(){
                userInfoService
                .addReview(this.addCard)
                .then(
                    // this.getReview()
        
                    this.$router.push({name:'home'})
                )
                // console.log("what is" + this);
                
            },
            
        },
        created(){
            let userId = this.$store.state.user.id;
            userInfoService.getRecommended(userId)
            .then(response => {
                this.favorites.results = response.data.favorites.results;
            })
        }
    };
  
</script>

<style scoped>

    header {

        background-color: #FECE00;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    img {
      margin-left: 10px;
      height: 100px;
    }

    h1 {
        font-size: 15px;
        margin-right: 10px;
    }

    .detail-card {
        /* margin: auto; */
        margin-left: 10%;
        margin-top: 5%;
        margin-bottom: 10%;
        display: flex;
        gap: 20px;
        justify-content: space-evenly;
        height: 50vh;
        
    }

    #card-info{
        display: flex;
        flex-direction: column;
        gap: 20px;
    }

    #poster > img {
        height: 300px;
    }

    .genres {
        display: flex;

    }

    #stars{
        display: flex;
        margin-left: 10px;
        margin-top: 5px;
        font-size: 30px;
        height: 20px;
        width: fit-content;

    }

    .checked {
    color: orange;
    
    }

    .unchecked, .checked {
        margin-left: 3px;
        height: 1px;

    }

    #title-info {
        color: #012F6D;
        border-bottom: solid #012f6d;
        width: 80%;
    }

    #genre-info {
        display: flex;
        align-items: baseline;
        gap: 8px;
    }

    h2 {
        font-size: 15px;
        /* border:solid; */
        /* height: 10px; */
    }

    .overview {
        margin-top: 8px;
        width: 80%;
    }

    .titles{
        font-weight: bold;
        font-size: 15px;
    }

    p {
        display: inline-block;
        margin-left: 10px;
    }

    button {
    background-color: #012F6D;
    color: white;
    padding: 14px 20px;
    margin: 8px;
    border: none;
    cursor: pointer;
    border-radius: 10px;
    }


</style>