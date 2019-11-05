package com.example.hubbud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubbud.Database.FirebaseDatabaseHelper;
import com.example.hubbud.Helper.MyApp;
import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private EditText email_id, input_phone, et_about, et_city, et_country, et_name, et_dob, passwordcheck;
    Switch switch_gender;
    private FirebaseAuth mAuth;
    private static final String TAG = "";
    private ProgressBar progressBar;

    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextView btnSignUp = (TextView) findViewById(R.id.login_page);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        email_id = (EditText) findViewById(R.id.input_email);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        passwordcheck = (EditText) findViewById(R.id.input_password);
        et_name = findViewById(R.id.et_name);
        et_dob = findViewById(R.id.et_dob);
        switch_gender = findViewById(R.id.switch_gender);
        et_about = findViewById(R.id.et_about);
        et_city = findViewById(R.id.et_city);
        et_country = findViewById(R.id.et_country);
        input_phone = findViewById(R.id.input_phone);

        switch_gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }

            }
        });

        Button ahsignup = findViewById(R.id.btn_signup);
        ahsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_id.getText().toString();
                String password = passwordcheck.getText().toString();
                /*if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (et_name.getText().toString().isEmpty()) {

                    et_name.setError("Required!");

                } else if (et_about.getText().toString().isEmpty()) {

                    et_about.setError("Required!");

                } else if (et_dob.getText().toString().isEmpty()) {

                    et_dob.setError("Required!");

                } else if (et_city.getText().toString().isEmpty()) {

                    et_city.setError("Required!");

                } else if (et_country.getText().toString().isEmpty()) {

                    et_country.setError("Required!");

                } else if (input_phone.getText().toString().isEmpty()) {

                    input_phone.setError("Required!");

                } else if (email_id.getText().toString().isEmpty()) {

                    email_id.setError("Required!");

                } else if (passwordcheck.getText().toString().isEmpty()) {


                    passwordcheck.setError("Required!");


                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    private void updateUI(final FirebaseUser user) {

        if (user != null) {

            //Toast.makeText(getApplicationContext(), "Users Verified: " + user.isEmailVerified(), Toast.LENGTH_LONG).show();

            //realtimeDatabase(user);
            final Users user1 = new Users("Photo", et_name.getText().toString(), et_about.getText().toString(), et_city.getText().toString() + " / " + et_country.getText().toString(), user.getEmail(),
                    user.getEmail(), gender, et_dob.getText().toString(), passwordcheck.getText().toString(), input_phone.getText().toString(), user.getUid());

            new FirebaseDatabaseHelper().addUser(user1, new FirebaseDatabaseHelper.UserStatus() {
                @Override
                public void userisLoaded(List<Users> usersArray, List<String> keys) {

                }

                @Override
                public void userisInserted() {

                    progressBar.setVisibility(View.GONE);
                    Log.e("Prefs", "userid: " + user1.getUserid());
                    Log.e("Prefs", "getName: " + user1.getName());
                    Prefs.addPrefsForLogin(SignUpActivity.this, user1.getUserid(), user1.getName(), user1.getAbout(), user1.getLocation(), user.getDisplayName(),
                            user.getEmail(), et_dob.getText().toString(), gender, input_phone.getText().toString(), passwordcheck.getText().toString());

                    Log.e("Prefs: ", "User_Id" + user1.getUserid());
                    Log.e("Prefs: ", "User_Id" + Prefs.getUserIDFromPref(SignUpActivity.this));

                    Toast.makeText(SignUpActivity.this, "Sign Up Successful!", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "Sign Up Successful!");

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

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

}