package com.example.hubbud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hubbud.Adapter.SpinnerListingAdapter;
import com.example.hubbud.Database.FirebaseDatabaseHelper;
import com.example.hubbud.Helper.Arrays;
import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.Model.DataIds;
import com.example.hubbud.Model.PostModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    Spinner sp_category, sp_subcategory;
    ArrayList<DataIds> categoryList;
    ArrayList<DataIds> subCategoryList;
    FloatingActionButton fab;
    EditText et_post;
    EditText et_title;

    PostModel postData;

    String subCat = "";

    int sub_pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        fab = findViewById(R.id.fab);

        init();
        clickListeners();

    }

    private void init() {

        sp_category = (Spinner) findViewById(R.id.spinner_category);
        sp_subcategory = (Spinner) findViewById(R.id.spinner_sub_category);
        et_post = findViewById(R.id.et_post);
        et_title = findViewById(R.id.et_title);

        categoryList = new ArrayList<DataIds>() {{
            add(new DataIds("Select Category", "0"));
            add(new DataIds("Art & Crafts", "1"));
            add(new DataIds("Sports", "2"));
            add(new DataIds("Tourism", "3"));
            add(new DataIds("Vendor", "4"));
            add(new DataIds("Organize", "5"));
        }};

        subCategoryList = new ArrayList<DataIds>() {{
            add(new DataIds("Select Sub-Category", "0"));
            add(new DataIds("Calligraphy/Embroidery", "1"));
            add(new DataIds("Cooking", "2"));
            add(new DataIds("Dancing", "3"));
            add(new DataIds("Vendor", "4"));
            add(new DataIds("Organize", "5"));
        }};

        SpinnerListingAdapter category = new SpinnerListingAdapter(PostActivity.this, categoryList);
        sp_category.setAdapter(category);
        category.notifyDataSetChanged();

        SpinnerListingAdapter subcategory = new SpinnerListingAdapter(PostActivity.this, subCategoryList);
        sp_subcategory.setAdapter(subcategory);
        subcategory.notifyDataSetChanged();

        scrollSelectListeners();

    }

    private void scrollSelectListeners() {

        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sub_pos = i;

                if (i == 1){

                    sp_subcategory.setVisibility(View.VISIBLE);
                    subCat = Arrays.ArtsCrafts.get(sp_subcategory.getSelectedItemPosition()).getName();
                    SpinnerListingAdapter subcategory = new SpinnerListingAdapter(PostActivity.this, Arrays.ArtsCrafts);
                    sp_subcategory.setAdapter(subcategory);
                    subcategory.notifyDataSetChanged();

                } else if (i == 2){
                    sp_subcategory.setVisibility(View.VISIBLE);
                    subCat = Arrays.Sports.get(sp_subcategory.getSelectedItemPosition()).getName();
                    SpinnerListingAdapter subcategory = new SpinnerListingAdapter(PostActivity.this, Arrays.Sports);
                    sp_subcategory.setAdapter(subcategory);
                    subcategory.notifyDataSetChanged();

                } else if (i == 3) {
                    sp_subcategory.setVisibility(View.VISIBLE);
                    subCat = Arrays.Tourism.get(sp_subcategory.getSelectedItemPosition()).getName();
                    SpinnerListingAdapter subcategory = new SpinnerListingAdapter(PostActivity.this, Arrays.Tourism);
                    sp_subcategory.setAdapter(subcategory);
                    subcategory.notifyDataSetChanged();

                } else {

                    sp_subcategory.setVisibility(View.GONE);
                    subCat = "Not Available!";

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sp_subcategory.setVisibility(View.VISIBLE);

                if (sub_pos == 1){

                    subCat = Arrays.ArtsCrafts.get(i).getName();

                } else if (sub_pos == 2){

                    subCat = Arrays.Sports.get(i).getName();

                } else if (sub_pos == 3) {

                    subCat = Arrays.Tourism.get(i).getName();

                } else {

                    sp_subcategory.setVisibility(View.GONE);
                    subCat = "Not Available!";

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void clickListeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding Post", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if (et_post.getText().toString().equals("")) {

                    et_post.setError("This Field can not be empty!");

                } else {

                    createPost();

                }

            }
        });

    }

    private void createPost() {

        if (sub_pos > 3 || sub_pos == -1){

            subCat = "Not Available!";

        }

        Toast.makeText(PostActivity.this, "Adding...!", Toast.LENGTH_SHORT).show();
        postData = new PostModel("0", et_title.getText().toString(), categoryList.get(sp_category.getSelectedItemPosition()).getName(), subCat, et_post.getText().toString(), Calendar.getInstance().getTime().toString(), Calendar.getInstance().getTime().toString(), Prefs.getUserIDFromPref(PostActivity.this));

        new FirebaseDatabaseHelper().addPost(postData, new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataisLoaded(List<PostModel> postsArray, List<String> keys) {

            }

            @Override
            public void dataisInserted() {

                Snackbar.make(fab, "Post Added!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(PostActivity.this, "Post Added!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(PostActivity.this, MainActivity.class));
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