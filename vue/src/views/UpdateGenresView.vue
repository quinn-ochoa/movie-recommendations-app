<template>
    <header>
        <img alt="Glasses logo" src="../assets/logo.png"/>
        <div>Search Bar</div>
        <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>
    </header>
      <h3>Update Profile</h3>
      <update-genre-form v-bind:user-profile="userProfile"/>
      <!-- <genre-form v-bind:genre="genre" /> -->
      {{ $store.state.user.userProfile }}
      <!-- {{ $store.state.user.userProfile }} -->
      <!-- {{ $store.state.user.userProfile.favoriteGenres }} -->
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