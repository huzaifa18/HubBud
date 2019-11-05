package com.example.hubbud.Model;

public class RecentChatModel {

    String image_path;
    String name;
    String last_text;
    String date;

    public RecentChatModel(String image_path, String name, String last_text, String date) {
        this.image_path = image_path;
        this.name = name;
        this.last_text = last_text;
        this.date = date;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_text() {
        return last_text;
    }

    public void setLast_text(String last_text) {
        this.last_text = last_text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}