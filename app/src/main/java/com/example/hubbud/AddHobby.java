package com.example.hubbud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class AddHobby extends AppCompatActivity {

    CardView cv_sports;
    CardView cv_arts;
    CardView cv_tourism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);

        init();

    }

    private void init() {

        cv_sports = (CardView) findViewById(R.id.sports);
        cv_arts = (CardView) findViewById(R.id.arts);
        cv_tourism = (CardView) findViewById(R.id.tourism);

        clickListeners();

    }

    private void clickListeners() {

        cv_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddHobby.this,SubCategory.class);
                intent.putExtra("activity","addhobby");
                intent.putExtra("title","Sports");
                startActivity(intent);

            }
        });

        cv_arts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddHobby.this,SubCategory.class);
                intent.putExtra("activity","addhobby");
                intent.putExtra("title","Art & Crafts");
                startActivity(intent);

            }
        });

        cv_tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(AddHobby.this,MainActivity.class);
                intent.putExtra("hobby","Tourism");
                startActivity(intent);*/

                Intent intent = new Intent(AddHobby.this,SubCategory.class);
                intent.putExtra("activity","addhobby");
                intent.putExtra("title","Tourism");
                startActivity(intent);

            }
        });

    }

}