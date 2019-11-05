package com.example.hubbud;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    Button logout;
    View v;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    private static Context mContext;
    CardView cv_aboutUs;
    CardView cv_privacy_policy;
    CardView cv_contact_us;
    CardView cv_invite_friends;
    CardView cv_reviews;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_settings, container, false);

        init();

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void init() {

        mAuth = FirebaseAuth.getInstance();

        logout = (Button) v.findViewById(R.id.log_out);

        cv_aboutUs = v.findViewById(R.id.cv_about_us);
        cv_privacy_policy = v.findViewById(R.id.cv_privacy_policy);
        cv_contact_us = v.findViewById(R.id.cv_contact_us);
        cv_invite_friends = v.findViewById(R.id.cv_invite);
        cv_reviews = v.findViewById(R.id.cv_reviews);

        clickListeners();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    Intent intent = new Intent(mContext, SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);
                }
            }
        };

    }

    private void clickListeners() {

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        cv_aboutUs.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AboutUs.class));
            }
        });

        cv_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),PrivacyPolicy.class));
            }
        });

        cv_contact_us.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ContactUs.class));
            }
        });

        cv_invite_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareApp();

            }
        });

        cv_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void shareApp() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        //String shareBodyText = "Please Download this App \n https://play.google.com/store/apps/details?id=hubbud.com";
        String shareBodyText = "Please Download Hub Bud!";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Hub Bud Android App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));

    }

    public void logOut() {

        mAuth.signOut();

    }

}