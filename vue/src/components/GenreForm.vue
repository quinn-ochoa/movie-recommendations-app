<template>
    
    <form v-on:submit.prevent="submitForm" >
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
            </section>

            <section>
                <div>
                    <input type="checkbox" id="drama" name="genre" value="true" v-model="userProfile.favoriteGenres.Drama"/>
                    <label for="drama">Drama</label>
                </div>
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

            <div class="actions">
            <button class="btn-submit" type="submit" >Submit</button>
            <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
            </div>
        </fieldset>
    </form>

</template>

<script>
import userInfoService from '../services/UserInfoService'

export default{
    props:{
        profile:{
            type: Object
        }
    },

    data(){
        return {
            showForm: false,
            userProfile:{
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
            // if(this.userProfile.full_name)
            userInfoService
            .addUserInfo(this.userProfile)
            .then(response =>{
                if(response.status === 201){
                
                    this.$store.commit(
                        'SET_NOTIFICATION', {
                        message:'Added new user info',
                        type:'success'
                        }
                );
                this.$router.push({name: 'HomeView'})
                }
            })
        },
        cancelForm() {
            // Go back to previous page
            this.$router.back();
        },
    }
}

</script>