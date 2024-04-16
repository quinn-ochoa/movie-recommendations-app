<!-- <template>
    <header>
        <router-link v-bind:to="{ name: 'home' }">
            <img alt="Movie reel logo" src="../assets/grey-reel.png"/>
        </router-link>
        <h1>
            <i class="fa-solid fa-circle-user"></i>&nbsp; Welcome {{ $store.state.user.username }} &nbsp;|&nbsp;
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        </h1>
    </header>
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

    </form>
    {{ user }}
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
                    if (response.status == 201) {
                        this.$router.push({name:'home'});
                    }
                })
            }
        }
    }

};


</script> -->