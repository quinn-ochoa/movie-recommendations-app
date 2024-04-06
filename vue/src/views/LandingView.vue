<template>

  <div class="container">
    <div class="image">
      <img src="../../src/assets/movie_collage.jpg" alt="Movie Poster Collage"/>
    </div>
  
    <div class="home">
        <div class="content">
            <h1>LOGO GOES HERE</h1>
            <h1>WeWatchWhat</h1>
            <p>Discover Your Next Favorite Film: Your Personalized Movie Journey Begins Here!</p>
          
            <div class="search">
              <input type="text" id="search" required autofocus />
            </div>
            <br/>
            <button type="sign-in">Sign in</button>&nbsp;
            <button type="create-account">Create Account</button>
        </div>
    </div>
  </div>
</template>
  
  <script>
  export default {
  };
  </script>
  
  <style scoped>
  .container{
    background-color: aqua;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas: 
      "image home";
    height: 100vh;
    margin: 0px -10px;
    /* justify-items: center; */
  }
  .image{
    grid-area: image;
    background-color: #FECE00;
  }

  .image img{
      height: 100%;
      width: 100%;
      object-fit: fill;
  } 

  .home{
    grid-area: home;
    /* padding: 20px; */
    background-color: #FECE00;
    position: relative;
  }
  .content{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 10px;
    text-align: center;
  }

  
  </style>