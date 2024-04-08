<template>
  <div id="register" class="text-center">   
      <img alt="Glasses logo" src="../assets/logo.png">   
      <h1>WeWatchWhat</h1>
      <h2>Create an account</h2>

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

      <fieldset>
        <legend>Choose your genres</legend>
      <section>
      <div>
        <input type="checkbox" id="action" name="genre" value="action" />
        <label for="action">Action</label>
      </div>
      <div>
        <input type="checkbox" id="adventure" name="genre" value="adventure" />
        <label for="adventure">Adventure</label>
      </div>
      <div>
        <input type="checkbox" id="animation" name="genre" value="animation" />
        <label for="animation">Animation</label>
      </div>
      <div>
        <input type="checkbox" id="comedy" name="genre" value="comedy" />
        <label for="comedy">Comedy</label>
      </div>
      <div>
        <input type="checkbox" id="crime" name="genre" value="crime" />
        <label for="crime">Crime</label>
      </div>
      <div>
        <input type="checkbox" id="documentary" name="genre" value="documentary" />
        <label for="documentary">Documentary</label>
      </div>
      </section>
      <section>
      <div>
        <input type="checkbox" id="drama" name="genre" value="drama" />
        <label for="drama">Drama</label>
      </div>
      <div>
        <input type="checkbox" id="family" name="genre" value="family" />
        <label for="family">Family</label>
      </div>
      <div>
        <input type="checkbox" id="history" name="genre" value="history" />
        <label for="history">History</label>
      </div>
      <div>
        <input type="checkbox" id="horror" name="genre" value="horror" />
        <label for="horror">Horror</label>
      </div>
      <div>
        <input type="checkbox" id="music" name="genre" value="music" />
        <label for="music">Music</label>
      </div>
      <div>
        <input type="checkbox" id="mystery" name="genre" value="mystery" />
        <label for="mystery">Mystery</label>
      </div>
      </section>
      <section>
      <div>
        <input type="checkbox" id="romance" name="genre" value="romance" />
        <label for="romance">Romance</label>
      </div>
      <div>
        <input type="checkbox" id="science-fiction" name="genre" value="science-fiction" />
        <label for="science-fiction">Science Fiction</label>
      </div>
      <div>
        <input type="checkbox" id="tv-movie" name="genre" value="tv-movie" />
        <label for="tv-movie">TV Movie</label>
      </div>
      <div>
        <input type="checkbox" id="thriller" name="genre" value="thriller" />
        <label for="thriller">Thriller</label>
      </div>
      <div>
        <input type="checkbox" id="war" name="genre" value="war" />
        <label for="war">War</label>
      </div>
      <div>
        <input type="checkbox" id="western" name="genre" value="western" />
        <label for="western">Western</label>
      </div>
      </section>
        </fieldset>
      </section>
    
      <section id="buttons-section">
        <button type="submit">Sign up</button>
        <button type="cancel">Cancel</button>
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

}
#register {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #fff3bf;
}

#left-entries {
  display: flex;
  flex-direction: row;
  gap: 10px;
  
}

/* #right-entries {
  display: flex;
  flex-direction: column;

} */

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
  background-color: #012f6d;
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

#left-entries :first-child{
  border-radius:10px;
  border: none;
  height: 40px;
  text-align: center;
 

}

 input[type="checkbox"] {
  margin-right: 5px;

}

</style>
