<template>
  <div class="card" style="width: 18rem;">
    <img :src="pet.image" class="card-img-top" alt="Pet Image">
    <div class="card-body">
      <h5 class="card-title">{{ pet.name }}</h5>
      <p class="card-text">{{ pet.description }}</p>
      <a href="#" class="btn btn-primary">Adopt</a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PetCard',
  props: {
    pet: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
/* Add any additional styles here */
</style>
