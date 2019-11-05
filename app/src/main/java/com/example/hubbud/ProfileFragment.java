package com.example.hubbud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hubbud.Helper.Prefs;

public class ProfileFragment extends Fragment {

    RelativeLayout ll_hobby1;
    RelativeLayout ll_hobby2;
    RelativeLayout ll_hobby3;
    LinearLayout ll_add;

    TextView tv_h1;
    TextView tv_h2;
    TextView tv_h3;

    View view;

    ImageView iv_edit;
    ImageView iv_profile;
    ImageView closeh1,closeh2,closeh3;

    TextView tv_username,tv_about,tv_gender,tv_bio,tv_first_name,tv_last_name,tv_email,tv_dob, tv_location,tv_city,tv_phone;
    EditText et_username,et_bio,et_first_name,et_last_name,et_dob,et_country,et_city,et_phone;

    Button save;

    Boolean edit_check = true;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        init(view);

        return view;
    }

    private void init(View view) {

        ll_hobby1 = (RelativeLayout) view.findViewById(R.id.hobby1);
        ll_hobby1.setVisibility(View.GONE);
        ll_hobby2 = (RelativeLayout) view.findViewById(R.id.hobby2);
        ll_hobby2.setVisibility(View.GONE);
        ll_hobby3 = (RelativeLayout) view.findViewById(R.id.hobby3);
        ll_hobby3.setVisibility(View.GONE);
        ll_add = (LinearLayout) view.findViewById(R.id.hobbyadd_btn);

        tv_h1 = (TextView) view.findViewById(R.id.tv_h1);
        tv_h2 = (TextView) view.findViewById(R.id.tv_h2);
        tv_h3 = (TextView) view.findViewById(R.id.tv_h3);

        iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
        iv_profile = view.findViewById(R.id.iv_profile);
        closeh1 = (ImageView) view.findViewById(R.id.closeh1);
        closeh2 = (ImageView) view.findViewById(R.id.closeh2);
        closeh3 = (ImageView) view.findViewById(R.id.closeh3);

        tv_username = (TextView) view.findViewById(R.id.tv_username);
        tv_about = view.findViewById(R.id.tv_bio);
        tv_gender = view.findViewById(R.id.tv_gender);
        tv_bio = (TextView) view.findViewById(R.id.tv_bio);
        tv_first_name = (TextView) view.findViewById(R.id.tv_first_name);
        tv_last_name = (TextView) view.findViewById(R.id.tv_last_name);
        tv_dob = (TextView) view.findViewById(R.id.tv_dob);
        tv_location = (TextView) view.findViewById(R.id.tv_location);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_bio = (EditText) view.findViewById(R.id.et_bio);
        et_first_name = (EditText) view.findViewById(R.id.et_first_name);
        et_last_name = (EditText) view.findViewById(R.id.et_last_name);
        et_dob = (EditText) view.findViewById(R.id.et_dob);
        et_country = (EditText) view.findViewById(R.id.et_country);
        et_city = (EditText) view.findViewById(R.id.et_city);
        et_phone = (EditText) view.findViewById(R.id.et_phone);

        save = (Button) view.findViewById(R.id.save);

        tv_email = view.findViewById(R.id.email);

        /*DataProviderFromActivity myActivity= (DataProviderFromActivity) getActivity();
        String hobby = myActivity.getHobby();

        if (hobby != null && !hobby.equals("")){

            if (hobby.equals("Tourism")) {

                ll_hobby1.setVisibility(View.VISIBLE);

            }

            Log.e("frag", "Hobby: " + hobby);

        } else {

            Log.e("frag", "Hobby: " + hobby);

        }*/

        setDataFromPrefs();

        clickListeners();

    }

    @SuppressLint("NewApi")
    private void setDataFromPrefs() {

        if (Prefs.getHobby1FromPref(getContext()).equals("Art & Crafts")){

            Log.e("hob","Hobby Title: " + Prefs.getHobby1FromPref(getContext()));
            Log.e("hob","Hobby Sub Category: " + Prefs.getHobby1subFromPref(getContext()));
            ll_hobby2.setVisibility(View.VISIBLE);
            tv_h2.setText(Prefs.getHobby1subFromPref(getContext()));

        }
        if (Prefs.getHobby2FromPref(getContext()).equals("Sports")){

            Log.e("hob","Hobby Title: " + Prefs.getHobby2FromPref(getContext()));
            Log.e("hob","Hobby Sub Category: " + Prefs.getHobby2subFromPref(getContext()));
            ll_hobby3.setVisibility(View.VISIBLE);
            tv_h3.setText(Prefs.getHobby2subFromPref(getContext()));

        }
        if (Prefs.getHobby3FromPref(getContext()).equals("Tourism")){

            Log.e("hob","Hobby Title: " + Prefs.getHobby3FromPref(getContext()));
            Log.e("hob","Hobby Sub Category: " + Prefs.getHobby3subFromPref(getContext()));
            ll_hobby1.setVisibility(View.VISIBLE);
            tv_h1.setText(Prefs.getHobby3subFromPref(getContext()));

        }

        Log.e("Prefs", "Name: " + Prefs.getUserNameFromPref(getContext()));
        Log.e("Prefs", "Email: " + Prefs.getEmailFromPref(getContext()));
        Log.e("Prefs", "gender: " + Prefs.getUsergenderFromPref(getContext()));
        Log.e("Prefs", "dob: " + Prefs.getUserdobFromPref(getContext()));
        Log.e("Prefs", "pass: " + Prefs.getPasswordFromPref(getContext()));

        et_first_name.setText(Prefs.getUserFullNameFromPref(getContext()));
        et_dob.setText(Prefs.getUserdobFromPref(getContext()));
        tv_username.setText(Prefs.getUserFullNameFromPref(getContext()));
        tv_email.setText(Prefs.getEmailFromPref(getContext()));
        tv_location.setText(Prefs.getLocationFromPref(getContext()));
        tv_about.setText(Prefs.getUseraboutFromPref(getContext()));
        tv_gender.setText(Prefs.getUsergenderFromPref(getContext()));

        if (Prefs.getUsergenderFromPref(getContext()).equals("Male")){

            iv_profile.setImageDrawable(getResources().getDrawable(R.drawable.ic_man));

        } else {

            iv_profile.setImageDrawable(getResources().getDrawable(R.drawable.ic_girl));

        }

    }

    private void clickListeners() {

        ll_add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),AddHobby.class);
                startActivity(intent);

            }
        });

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit();
                Log.e("pro","Edit clicked");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        closeh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteh1();
            }
        });

        closeh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteh1();
            }
        });

        closeh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteh1();
            }
        });

        tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("pro","UserName");

            }
        });

    }

    private void onEdit() {

        Log.e("pro","Bool: " + edit_check);

        if (edit_check) {

            iv_edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_black_24dp));
            editVisibilities();
            edit_check = false;

        } else {

            iv_edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit_white_24dp));
            nonEditVisibilities();
            edit_check = true;

        }

    }

    private void nonEditVisibilities() {

        Log.e("pro","Non Edit visibility");
        tv_username.setVisibility(View.VISIBLE);
        tv_bio.setVisibility(View.VISIBLE);
        tv_first_name.setVisibility(View.VISIBLE);
        tv_last_name.setVisibility(View.VISIBLE);
        tv_dob.setVisibility(View.VISIBLE);
        tv_location.setVisibility(View.VISIBLE);
        tv_city.setVisibility(View.VISIBLE);
        tv_phone.setVisibility(View.VISIBLE);

        et_username.setVisibility(View.GONE);
        et_bio.setVisibility(View.GONE);
        et_first_name.setVisibility(View.GONE);
        et_last_name.setVisibility(View.GONE);
        et_dob.setVisibility(View.GONE);
        et_country.setVisibility(View.GONE);
        et_city.setVisibility(View.GONE);
        et_phone.setVisibility(View.GONE);

        closeh1.setVisibility(View.GONE);
        closeh2.setVisibility(View.GONE);
        closeh3.setVisibility(View.GONE);

        save.setVisibility(View.GONE);

    }

    private void editVisibilities() {

        Log.e("pro","Edit visibility");
        tv_username.setVisibility(View.GONE);
        tv_bio.setVisibility(View.GONE);
        tv_first_name.setVisibility(View.GONE);
        tv_last_name.setVisibility(View.GONE);
        tv_dob.setVisibility(View.GONE);
        tv_location.setVisibility(View.GONE);
        tv_city.setVisibility(View.GONE);
        tv_phone.setVisibility(View.GONE);

        et_username.setVisibility(View.VISIBLE);
        et_bio.setVisibility(View.VISIBLE);
        et_first_name.setVisibility(View.VISIBLE);
        et_last_name.setVisibility(View.VISIBLE);
        et_dob.setVisibility(View.VISIBLE);
        et_country.setVisibility(View.VISIBLE);
        et_city.setVisibility(View.VISIBLE);
        et_phone.setVisibility(View.VISIBLE);

        closeh1.setVisibility(View.VISIBLE);
        closeh2.setVisibility(View.VISIBLE);
        closeh3.setVisibility(View.VISIBLE);

        save.setVisibility(View.VISIBLE);

    }

    private void save() {

        nonEditVisibilities();
        iv_edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit_white_24dp));
        nonEditVisibilities();
        edit_check = true;

    }

    private void deleteh1() {



    }

    private void deleteh2() {



    }

    private void deleteh3() {



    }

}