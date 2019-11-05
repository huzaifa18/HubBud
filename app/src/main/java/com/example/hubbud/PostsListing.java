package com.example.hubbud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hubbud.Adapter.PostListingAdapter;
import com.example.hubbud.Database.FirebaseDatabaseHelper;
import com.example.hubbud.Model.PostModel;
import com.example.hubbud.Model.Users;

import java.util.ArrayList;
import java.util.List;

public class PostsListing extends AppCompatActivity {

    RecyclerView rv_post;
    LinearLayoutManager linearLayoutManager;
    PostListingAdapter dataAdapter;
    public ArrayList<PostModel> requestData;
    public ArrayList<PostModel> postsArray1;
    SwipeRefreshLayout swipe_container;
    String category = "";
    String subCategory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_listing);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        category = getIntent().getStringExtra("category");
        subCategory = getIntent().getStringExtra("sub_category");

        Log.e("Tag", "Category: " + category);
        Log.e("Tag", "subCategory: " + subCategory);

        init();

    }

    private void init() {

        rv_post = (RecyclerView) findViewById(R.id.rv_post);
        swipe_container = findViewById(R.id.swipe_container);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL, false);
        rv_post.setLayoutManager(linearLayoutManager);
        requestData = new ArrayList<>();
        postsArray1 = new ArrayList<>();
        /*requestData.add(new PostModel("0","SPOTRS/Football","Category","SubCategory","Want to play football in cavalry ground at 5 pm","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));
        requestData.add(new PostModel("0","Title","Category","SubCategory","Description","01:23AM","21/04/2019","0"));*/
        dataAdapter = new PostListingAdapter(PostsListing.this, requestData,"sender");
        rv_post.setAdapter(dataAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(PostsListing.this,PostActivity.class));
            }
        });

        //getDataFromFirebase();
        getAllPosts();

        refreshListener();

    }

    private void refreshListener() {

        swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData.clear();
                postsArray1.clear();
                //getDataFromFirebase();
                getAllPosts();
            }
        });

    }

    private void getDataFromFirebase() {

        Toast.makeText(PostsListing.this, "Loading...!", Toast.LENGTH_SHORT).show();
        new FirebaseDatabaseHelper().readPosts(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataisLoaded(List<PostModel> postsArray, List<String> keys) {

                Toast.makeText(PostsListing.this, "Loaded...!", Toast.LENGTH_SHORT).show();
                requestData.clear();
                swipe_container.setRefreshing(false);
                if (!postsArray.isEmpty()) {
                    requestData = (ArrayList<PostModel>) postsArray;
                    requestData.add(postsArray.get(0));
                    Log.e("fetch", "requestData: " + requestData.get(0).getPostDescription());
                    swipe_container.setRefreshing(false);
                    dataAdapter.notifyDataSetChanged();
                    dataAdapter = new PostListingAdapter(PostsListing.this, requestData, "sender");
                    rv_post.setAdapter(dataAdapter);
                } else {

                    Toast.makeText(PostsListing.this, "No Data!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void dataisInserted() {

            }

            @Override
            public void dataisUpdated() {

            }

            @Override
            public void dataisDeleted() {

            }
        });

    }

    private void getAllPosts() {

        swipe_container.setRefreshing(true);

        new FirebaseDatabaseHelper().getAllPosts(new FirebaseDatabaseHelper.UserStatus() {
            @Override
            public void userisLoaded(final List<Users> usersArray, List<String> keys) {

                for (int i = 0 ; i < usersArray.size(); i++) {

                    final int finalI = i;
                    new FirebaseDatabaseHelper().readCurrentUserPosts(usersArray.get(i).getUserid(), new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void dataisLoaded(List<PostModel> postsArray, List<String> keys) {

                            //Log.e("posts", "Post Title: " + postsArray.size());

                            for (int i = 0; i < postsArray.size(); i++) {

                                postsArray1.add(postsArray.get(i));

                            }

                            Log.e("abc", "postsArray1: " + postsArray1.size());
                            if (usersArray.size()-1 == finalI){
                                swipe_container.setRefreshing(false);

                                for (int j = 0; j < postsArray1.size(); j++) {
                                    Log.e("abc", "getPostCategory: " + postsArray1.get(j).getPostCategory());
                                    Log.e("abc", "getPostSubCategory: " + postsArray1.get(j).getPostSubCategory());
                                    Log.e("abc", "category: " + category);
                                    Log.e("abc", "subCategory: " + subCategory);

                                    Log.e("Tag", "Cat: " + category);
                                    Log.e("Tag", "Sub: " + subCategory);
                                    if (subCategory.equals("Not Available!") || subCategory.equals("no")){

                                        if (postsArray1.get(j).getPostCategory().equals(category)) {

                                            Log.e("Tag", "Okay!");
                                            requestData.add(postsArray1.get(j));

                                        }

                                    } else {

                                        if (postsArray1.get(j).getPostCategory().equals(category) && postsArray1.get(j).getPostSubCategory().equals(subCategory)) {

                                            Log.e("Tag", "Not Okay!");
                                            requestData.add(postsArray1.get(j));

                                        }

                                    }

                                }

                                Log.e("abc", "requestData: " + requestData.size());
                                dataAdapter.notifyDataSetChanged();
                                dataAdapter = new PostListingAdapter(PostsListing.this, requestData, "sender");
                                rv_post.setAdapter(dataAdapter);
                            }

                        }

                        @Override
                        public void dataisInserted() {

                        }

                        @Override
                        public void dataisUpdated() {

                        }

                        @Override
                        public void dataisDeleted() {

                        }
                    });

                }

            }

            @Override
            public void userisInserted() {

            }

            @Override
            public void userisUpdated() {

            }

            @Override
            public void userisDeleted() {

            }
        });

    }

}