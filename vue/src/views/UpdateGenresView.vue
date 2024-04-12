<template>
  <header>
      <img alt="Glasses logo" src="../assets/logo.png"/>
      <div>Search Bar</div>
      <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>
  </header>

    <h1>Update Profile</h1>
    <update-genre-form/>
    <!-- <genre-form v-bind:genre="genre" /> -->

    {{ $store.state.user }}
    {{ $store.state.user.userProfile }}
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
        // this.userProfile = response.data;
        this.$store.commit("SET_PROFILE", response.data)
      })
    }
  };
  </script>
  
  <style scoped>
  </style>