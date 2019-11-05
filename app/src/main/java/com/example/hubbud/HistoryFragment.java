package com.example.hubbud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubbud.Adapter.PostListingAdapter;
import com.example.hubbud.Database.FirebaseDatabaseHelper;
import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.Model.PostModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    RecyclerView rv_post;
    LinearLayoutManager linearLayoutManager;
    PostListingAdapter dataAdapter;
    public ArrayList<PostModel> requestData;
    SwipeRefreshLayout srl_posts;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                startActivity(new Intent(getActivity(), PostActivity.class));

            }
        });

        init(view);

        // Inflate the layout for this fragment
        return view;
    }

    @SuppressLint("NewApi")
    private void init(View view) {

        rv_post = (RecyclerView) view.findViewById(R.id.rv_post);

        srl_posts = view.findViewById(R.id.srl_posts);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_post.setLayoutManager(linearLayoutManager);
        requestData = new ArrayList<>();

        /*requestData.add(new PostModel("0", "SPOTRS/Football", "Category", "SubCategory", "Want to play football in cavalry ground at 5 pm", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));
        requestData.add(new PostModel("0", "Title", "Category", "SubCategory", "Description", "01:23AM", "21/04/2019", "0"));*/

        dataAdapter = new PostListingAdapter(getActivity(), requestData, "self");
        rv_post.setAdapter(dataAdapter);

        getDataFromFirebase();

        swipeListener();

    }

    private void swipeListener() {

        srl_posts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getDataFromFirebase();

            }
        });

    }

    private void getDataFromFirebase() {

        srl_posts.setRefreshing(true);
        //Toast.makeText(getContext(), "Loading...!", Toast.LENGTH_SHORT).show();
        new FirebaseDatabaseHelper().readCurrentUserPosts(Prefs.getUserIDFromPref(getContext()),new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataisLoaded(List<PostModel> postsArray, List<String> keys) {

                //Toast.makeText(getContext(), "Loaded...!", Toast.LENGTH_SHORT).show();
                requestData.clear();
                srl_posts.setRefreshing(false);
                if (!postsArray.isEmpty()) {
                    requestData = (ArrayList<PostModel>) postsArray;
                    Log.e("fetch", "requestData: " + requestData.get(0).getPostDescription());
                    srl_posts.setRefreshing(false);
                    dataAdapter.notifyDataSetChanged();
                    dataAdapter = new PostListingAdapter(getActivity(), requestData, "self");
                    rv_post.setAdapter(dataAdapter);
                } else {

                    //Toast.makeText(getContext(), "No Data!", Toast.LENGTH_LONG).show();

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
