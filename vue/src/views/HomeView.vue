<template>
    <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  </head>
  
  <div class="home">
    <header>
      <img alt="Glasses logo" src="../assets/logo.png"/>
      <div>Search Bar</div>
      <h1><i class="fa-solid fa-circle-user"></i>&nbsp; User profile</h1>

    </header>
    <body>
      <div>
        <h2>Based on your favorite genres</h2>
        <button class="btn-add" v-on:click="$router.push({ name: 'AddGenresView' })">Add Genres</button>
        <div>movies with favorite genres goes here</div>
      </div>
      <div>
        <h2>Suggested movies for you</h2>
      </div>
      <!-- <p>You must be authenticated to see this</p> -->
    </body>

    <footer>
 <div><i class="fa-brands fa-facebook social-icon" ></i><i class="fa-brands fa-instagram social-icon"></i></div>

    </footer>

    
  </div>
</template>

<script>
export default {
};
</script>

<style>
img {
  margin-left: 10px;
  height: 100px;
}

header {
  background-color: #FECE00;
  display: flex;
  justify-content: space-between;

}

footer {
  background-color: #FFF3BF;
  text-align: center;
  height: 100px;
}

h1 {
  font-size: medium;
  margin-right: 10px;
}

h2 {
  font-size: medium;
  height: 100px;
}

.social-icon {
  font-size: 20px;
  height: 20px;
  margin-top: 40px;
  margin-right: 20px;
}






</style>
