package com.example.hubbud.Model;

public class MessageModel {

    String message;
    String sender_id;
    String reciever_id;
    String post_id;
    String time;

    public MessageModel() {
    }

    public MessageModel(String message, String sender_id, String reciever_id, String post_id, String time) {
        this.message = message;
        this.sender_id = sender_id;
        this.reciever_id = reciever_id;
        this.post_id = post_id;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReciever_id() {
        return reciever_id;
    }

    public void setReciever_id(String reciever_id) {
        this.reciever_id = reciever_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
