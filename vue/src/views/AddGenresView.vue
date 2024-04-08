<template>
    <h1>Select Your Favorite Genres</h1>
    <genre-form v-bind:genre="genre" />
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