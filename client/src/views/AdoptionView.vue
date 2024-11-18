<template>
    <div>
        <!-- <HeaderNav /> -->
        <div class="form-container">
            <AdoptionForm :petName="petName" />
        </div>
    </div>
</template>

<script>
import AdoptionForm from '../components/AdoptionForm.vue'   
// import HeaderNav from '../components/HeaderNav.vue'

export default {
    name: 'AdoptionView',
    components: {
        AdoptionForm,
        // HeaderNav
          },
    data() {
        return {
            petName: this.$route.params.name
        }
    }
}
</script>

<style>


</style>