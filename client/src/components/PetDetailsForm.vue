<template>
  <div class="container">
    <h1>Pet Details Form</h1>
    <form>
      <div>
        <label for="name">Name</label>
        <input type="text" id="name" v-model="pet.petName" required />
      </div>
      <div>
        <label for="species">Species</label>
        <input type="text" id="species" v-model="pet.species" required />
      </div>
      <div>
        <label for="breed">Breed</label>
        <input type="text" id="breed" v-model="pet.breed" required />
      </div>
      <div>
        <label for="age">Age</label>
        <input type="number" id="age" v-model="pet.age" required />
      </div>
      <div>
        <label for="color">Color</label>
        <input type="text" id="color" v-model="pet.color" required />
      </div>
      <div>
        <label for="photo">Photo</label>
        <input type="text" id="photo" v-model="pet.photo" />
      </div>
      <!-- <div>
                <label for="bio">Bio</label>
                <input type="text" id="bio" v-model="pet.bio" required>
            </div>
            <div>
                <label for="notes">Notes</label>
                <input type="text" id="notes" v-model="pet.notes" required>
            </div> -->
      <button type="submit" @click.prevent="submitPetDetails">Submit</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import PetService from '../services/PetService.js';

const uploadedFiles = ref([]);

// var date = new Date();
// var day = date.getDate();
// var month = date.getMonth();
// var year = date.getFullYear();
// let currentDate = year + "/" + month + "/" + day;
// console.log(currentDate);

export default {
  name: "PetDetailsForm",
  props: ["id"],
  data() {
    return {
      pet: {
        petName: "",
        species: "",
        breed: "",
        age: 0,
        color: "",
        photo: "",
        // bio: "",
        // notes: "",
        ownerID: 0,
        active: true,
        // activeDate: currentDate,
      },
    };
  },
  methods: {
    getPetDetails() {
      PetService.getPetById(this.id).then((response) => {
        this.pet = response.data;
        console.log(this.pet);
      });
    },
    submitPetDetails() {
      if (this.id) {
        PetService.updatePet(this.id, this.pet)
          .then((response) => {
            console.log("Pet details updated successfully, ", response);
            this.$router.push({ name: "home" });
          })
          .catch((error) => {
            console.error("Error updating pet details: ", error);
          });
      } else {
        PetService.createPet(this.pet)
          .then((response) => {
            console.log("Pet details added successfully, ", response);
            this.$router.push({ name: "home" });
          })
          .catch((error) => {
            console.error("Error adding pet details: ", error);
          });
      }
    },
    // handleFilesUploaded(imgUrl) {
    //   console.log(imgUrl);
    //   this.pet.photo = imgUrl;
    // },

  },
  mounted() {
    if (this.id) {
      this.getPetDetails();
    } else {
      console.log("No ID provided");
    }
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;
}
/* form div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
} */
.container {
  justify-self: center;
  margin-top: 10px;
  height: 82vh;
  width: 90vw;
}

</style>
