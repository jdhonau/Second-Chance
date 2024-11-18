<template>
  <!-- <HeaderNav /> -->
  <div>
    <PetDetailsForm :id="id" />
  </div>
</template>

<script>
// import HeaderNav from "../components/HeaderNav.vue";
import PetDetailsForm from "../components/PetDetailsForm.vue";

export default {
  name: "PetDetailsView",
  components: {
    // HeaderNav,
    PetDetailsForm,
  },
  props: ["id"],
  data() {
    return {
      // Define data properties related to pet details
    };
  },
  methods: {
    // Define methods to handle pet details-related actions
  },
};
</script>

<style scoped>

</style>
