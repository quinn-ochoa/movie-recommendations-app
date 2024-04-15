<template>
  <div id="register" class="text-center">
      <router-link v-bind:to="{ name: 'home' }">
        <img alt="Movie reel logo" src="../assets/grey-reel.png">
      </router-link>
      
      <!-- <h1>WeWatchWhat</h1> -->
      <h1>Create an account</h1>
      <form v-on:submit.prevent="register">
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <section id="all-entries">
        <section id="left-entries">
        <div class="form-input-group">
          <!-- <label for="username">Username</label> -->
          <input type="text" id="username" v-model="user.username" placeholder="Username" required autofocus />
        </div>
        <div class="form-input-group">
          <!-- <label for="password">Password</label> -->
          <input type="password" id="password" v-model="user.password" placeholder="Password"  required />
        </div>
        <div class="form-input-group">
          <!-- <label for="confirmPassword">Confirm Password</label> -->
          <input type="password" id="confirm-password" v-model="user.confirmPassword" placeholder="Confirm Password" required />
        </div>
        </section>
        <!-- <section id="right-entries">
          <div class="form-input-group">
          <label for="name">Name</label>
          <input type="text" id="name" v-model="user.name" placeholder="Name" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="email">Email</label>
          <input type="text" id="email" v-model="user.email" placeholder="Email" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="birthday">Birthday</label>
          <input type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="date" placeholder="Birthday" v-model="user.birthday" required autofocus />
        </div>
      </section> -->
        
      </section>
      <section id="buttons-section">
        <button type="submit">Sign up</button>
        <button type="cancel">Cancel</button><br/>
        <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      </section>
    </form>
  </div>
</template>

<script>
  import authService from '../services/AuthService';
  export default {
    data() {
      return {
        user: {
          username: '',
          password: '',
          confirmPassword: '',
          role: 'user',
        },
        registrationErrors: false,
        registrationErrorMsg: 'There were problems registering this user.',
      };
    },
    methods: {
      register() {
        if (this.user.password != this.user.confirmPassword) {
          this.registrationErrors = true;
          this.registrationErrorMsg = 'Password & Confirm Password do not match.';
        } else {
          authService
            .register(this.user)
            .then((response) => {
              if (response.status == 201) {
                this.$router.push({
                  path: '/login',
                  query: { registration: 'success' },
                });
              }
            })
            .catch((error) => {
              const response = error.response;
              this.registrationErrors = true;
              if (response.status === 400) {
                this.registrationErrorMsg = 'Bad Request: Validation Errors';
              }
            });
        }
      },
      clearErrors() {
        this.registrationErrors = false;
        this.registrationErrorMsg = 'There were problems registering this user.';
      },
    },
  };
</script>

<style scoped>
  #register {
    height: 100vh;
    font-family: Arial, Helvetica, sans-serif;
    background-color: #FFF3BF;
  }
  .form-input-group {
    margin-bottom: 1rem;
  }
  label {
    margin-right: 0.5rem;
  }
  h1 {
    text-align: center;
  }
  h2 {
    text-align: center;
    font-size: medium;
    margin-bottom: 20px;
  }
  
  #left-entries {

    display: flex;
    flex-direction: row;
    margin: auto;
    gap: 15px;
  }
  /* #right-entries {
    display: flex;
    flex-direction: column;
  } */

  #left-entries :first-child{
    border-radius:10px;
    border: none;
    height: 40px;
    text-align: center;
  }

  #all-entries {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 5px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    margin-bottom: 20px;
  }
  #buttons-section {
    text-align: center;
  }
  button {
    background-color: #012F6D;
    color: white;
    padding: 14px 20px;
    margin: 8px;
    border: none;
    cursor: pointer;
    border-radius: 10px;
  }
  fieldset {
    display: flex;
    flex-wrap: wrap;
    width: 40%;
    justify-content: space-around;
  }
  img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 100px;
  }
  #register :first-child:img {
  text-align: center;
  }


  .form-input-group{
    text-align: center;
  }

  #left-entries{
    text-align: center;
  }
  input[type="checkbox"] {
    margin-right: 5px;
  }
</style>