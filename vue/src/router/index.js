import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import LandingView from '../views/LandingView.vue';
import UpdateGenresView from '../views/UpdateGenresView.vue';
import EditGenresView from '../views/EditGenresView.vue';
import MovieDetailView from '../views/MovieDetailView.vue';
import UpdatePasswordView from '../views/UpdatePasswordView.vue';
import UpdatePasswordForm from '../components/UpdatePasswordForm.vue';
import AddReviewView from '../views/AddReviewView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    },
  },
  {
    path: "/landing",
    name: "landing",
    component: LandingView,
    meta: {
      requiresAuth: false
    },
  },
  {
    path: '/update',
    name: 'UpdateGenresView',
    component: UpdateGenresView
  },

  {
    path: '/create',
    name: 'EditGenresView',
    component: EditGenresView
  },

  {
    path: '/movie/:movieId',
    name: 'MovieDetailView',
    component: MovieDetailView
  },

  {
    path: '/forgotPassword/',
    // name: 'UpdatePasswordView',
    component: UpdatePasswordForm 
  },
  
  //ADD REVIEW AND GET REVIEW
  {
    path: '/movie/:movieId/review/create',
    name: 'AddReviewView',
    component: AddReviewView
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "landing"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
