package com.example.hubbud.Model;

public class PostModel {

    String postID;
    String postTitle;
    String postCategory;
    String postSubCategory;
    String postDescription;
    String postTime;
    String postDate;
    String userId;

    public PostModel() {
    }

    public PostModel(String postID, String postTitle, String postCategory, String postSubCategory, String postDescription, String postTime, String postDate, String userId) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postSubCategory = postSubCategory;
        this.postDescription = postDescription;
        this.postTime = postTime;
        this.postDate = postDate;
        this.userId = userId;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostSubCategory() {
        return postSubCategory;
    }

    public void setPostSubCategory(String postSubCategory) {
        this.postSubCategory = postSubCategory;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}