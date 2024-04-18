<template>

    <genre-form/>
    <!-- <genre-form v-bind:genre="genre" /> -->

    <!-- PRINT OUT CURRENT USER  -->
    <!-- {{ $store.state.user }}
    {{ $store.state.userProfile }} -->

    <footer>
        <div>
        <i class="fa-brands fa-facebook social-icon" ></i>
        <i class="fa-brands fa-instagram social-icon"></i>
        </div>
    </footer>
</template>

<script>
    import GenreForm from '../components/GenreForm.vue';
    // import userInfoService from '../services/UserInfoService';

    export default{
        components: {
            GenreForm
        },
       
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

    .social-icon {
      font-size: 20px;
      height: 20px;
      margin-top: 40px;
      margin-right: 20px;
    }

</style>