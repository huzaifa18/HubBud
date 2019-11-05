package com.example.hubbud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hubbud.Adapter.SubCategoryAdapter;
import com.example.hubbud.Helper.Arrays;
import com.example.hubbud.Model.DataIds;

import java.util.ArrayList;

public class SubCategory extends AppCompatActivity {

    String activity;
    String title;
    RecyclerView rv_sub_category;
    SubCategoryAdapter subCategoryAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<DataIds> arr;
    TextView tv_title;

    LinearLayout ll_tourism_btns;
    Button btn_tourism_info,btn_tourism_services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        activity = getIntent().getStringExtra("activity");

        title = getIntent().getStringExtra("title");

        init();

    }

    private void init() {

        tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(title);
        rv_sub_category = (RecyclerView) findViewById(R.id.rv_sub_category);
        linearLayoutManager = new LinearLayoutManager(SubCategory.this);
        rv_sub_category.setLayoutManager(linearLayoutManager);
        arr = new ArrayList<>();

        ll_tourism_btns = findViewById(R.id.ll_tourism_btns);
        btn_tourism_info = findViewById(R.id.btn_tourism_info);
        btn_tourism_services = findViewById(R.id.btn_tourism_services);

        if (title.equals("Art & Crafts")){

            arr = Arrays.ArtsCrafts;

        } else if (title.equals("Sports")) {

            arr = Arrays.Sports;

        } else if (title.equals("Tourism")) {

            arr = Arrays.Tourism;

            ll_tourism_btns.setVisibility(View.VISIBLE);

        }

        subCategoryAdapter = new SubCategoryAdapter(SubCategory.this, arr, activity,title);
        rv_sub_category.setAdapter(subCategoryAdapter);

        clickListeners();

    }

    private void clickListeners() {

        btn_tourism_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SubCategory.this,TourismInfo.class));

            }
        });

        btn_tourism_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SubCategory.this,TourismServices.class));

            }
        });

    }
}