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

    <h3>Create Profile</h3>
    
    <form v-on:submit.prevent="submitForm">
        <!-- ="submitForm()"  -->
        <div class="form-input-group">
          <input type="text" id="name" v-model="userProfile.full_name" placeholder="Name" required autofocus />
        </div>
        <div class="form-input-group">
          <input type="text" id="email" v-model="userProfile.email" placeholder="Email" required autofocus />
        </div>
        <div class="form-input-group">
          <input type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="birthday" placeholder="Birthday" v-model="userProfile.birthday" required autofocus />
        </div>
        <fieldset>
            <legend>Choose your genres</legend>
            <section>
                <div>
                    <input type="checkbox" id="action" name="genre" value="true" v-model="userProfile.favoriteGenres.Action"/>
                    <label for="action">Action</label>
                </div>
                <div>
                    <input type="checkbox" id="adventure" name="genre" value="true" v-model="userProfile.favoriteGenres.Adventure"/>
                    <label for="adventure">Adventure</label>
                </div>
                <div>
                    <input type="checkbox" id="animation" name="genre" value="true" v-model="userProfile.favoriteGenres.Animation"/>
                    <label for="animation">Animation</label>
                </div>
                <div>
                    <input type="checkbox" id="comedy" name="genre" value="true" v-model="userProfile.favoriteGenres.Comedy"/>
                    <label for="comedy">Comedy</label>
                </div>
                <div>
                    <input type="checkbox" id="crime" name="genre" value="true" v-model="userProfile.favoriteGenres.Crime"/>
                    <label for="crime">Crime</label>
                </div>
                <div>
                    <input type="checkbox" id="documentary" name="genre" value="true" v-model="userProfile.favoriteGenres.Documentary"/>
                    <label for="documentary">Documentary</label>
                </div>
                <div>
                    <input type="checkbox" id="drama" name="genre" value="true" v-model="userProfile.favoriteGenres.Drama"/>
                    <label for="drama">Drama</label>
                </div>
            </section>

            <section>
                
                <div>
                    <input type="checkbox" id="family" name="genre" value="true" v-model="userProfile.favoriteGenres.Family"/>
                    <label for="family">Family</label>
                </div>
                <div>
                    <input type="checkbox" id="fantasy" name="genre" value="true" v-model="userProfile.favoriteGenres.Fantasy"/>
                    <label for="fantasy" >Fantasy</label>
                </div>
                <div>
                    <input type="checkbox" id="history" name="genre" value="true" v-model="userProfile.favoriteGenres.History"/>
                    <label for="history">History</label>
                </div>
                <div>
                    <input type="checkbox" id="horror" name="genre" value="true" v-model="userProfile.favoriteGenres.Horror"/>
                    <label for="horror">Horror</label>
                </div>
                <div>
                    <input type="checkbox" id="music" name="genre" value="true" v-model="userProfile.favoriteGenres.Music"/>
                    <label for="music">Music</label>
                </div>
                <div>
                    <input type="checkbox" id="mystery" name="genre" value="true" v-model="userProfile.favoriteGenres.Mystery"/>
                    <label for="mystery">Mystery</label>
                </div>
            </section>

            <section>
                <div>
                    <input type="checkbox" id="romance" name="genre" value="true" v-model="userProfile.favoriteGenres.Romance"/>
                    <label for="romance">Romance</label>
                </div>
                <div>
                    <input type="checkbox" id="science-fiction" name="genre" value="true" v-model="userProfile.favoriteGenres.ScienceFiction"/>
                    <label for="science-fiction">Science Fiction</label>
                </div>
                <div>
                    <input type="checkbox" id="tv-movie" name="genre" value="true" v-model="userProfile.favoriteGenres.TVMovie"/>
                    <label for="tv-movie">TV Movie</label>
                </div>
                <div>
                    <input type="checkbox" id="thriller" name="genre" value="true" v-model="userProfile.favoriteGenres.Thriller"/>
                    <label for="thriller">Thriller</label>
                </div>
                <div>
                    <input type="checkbox" id="war" name="genre" value="true" v-model="userProfile.favoriteGenres.War"/>
                    <label for="war">War</label>
                </div>
                <div>
                    <input type="checkbox" id="western" name="genre" value="true" v-model="userProfile.favoriteGenres.Western"/>
                    <label for="western">Western</label>
                </div>
            </section>

           
        </fieldset>
        <div class="actions">
                <button class="btn-submit" type="submit" v-on:click.prevent="submitForm()">Submit</button>
                <button class="btn-cancel" type="button" v-on:click.prevent="cancelForm()">Cancel</button>
        </div>
    </form>

</template>

<script>
import userInfoService from '../services/UserInfoService';

export default{

    data(){
        return {
            showForm: false,
            
            userProfile:{
                user_id: this.$store.state.user.id,
                email:"",
                full_name:"",
                username: this.$store.state.user.username,
                birthday:"",
                favoriteGenres: {
                    Action: false,
                    Adventure: false,
                    Horror: false,
                    Romance: false,
                    War: false,
                    History: false,
                    ScienceFiction: false,
                    Western: false,
                    Documentary: false,
                    Drama: false,
                    TVMovie: false,
                    Thriller: false,
                    Music: false,
                    Crime: false,
                    Fantasy: false,
                    Animation: false,
                    Family: false,
                    Comedy: false,
                    Mystery: false
                }
            }
            
        }
    },
    methods:{
        submitForm(){
            userInfoService
            .addUserInfo(this.userProfile)
            .then((response) => {
                if (response.status === 200) {
                    this.$router.push({name:'home'});
                }
            })
            .catch(error => {
                console.error(error);
            });
        },
        cancelForm() {
            // Go back to previous page
            this.$router.back();
        },
        
    }
}

</script>

<style scoped>
    header {
        background-color: #FECE00;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    img {
        height: 100px;   
    }

    fieldset{
        display: flex;
    }

    section{
        flex-grow: 1;
    }

    #birthday, #email, #name{
        padding: 3px 10px;
        margin: 3px;
        border-radius:5px;
        /* border: none; */
        height: 35px;
        text-align: left;
    }
</style>
