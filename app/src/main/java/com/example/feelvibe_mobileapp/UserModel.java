package com.example.feelvibe_mobileapp;

public class UserModel {
    private String userId;
    private String userName;
    private String userEmail;
    private String userBio;
    private String userProfile;
    private String userCover;
    private String userNumber;

    public UserModel(){

    }

    public UserModel(String userId, String userName, String userEmail, String userBio, String userProfile, String userCover, String userNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBio = userBio;
        this.userProfile = userProfile;
        this.userCover = userCover;
        this.userNumber = userNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserCover() {
        return userCover;
    }

    public void setUserCover(String userCover) {
        this.userCover = userCover;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}
