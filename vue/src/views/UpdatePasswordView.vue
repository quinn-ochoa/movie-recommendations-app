<template>
    <update-password-form />
</template>

<script>
import UpdatePasswordForm from '../components/UpdatePasswordForm.vue';

export default {
    components: {
        UpdatePasswordForm
    },

    data() {
        return {
            user: {}
        };
    }
}


</script>

