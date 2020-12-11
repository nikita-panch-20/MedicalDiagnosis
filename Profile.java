package com.gs.medicaldiagnosisexpertsystem;



public class Profile {

    String imageLink;
    String userId;

    public Profile() {
    }

    public Profile(String imageLink, String userId) {
        this.imageLink = imageLink;
        this.userId = userId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
