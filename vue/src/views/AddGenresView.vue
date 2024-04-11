<template>
  <header>
      <img alt="Glasses logo" src="../assets/logo.png"/>
      <div>Search Bar</div>
      <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>
  </header>

    <h1>Update Profile</h1>
    <genre-form v-bind:genre="genre" />

  <footer>
    <div>
      <i class="fa-brands fa-facebook social-icon" ></i><i class="fa-brands fa-instagram social-icon"></i>
    </div>
  </footer>

</template>
  
  <script>
  import GenreForm from '../components/GenreForm.vue';
  
  export default {
    components: {
      GenreForm
    },
    data() {
      return {
        // Default data for a new topic
        genre: {
          //TODO
        },
      }
    } 
  };
  </script>
  
  <style scoped>
  </style>