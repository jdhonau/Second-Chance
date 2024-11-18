package com.techelevator.model;
import java.time.LocalDate;

public class Pet {

    private int animalid;
    private String PetName;
    private String breed;
    private String color;
    private int age;
    private String Photo;
    private String Species;
    private boolean active;
    private int owner_id;
    private LocalDate adoption_date;

    public Pet(int animalid, String PetName, String breed, String color, int age, String photo, String species, boolean active, int owner_id, LocalDate adoption_date) {
        this.animalid = animalid;
        this.PetName = PetName;
        this.breed = breed;
        this.color = color;
        this.age = age;
        this.Photo = photo;
        this.Species = species;
        this.active = active;
        this.owner_id = owner_id;
        this.adoption_date = adoption_date;
    }

    public Pet() { }

    public Pet(String PetName, String breed, String color, int age, String photo, String species, boolean active, int owner_id, LocalDate adoption_date) {
        this(0, PetName, breed, color, age, photo, species, active, owner_id, adoption_date);
    }

    // Getters and setters

    public int getAnimalid() {
        return animalid;
    }

    public void setAnimalid(int animalid) {
        this.animalid = animalid;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String PetName) {
        this.PetName = PetName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public LocalDate getAdoption_date() {
        return adoption_date;
    }

    public void setAdoption_date(LocalDate adoption_date) {
        this.adoption_date = adoption_date;
    }
}
