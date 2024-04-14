<template>

      <update-genre-form v-bind:user-profile="userProfile"/>


    <footer>
      <div>
        <i class="fa-brands fa-facebook social-icon" ></i>
        <i class="fa-brands fa-instagram social-icon"></i>
      </div>
    </footer>
</template>

  <script>
  import UpdateGenreForm from '../components/UpdateGenreForm.vue';
  import userInfoService from '../services/UserInfoService';
  export default {
    components: {
      UpdateGenreForm
    },
    data(){
      return {
        userProfile:{}
      };
    },
    created(){
      userInfoService
      .getUserProfileById(this.$store.state.user.id)
      .then(response => {
        this.userProfile = response.data;
      })
    }
  };
  </script>

  <style scoped>
  </style>