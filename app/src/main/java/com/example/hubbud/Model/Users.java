package com.example.hubbud.Model;

public class Users {

    String profileimg;
    String name;
    String about;
    String location;
    String username;
    String email;
    String gender;
    String dob;
    String password;
    String phone;
    String userid;
    PostModel posts;

    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public Users(String profileimg, String name, String about, String location, String username, String email, String gender, String dob, String password, String phone, String userid) {
        this.profileimg = profileimg;
        this.name = name;
        this.about = about;
        this.location = location;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.password = password;
        this.phone = phone;
        this.userid = userid;
    }

    public Users(String profileimg, String name, String username, String email, String gender, String dob, String password, String userid, PostModel posts) {
        this.profileimg = profileimg;
        this.name = name;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.password = password;
        this.userid = userid;
        this.posts = posts;
    }

    public PostModel getPosts() {
        return posts;
    }

    public void setPosts(PostModel posts) {
        this.posts = posts;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}