<template>
  <div>
    <div class="card">
      <img :src="animal.photo" class="card-img-top" :alt="animal.petName">
      <div class="card-body">
        <h5 class="card-title">I'm {{ animal.petName }}!</h5>
        <p class="card-text">Let me be your new {{ animal.species }}</p>
        <p class="card-text">I am {{ animal.age }} years old</p>
        <p class="card-text">Breed: {{ animal.breed }}</p>
        <p class="card-text">Color: {{ animal.color }}</p>
        <div class="d-flex justify-content-between align-items-end">
          <button class="btn btn-outline-primary" @click="adoptMe(animal.petName)">Adopt Me!</button>
          <button class="btn btn-outline-danger" @click="openMeetMeLink">Meet Me!</button>
          <button >
            <router-link v-bind:to="{ name: 'ContactView' }">Learn More</router-link>
          </button>
        </div>
        <br>
        <div>
          <button 
            v-if="userRole === 'ROLE_USER' || userRole === 'ROLE_ADMIN'" 
            class="btn btn-outline-secondary bi bi-share"
          >
            <router-link class="btn-outline-secondary"
              v-bind:to="{ name: 'PetDetailsView', params: { id: animal.animalid } }">Edit</router-link>
          </button>
          <button 
            v-if="userRole === 'ROLE_ADMIN'" 
            class="btn btn-outline-secondary bi bi-share" 
            @click="deletePet(animal.animalid)"
          >
            Delete
          </button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import PetService from "../services/PetService";


export default {
  name: 'AnimalCard',
  props: {
    animal: {
      type: Object,
      required: true
    }
  },

  computed: {
    userRole() {
      return this.$store.state.auth.userRole || 'guest';
    }
  },

  methods: {
    adoptMe(animalName) {
      // Implement the adoptMe logic here
      console.log(`Adopt animal with name: ${animalName}`);
      this.$router.push({ name: 'AdoptionView', params: { name: animalName } });
    },
    toggleFavorite() {
      // Implement the toggleFavorite logic here
      console.log('Toggle favorite');
    },
    openMeetMeLink() {
      window.open('https://calendly.com/secondchanceshelter', '_blank');
    },
    showContactForm() {
      this.$router.push('/contact');
    },
    deletePet(animalId) {
      PetService.deletePet(animalId).then((response) => {
        console.log("Pet details deleted successfully, ", response);
        window.location.reload(); // Refresh the page
      });
      console.log(`Delete animal with ID: ${animalId}`);
    }
  }
}
</script>

<style scoped>
 .card {
  display: grid;
  column-width: 100%;
  grid-template-columns: 1fr 1fr;
  transition: transform 0.2s; 
 }

.card-img-top {
  width: 200px;
  margin: 8%;
}

.card-title {
  margin-top: 5%;
  font-size: 25px;
}
main.dark-mode .card-title {
  color: #bfbfbf;
} 
main.dark-mode .card-text {
  color: #bfbfbf;
}
main.dark-mode .card {
  background-color: #333;
  border: 1px solid #bfbfbf;
}
main.dark-mode .card:hover {
  background-color: #66023c;
}
/* .card-text:last-of-type {
    margin-bottom: 15%;
    
} */
.card button {
  background-color: #f4f4f9;
  padding-right: 10px;
  padding-left: 10px;
  font-size: 12px;
}

.card:hover {
  transform: scale(1.05);
  background-color: #66023c;
  color: #ccc;
}


/* .card button:hover {
  background-color: #fff;
  color: #222;
} */

a.btn-outline-secondary {
  background-color: #f4f4f9;
  font-size: 12px;
  height: 32px;
  padding-top: 6px;
  padding-bottom: 6px;
  padding-left: 10px;
  padding-right: 10px;
  text-decoration: none;
  color: #222;
  overflow: hidden;
  text-justify: center;
  text-align: center;
  padding-block: 1px;
  padding-inline: 6px;
  vertical-align: middle;
}
</style>
