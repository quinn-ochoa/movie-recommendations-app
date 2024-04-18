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
    <h1>Update Profile</h1>

    <div class="update-container">
        <div class="box"> 
            <h3>Name: {{ userProfile.full_name }}</h3>
            <p>Email: {{ userProfile.email }}</p><br/>
            <p>Birthday: {{ userProfile.birthday }}</p>
            <div>Genres: </div>
            <div v-for="(value, key, index) in userProfile.favoriteGenres" v-bind:key="index">
                {{ value ? key + ", &nbsp;" : "" }}
            </div>
            <label class="switch">
                <input type="checkbox">
                <span class="slider"></span>
            </label>
        </div>

        <div class="box">
            <fieldset>
                <legend>Choose your genres</legend>
                <section>
                    <div>
                        <input type="checkbox" id="action" name="genre" value="true" v-model="genres.Action"/>
                        <label for="action">Action</label>
                    </div>
                    <div>
                        <input type="checkbox" id="adventure" name="genre" value="true" v-model="genres.Adventure"/>
                        <label for="adventure">Adventure</label>
                    </div>
                    <div>
                        <input type="checkbox" id="animation" name="genre" value="true" v-model="genres.Animation"/>
                        <label for="animation">Animation</label>
                    </div>
                    <div>
                        <input type="checkbox" id="comedy" name="genre" value="true" v-model="genres.Comedy"/>
                        <label for="comedy">Comedy</label>
                    </div>
                    <div>
                        <input type="checkbox" id="crime" name="genre" value="true" v-model="genres.Crime"/>
                        <label for="crime">Crime</label>
                    </div>
                    <div>
                        <input type="checkbox" id="documentary" name="genre" value="true" v-model="genres.Documentary"/>
                        <label for="documentary">Documentary</label>
                    </div>
                </section>
                <section>
                    <div>
                        <input type="checkbox" id="drama" name="genre" value="true" v-model="genres.Drama"/>
                        <label for="drama">Drama</label>
                    </div>
                    <div>
                        <input type="checkbox" id="family" name="genre" value="true" v-model="genres.Family"/>
                        <label for="family">Family</label>
                    </div>
                    <div>
                        <input type="checkbox" id="fantasy" name="genre" value="true" v-model="genres.Fantasy"/>
                        <label for="fantasy" >Fantasy</label>
                    </div>
                    <div>
                        <input type="checkbox" id="history" name="genre" value="true" v-model="genres.History"/>
                        <label for="history">History</label>
                    </div>
                    <div>
                        <input type="checkbox" id="horror" name="genre" value="true" v-model="genres.Horror"/>
                        <label for="horror">Horror</label>
                    </div>
                    <div>
                        <input type="checkbox" id="music" name="genre" value="true" v-model="genres.Music"/>
                        <label for="music">Music</label>
                    </div>
                    <div>
                        <input type="checkbox" id="mystery" name="genre" value="true" v-model="genres.Mystery"/>
                        <label for="mystery">Mystery</label>
                    </div>
                </section>
                <section>
                    <div>
                        <input type="checkbox" id="romance" name="genre" value="true" v-model="genres.Romance"/>
                        <label for="romance">Romance</label>
                    </div>
                    <div>
                        <input type="checkbox" id="science-fiction" name="genre" value="true" v-model="genres.ScienceFiction"/>
                        <label for="science-fiction">Science Fiction</label>
                    </div>
                    <div>
                        <input type="checkbox" id="tv-movie" name="genre" value="true" v-model="genres.TVMovie"/>
                        <label for="tv-movie">TV Movie</label>
                    </div>
                    <div>
                        <input type="checkbox" id="thriller" name="genre" value="true" v-model="genres.Thriller"/>
                        <label for="thriller">Thriller</label>
                    </div>
                    <div>
                        <input type="checkbox" id="war" name="genre" value="true" v-model="genres.War"/>
                        <label for="war">War</label>
                    </div>
                    <div>
                        <input type="checkbox" id="western" name="genre" value="true" v-model="genres.Western"/>
                        <label for="western">Western</label>
                    </div>
                </section>
                
            </fieldset>

            <div class="actions">
                <button class="btn-submit" type="submit" v-on:click.prevent="saveProfile()">
                    Save
                </button>
                <button class="btn-cancel" type="button" v-on:click.prevent="cancelForm()">
                    Cancel
                </button>
            </div>
        </div>
    </div>
</template>

<script>
    import userInfoService from '../services/UserInfoService'
    export default{
        props:['userProfile'],
        data(){
            return{
                // userProfile: this.userProfile
                // favoriteGenres: this.userProfile.favoriteGenres
                genres: this.userProfile.favoriteGenres
            }
        },
        methods:{
            saveProfile(){
                const current = this.userProfile;
                const info = {
                    user_id: this.$store.state.user.id,
                    email: current.email,
                    full_name: current.full_name,
                    username: this.$store.state.user.username,
                    birthday: current.birthday,
                    favoriteGenres: this.genres
                };
                userInfoService.updateUserInfo(info)
                .then(response => {
                    if (response.status === 200) {
                        this.$router.push({name:'home'});
                    }
                })
            },
            convertDate(day){
                return day.toDateString();
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

    .update-container{
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 10px;
        align-items: stretch
    }
    .box{
        padding: 10px 40px;
    }

    fieldset{
        display: flex;
    }

    section{
        flex-grow: 1;
    }
    h1{
        padding-left: 40px;
    }

</style>