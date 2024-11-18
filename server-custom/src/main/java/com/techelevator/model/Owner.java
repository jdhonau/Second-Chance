package com.techelevator.model;

public class Owner {

    private int ownerId;
    private int userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private boolean volunteer;

    public Owner() { }
    public Owner(int ownerId, String firstName, String lastName, String address, String phone, boolean volunteer) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.volunteer = volunteer;
    }


//Getters and Setters
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVolunteer() {
        return volunteer;
    }

    public void setVolunteer(boolean volunteer) {
        this.volunteer = volunteer;

    }
}
