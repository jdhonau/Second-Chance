package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    Pet getPetById(int petId);

    Pet createPet(Pet pet);

    List<Pet>getAllActivePets();

    Pet updatePet(Pet pet);

    Pet deletePet(int animalid);
}
