<template>
    <header>
        <img alt="Glasses logo" src="../assets/logo.png"/>
        <div>Search Bar</div>
        <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>
    </header>

    <h1>Create Profile</h1>
    <genre-form v-bind:genre="genre" />

    <!-- PRINT OUT CURRENT USER  -->
    {{ $store.state.user }}
    {{ $store.state.userProfile }}

    <footer>
        <div>
        <i class="fa-brands fa-facebook social-icon" ></i><i class="fa-brands fa-instagram social-icon"></i>
        </div>
    </footer>
</template>

<script>
    import GenreForm from '../components/GenreForm.vue';
    import userInfoService from '../services/UserInfoService';

    export default{
        components: {
        GenreForm
        },
        data(){
            return{
                userProfile:{}
            }
        },
        methods:{
            getUserProfile(id){
                userInfoService.getUserProfileById(id)
                .then(response => {
                    this.genre = response.data;
                })
            }
        },
        created(){
            this.getUserProfile(this.$store.state.user.id)
        }
    }
</script>

<style scoped></style>