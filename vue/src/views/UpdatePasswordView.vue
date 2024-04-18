<!-- <template>
    <update-password-form />
</template>

<script>
import UpdatePasswordForm from '../components/UpdatePasswordForm.vue';

export default {
    components: {
        UpdatePasswordForm
    },

    data() {
        return {
            user: {}
        };
    }
}
</script> -->

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
    <div class="form-container">
        <h1>Update Password</h1>

        <form @submit.prevent="saveNewPassword">

            <div>
                <input type="password" class="form-control" v-model="user.password" placeholder="Password"/>
            </div>
            <div>
                <input type="password" class="form-control" v-model="user.confirmPassword" placeholder="Confirm Password"/>
            </div>
            <button class="btn-submit" type="submit">
                Submit
            </button>
            <button class="btn-submit" type="button" v-on:click.prevent="cancelForm()">
                Cancel
            </button>

        </form>
    </div>
    
    <!-- {{ user }} -->
</template>

<script>
 import updatePasswordService from '../services/UpdatePasswordService';
//  import axios from 'axios';

export default {

    name: 'Reset',
    data(){
        return {
            // password: '',
            // passwordConfirm: ''
            user: {
                username: this.$store.state.user.username,
                password: '',
                confirmPassword: '',
                role: this.$store.state.user.role
            },
            resetPasswordErrors: false,
            resetPasswordErrorMsg: 'There were problems resetting this password.',
        };
    },
    
    methods: {

        // async saveNewPassword() {
        //     const response = await axios.put('reset', {
        //         password: this.password,
        //         passwordConfirm: this.passwordConfirm
        //     });

        // }

        // saveNewPassword(){
        //     const resetInfo = {
        //         username: this.$store.state.user.username,
        //         password: this.password,
        //         confirmPassword: this.confirmPassword,
        //         role: this.role
        //     };
        //     updatePasswordService
        //     .updatePassword(resetInfo)
        //     .then(response => {
        //         if (response.status === 200 || response.status === 201) {
        //                 this.$router.push({name:'home'});
        //         }
        //     });
        // }

        saveNewPassword(){
            if (this.user.password != this.user.confirmPassword) {
                this.resetPasswordErrors = true;
                this.resetPasswordErrorMsg = 'Password & Confirm Password do not match.';
            } else {
                updatePasswordService
                .updatePassword(this.user)
                .then((response) => {
                    if (response.status == 200) {
                        // this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                        // this.$store.commit("SET_USER", response.data.user);
                        this.$router.push("/");
                    }
                })
            }
        },
        cancelForm() {
            // Go back to previous page
            this.$router.back();
        }
    }

};


</script>

<style scoped>
    img {
      margin-left: 10px;
      height: 100px;
    }

    header {
      background-color: #FECE00;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

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
    .form-container{
        margin-left: 20px;
    }
    .form-control{
        padding: 3px 30px;
        margin: 10px 3px;
        border-radius:10px;
        border: 1px solid #012f6d;
        height: 35px;
        text-align: center;

    }

    button {
        background-color: #012f6d;
        color: white;
        padding: 14px 20px;
        margin: 8px;
        border: none;
        cursor: pointer;
        border-radius: 10px;
      }

    h1 {
    font-size: medium;
    margin-right: 10px;
        }

    h2 {
    font-size: medium;
    height: 100px;
     }

</style>