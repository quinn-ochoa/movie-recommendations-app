<template>
    <div>
      
      <review-form v-bind:card="card" />
    </div>
</template>

<script>
    import ReviewForm from '../components/ReviewForm.vue';
    export default {
        components:{
            ReviewForm
        },
        // data(){
        //     return{}
        // }
        
    }
    

</script>