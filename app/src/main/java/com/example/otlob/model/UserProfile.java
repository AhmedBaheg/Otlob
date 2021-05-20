package com.example.otlob.model;

public class UserProfile {

    private String fullName, email, phoneNumber, imgUrl;

    public UserProfile(String fullName, String email, String phoneNumber, String imgUrl) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imgUrl = imgUrl;
    }

    public UserProfile(String fullName, String email, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserProfile() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
