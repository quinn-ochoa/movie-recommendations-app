<template>
  <div class="container">
      <div id="login">
        <form v-on:submit.prevent="login">
          <router-link v-bind:to="{ name: 'home' }">
            <img alt="Movie reel logo" src="../assets/grey-reel.png">
          </router-link>
            
          <h1>Log In</h1>
            <div role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <div class="form-input-group">
              <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
            </div>
            <div class="form-input-group">
              <input type="password" id="password" placeholder="Password" v-model="user.password" required />
            </div>
            <button type="submit">Sign in</button>
            <button type="cancel">Cancel</button>
            <p>
            <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
         
        </form>
      </div>
  </div>

</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

.container{
  /* position: relative; */
  display: flex;
  align-items: center;
  justify-content: center;
}

#login{
  width: 400px;
  height: 400px;
  padding: 10px;
  background-color: #FECE00;
  /* position: absolute;
  top: 50%; */
  /* left: 50%; */
  /* transform: translate(30%, 30%); */
    /* padding: 10px; */
  text-align: center;
}
.form-input-group {
  margin-bottom: 1rem;
}

#username, #password{
    padding: 3px 30px;
    margin: 3px;
    border-radius:10px;
    border: none;
    height: 35px;
    text-align: center;

}

label {
  margin-right: 0.5rem;
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

img {
  height: 100px;
}

</style>