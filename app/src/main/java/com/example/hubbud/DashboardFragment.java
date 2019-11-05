package com.example.hubbud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DashboardFragment extends Fragment {

    ImageView art_craft;
    ImageView sports;
    ImageView tourism;
    ImageView vendor;
    ImageView competition;
    View view;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init(view);

        // Inflate the layout for this fragment
        return view;
    }

    private void init(View view) {

        art_craft = view.findViewById(R.id.art_craft);
        sports = view.findViewById(R.id.sports);
        tourism = view.findViewById(R.id.tourism);
        vendor = view.findViewById(R.id.vendor);
        competition = view.findViewById(R.id.competition);

        clickListeners();

    }

    private void clickListeners() {

        art_craft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),SubCategory.class);
                intent.putExtra("activity","dashboard");
                intent.putExtra("title","Art & Crafts");
                startActivity(intent);

            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),SubCategory.class);
                intent.putExtra("activity","dashboard");
                intent.putExtra("title","Sports");
                startActivity(intent);

            }
        });

        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),SubCategory.class);
                intent.putExtra("activity","dashboard");
                intent.putExtra("title","Tourism");
                intent.putExtra("sub_category","no");
                startActivity(intent);

            }
        });

        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),PostsListing.class);
                intent.putExtra("activity","dashboard");
                intent.putExtra("category","Vendor");
                intent.putExtra("sub_category","no");
                startActivity(intent);

            }
        });

        competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),PostsListing.class);
                intent.putExtra("activity","dashboard");
                intent.putExtra("category","Organize");
                intent.putExtra("sub_category","no");
                startActivity(intent);

            }
        });

    }
}
