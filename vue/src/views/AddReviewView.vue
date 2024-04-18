<template>
    <div>
      
      <review-form v-bind:card="card" />
    </div>
    <footer>
      <div>
        <i class="fa-brands fa-facebook social-icon" ></i>
        <i class="fa-brands fa-instagram social-icon"></i>
        <div class="tmdb-container">
        <img alt="tmdb logo" src="../assets/tmdb-logo.png"/>
        </div>
      </div>
    </footer>
</template>

<script>
    import ReviewForm from '../components/ReviewForm.vue';
    export default {
        components:{
            ReviewForm
        },
        // data(){
        //     return{}
        // }
        
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

    .tmdb-container > img{
      height: 50px
    }
</style>