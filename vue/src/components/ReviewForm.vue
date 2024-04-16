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

    <form v-on:submit.prevent="submitForm" class="cardForm">
      <h1>Add New Review</h1>
      <div>Favorite?<input type="checkbox" v-bind:checked="addCard.liked" v-on:change="onFavoriteChange"/> </div>
  
      <div class="form-group">
        <label for="description">Your review:</label>
        <textarea id="description" class="form-control" v-model="addCard.review"></textarea>
      </div>

      <button class="btn btn-submit">Submit</button>
      <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
    </form>
    {{ addCard }}
</template>

<script>
import userInfoService from '../services/UserInfoService';

export default {
    // props: {
    //     card: {
    //     type: Object,
    //     required: true
    //     }
    // },
    data(){
      return {
        addCard:{
          movie_id: this.$route.params.movieId,
          user_id: this.$store.state.user.id,
          liked: false,
          review:""
        } 
      }
    },
    methods: {
        submitForm(){
          userInfoService
          .addReview(this.addCard)
          .then(
            this.$router.push({name:'MovieDetailView'})
          )
        },
        cancelForm() {
        // Go back to previous page
        this.$router.back();
        },
        onFavoriteChange() {
          this.addCard.liked = !this.addCard.liked;
        }
    }
    
}

</script>

<style scoped>
    .cardForm {
    padding: 10px;
    margin-bottom: 10px;
    }

    .form-group {
    margin-bottom: 10px;
    margin-top: 10px;
    }

    label {
    display: inline-block;
    margin-bottom: 0.5rem;
    }

    .form-control {
    display: block;
    width: 80%;
    height: 30px;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
    }

    textarea.form-control {
    height: 75px;
    font-family: Arial, Helvetica, sans-serif;
    }

    select.form-control {
    width: 20%;
    display: inline-block;
    margin: 10px 20px 10px 10px;
    }

</style>