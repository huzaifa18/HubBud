package com.example.hubbud.Database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.hubbud.Helper.MyApp;
import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.Model.MessageModel;
import com.example.hubbud.Model.PostModel;
import com.example.hubbud.Model.User;
import com.example.hubbud.Model.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    FirebaseDatabase database;
    DatabaseReference reference;
    final String DB = "HubBud";
    List<PostModel> postsArray = new ArrayList<>();
    List<Users> usersArray = new ArrayList<>();
    List<MessageModel> messagesArray = new ArrayList<>();
    String key = "";

    public interface DataStatus{
        void dataisLoaded(List<PostModel> postsArray, List<String> keys);
        void dataisInserted();
        void dataisUpdated();
        void dataisDeleted();

    }

    public interface UserStatus{
        void userisLoaded(List<Users> usersArray, List<String> keys);
        void userisInserted();
        void userisUpdated();
        void userisDeleted();

    }

    public interface MessageStatus{
        void userisLoaded(List<MessageModel> messagesArray, List<String> keys);
        void userisInserted();
        void userisUpdated();
        void userisDeleted();

    }

    public FirebaseDatabaseHelper() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(DB);
    }

    public void getAllPosts(final UserStatus user) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersArray.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());

                    Users postsObj = snapshot.getValue(Users.class);

                    Log.e("read", "user: " + postsObj.getUserid());

                    usersArray.add(postsObj);

                }

                user.userisLoaded(usersArray,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("Err", "Database Error: " + databaseError.getMessage());

            }
        });

    }

    public void getAllMessages(String id, final MessageStatus messageStatus) {

        Log.e("msg", "id: " + id);

        reference.child(id).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messagesArray.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());

                    MessageModel postsObj = snapshot.getValue(MessageModel.class);

                    Log.e("msg", "message: " + postsObj.getMessage());

                    messagesArray.add(postsObj);

                }

                messageStatus.userisLoaded(messagesArray,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("Err", "Database Error: " + databaseError.getMessage());

            }
        });

    }

    public void addPost(PostModel post, final DataStatus dataStatus){

        String key = reference.push().getKey();
        Log.e("TAG", "Key: " + post.getUserId());

        Log.e("TAG", "Key: " + getKey(post.getUserId()));
        post.setPostID(key);
        reference.child(post.getUserId()).child("posts").child(key).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                dataStatus.dataisInserted();

            }
        });

    }

    public void sendMessage(MessageModel messageModel, final DataStatus dataStatus){

        String key = reference.push().getKey();
        Log.e("msg", "Reciever_Id: " + messageModel.getReciever_id());
        Log.e("msg", "Sender_Id: " + messageModel.getSender_id());
        Log.e("msg", "Post_id: " + getKey(messageModel.getPost_id()));

        messageModel.setPost_id(key);
        reference.child(messageModel.getSender_id()).child("messages").child(key).setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                dataStatus.dataisInserted();

            }
        });

    }

    public void addUser(Users user, final UserStatus dataStatus){

        String key = reference.push().getKey();
        user.setUserid(key);
        Log.e("TAG", "userid: " + user.getUserid());
        Log.e("TAG", "key: " + key);
        reference.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                dataStatus.userisInserted();

            }
        });

    }

    public void readCurrentUserPosts(final String userId, final DataStatus dataStatus){

        Log.e("TAG","Read Particular User: " + DB);
        //Query query = reference.child(userId).child("user").child("posts").orderByChild("userid").equalTo(userId);
        Query query = reference.child(userId).child("posts");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postsArray.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());

                    PostModel postsObj = snapshot.getValue(PostModel.class);

                    Log.e("TAG","Post Title: " + postsObj.getPostTitle());
                    postsArray.add(postsObj);

                }

                dataStatus.dataisLoaded(postsArray,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void readPosts(final DataStatus dataStatus) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postsArray.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());

                    PostModel postsObj = snapshot.getValue(PostModel.class);

                    Log.e("read", "post: " + postsObj.getPostDescription());
                    postsArray.add(postsObj);

                }

                dataStatus.dataisLoaded(postsArray,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void readAllUsers(final UserStatus userStatus) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersArray.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());

                    Users postsObj = snapshot.getValue(Users.class);

                    Log.e("read", "post: " + postsObj.getEmail());
                    usersArray.add(postsObj);

                }

                userStatus.userisLoaded(usersArray,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public String getKey(final String userId){

        Log.e("TAG","userid: " + userId);
        Query query = reference.orderByChild("userid").equalTo(userId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postsArray.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());
                    key = snapshot.getKey();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return key;

    }

    public void readParticularUser(final String userId, final UserStatus userStatus){

        Log.e("TAG","Read Particular User: " + DB);
        Query query = reference.orderByChild("userid").equalTo(userId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usersArray.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    keys.add(snapshot.getKey());

                    Users postsObj = snapshot.getValue(Users.class);

                    Log.e("TAG","Post Title: " + postsObj.getName());
                    usersArray.add(postsObj);

                }

                userStatus.userisLoaded(usersArray,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}