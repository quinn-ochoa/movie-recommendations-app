<template>
    <div>
      <h1>Add New Review</h1>
      <review-form v-bind:card="card" />
    </div>
</template>

<script>
    import ReviewForm from '../components/ReviewForm.vue';
    export default {
        components:{
            ReviewForm
        },
        data(){
            return{}
        }
    }

</script>