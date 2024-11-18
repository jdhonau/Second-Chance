<template >
  <!-- <HeaderNav /> -->
  <div>
  <!-- Add pet-related content here -->
  <!-- Animal Cards Section (router-view) -->

  </div>
  <div>
    <section class="animals-section">
      <div class="container">
        <div class="title-and-button">
          <h2 class="section-title">Meet Our Furry Friends</h2>
          <button 
            v-if="userRole === 'ROLE_USER' || userRole === 'ROLE_ADMIN'" 
            class="btn btn-outline-secondary bi bi-share"
          >
            <router-link
              v-bind:to="{ name: 'PetDetailsView' }"
              style="text-decoration: none; color: inherit; padding-top: 6px; padding-bottom: 6px; font-size: 12px;"
            >
              Create New Pet
            </router-link>
          </button>
        </div>
        <div id="animal-cards" class="animal-cards-grid">
          <!-- Animal cards will be dynamically added here by JS -->
          <AnimalCard v-for="animal in animals" :key="animal.animalid" :animal="animal" />
  
        </div>
      </div>
    </section>
  </div>
  
</template>

<script>
import AnimalCard from "../components/AnimalCard.vue";
// import HeaderNav from "../components/HeaderNav.vue";

export default {
  name: 'HomeView',
  components: {
    AnimalCard,
    // HeaderNav
  },
  data() {
    return {
      modalShow: false,
      animals: []
    };
  },
  computed: {
    userRole() {
      return this.$store.state.auth.userRole || 'guest';
    }
  },
  methods: {
    async fetchAnimals() {
      try {
        // const response = await PetService.getAllPets();
        const response = await fetch('http://localhost:9000/api/pets');
        this.animals = await response.json();
        console.log(response);
      } catch (error) {
        console.error('Error fetching animal data:', error);
      }
    }
  },
  mounted() {
    this.fetchAnimals();
  }
};
</script>

<style scoped>
h1 {
  text-align: center;
  margin-top: 5%;
  
}

.container {
  width: 100vw;
  padding: 0 1rem;
}

.animals-section {
  margin: 2rem 0;
}

.section-title {
  text-align: center;
  margin-bottom: 1.5rem;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  width: 80%;
  max-width: 500px;
}
.btn-outline-secondary {
  background-color: #f4f4f9;
  font-size: 12px;
  height: 32px;
  color: #222;
  text-decoration: none;
  position: absolute;
  bottom: 10px; 
  right: 10px; 
  margin-left: auto; 
  padding-top: 6px;
  padding-bottom: 6px;

}

.title-and-button {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  height: 200px; /* Adjust height as needed */
  position: relative; /* Position relative for absolute positioning of button */
}

.section-title {
  margin: 0;
  text-align: center;
}
/* @media (max-width: 425px) {
  .animal-cards-grid {
    grid-template-columns: 1fr;
  }
} */
</style>
