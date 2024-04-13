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

<style scoped></style>