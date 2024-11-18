<template>
  <div>
    <!-- <HeaderNav /> -->
    <ContactForm />
  </div>
</template>

<script>
import ContactForm from "../components/ContactForm.vue";
// import HeaderNav from "../components/HeaderNav.vue";


export default {
  name: 'ContactView',
  components: {
    ContactForm,
    // HeaderNav
  },
  data() {
    return {
      // Define data properties related to contact
    };
  },
  methods: {
    // Define methods to handle contact-related actions
  }
};
</script>

<style scoped>


/* Add styles specific to the ContactView */
</style>