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

    .social-icon {
      font-size: 20px;
      height: 20px;
      margin-top: 40px;
      margin-right: 20px;
    }

    
  </style>