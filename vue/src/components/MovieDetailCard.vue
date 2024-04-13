<template>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
    </head>
    <!-- <div class="detail-card"
        v-bind:key="movie.id"
    > -->
    <div class="detail-card" v-bind:movie="movie">
        <div id="poster">
            <img  :src ="'https://image.tmdb.org/t/p/original' + movie.poster_path"/> 
            <!-- <div class="rating">{{ fiveStarRating }}</div>  -->
            <div id="stars" >
                <span v-show="fiveStarRating > 1" class="fa fa-star fa-lg checked"></span>
                <span v-show="fiveStarRating > 2" class="fa fa-star fa-lg checked"></span>
                <span v-show="fiveStarRating > 3" class="fa fa-star fa-lg checked"></span>
                <span v-show="fiveStarRating > 4" class="fa fa-star fa-lg checked" ></span>
                <span v-show="fiveStarRating > 5" class="fa fa-star fa-lg checked"></span>

                <span v-show="fiveStarRating < 1" class="fa fa-star fa-lg unchecked"></span>
                <span v-show="fiveStarRating < 2" class="fa fa-star fa-lg unchecked"></span>
                <span v-show="fiveStarRating < 3" class="fa fa-star fa-lg unchecked"></span>
                <span v-show="fiveStarRating < 4" class="fa fa-star fa-lg unchecked" ></span>
                <span v-show="fiveStarRating < 5" class="fa fa-star fa-lg unchecked"></span>
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
                <button v-on:click="$router.push({ name: 'UpdateGenresView' })">
                    Add Review</button>
                <i onclick="flipLikeButton()" class="fa fa-thumbs-up" aria-hidden="true" id="fas"></i>
                <i onclick="flipLikeButton()" class="fa fa-thumbs-down" aria-hidden="true" id="fas"></i>
            </div>
           
        </section>
      
    
    </div>
</template>



<script>            
    export default{
        name:'detail-card',
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
                let convertedRating = parseFloat((movieRating / 2).toFixed(1));
                return convertedRating;
            },


        }
    };
  
</script>

<style>
 .detail-card {
    /* margin: auto; */
    margin-left: 10%;
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

#poster > img {
    height: 300px;
}

.genres {
    display: flex;

}

#stars{
    margin-left: 15px;
    margin-top: 5px;
}

.checked {
  color: orange;
  
}

.unchecked, .checked {
    margin-left: 3px;
}

#title-info {
    border-bottom: solid;
    width: 80%;
}

#genre-info {
    display: flex;
    align-items: baseline;
    gap: 8px;
}



.overview {
    margin-top: 8px;
    width: 80%;
}

footer {
    margin-top: 20px;
}

.titles{
    font-weight: bold;
}
.fa {
  font-size: 70px;
  cursor: pointer;
  
}

.fa:hover{
    color: navy;
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