<template>
    <form v-on:submit.prevent="submitForm" class="cardForm">
      <div class="form-group">
        <label for="title">Title:</label>
        <input id="title" type="text" class="form-control" v-model="editCard.title" autocomplete="off" />
      </div>
  
      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" class="form-control" v-model="editCard.description"></textarea>
      </div>

      <button class="btn btn-submit">Submit</button>
      <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
    </form>
</template>

<script>

export default {
    props: {
        card: {
        type: Object,
        required: true
        }
    },
    methods: {
        submitForm(){

        },
        cancelForm() {
        // Go back to previous page
        this.$router.back();
        },
    }
    
}

</script>

<style scoped>
    .cardForm {
    padding: 10px;
    margin-bottom: 10px;
    }

    .form-group {
    margin-bottom: 10px;
    margin-top: 10px;
    }

    label {
    display: inline-block;
    margin-bottom: 0.5rem;
    }

    .form-control {
    display: block;
    width: 80%;
    height: 30px;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
    }

    textarea.form-control {
    height: 75px;
    font-family: Arial, Helvetica, sans-serif;
    }

    select.form-control {
    width: 20%;
    display: inline-block;
    margin: 10px 20px 10px 10px;
    }

</style>