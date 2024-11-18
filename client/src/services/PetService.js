import axios from 'axios';

const baseUrl = 'http://localhost:9000/api';

export default {
  getAllPets() {
    return axios.get(`${baseUrl}/pets`);
  },
  getPetById(id) {
    return axios.get(`${baseUrl}/pets/${id}`);
  },
  createPet(petData) {
    return axios.post(`${baseUrl}/pets`, petData);
  },
  updatePet(id, petData) {
    return axios.put(`${baseUrl}/pets/${id}`, petData);
  },
  deletePet(id) {
    return axios.delete(`${baseUrl}/pets/${id}`);
  }
};

