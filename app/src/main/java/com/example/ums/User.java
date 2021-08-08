package com.example.ums;

class User {

    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String mobile;
    private String gender;

    public User(String fullName, String userName, String email, String password, String mobile, String gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
