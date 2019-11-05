package com.example.hubbud;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hubbud.Helper.GPSHelper;
import com.example.hubbud.Interfaces.DataProviderFromActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataProviderFromActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    ViewPager viewPager;
    BottomNavigationView bNavView;
    String hobby;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS=0x2;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hobby = getIntent().getStringExtra("hobby");

        if (hobby != null && !hobby.equals("")){

            Log.e("main", "Hobby: " + hobby);
            Bundle bundle = new Bundle();
            bundle.putString("hobby", hobby);
            ProfileFragment fragInfo = new ProfileFragment();
            fragInfo.setArguments(bundle);

            /*transaction.replace(R.id.fragment_single, fragInfo);
            transaction.commit();*/

        } else {

            Log.e("main", "Hobby: " + hobby);

        }

        init();
        viewPagerTabChangeListner();
        onNavigationChangeListner();

    }

    private void init() {

        bNavView = findViewById(R.id.navigation);

        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    finish();
                }
            }
        };

        /*FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);

        checkPermissions();

        GPSHelper gpsHelper = new GPSHelper(MainActivity.this);

        showNetworkDialog();

        if (gpsHelper.isGPSenabled()){

            showNetworkDialog();

        } else {

        }

    }

    private void showNetworkDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.location_dialog);
        Button btn_contact = dialog.findViewById(R.id.btn_contact);
        Button btn_location = dialog.findViewById(R.id.btn_location);
        ImageView iv_close = dialog.findViewById(R.id.iv_close);

        dialog.show();
        dialog.setCancelable(false);

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sendMessage(et_message.getText().toString(),blogPostList.get(holder.getAdapterPosition()));
                finish();

            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                dialog.dismiss();

            }
        });

    }

    public String getHobby(){
        return hobby;
    }

    private void onNavigationChangeListner(){

        bNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;

                    case R.id.navigation_history:
                        viewPager.setCurrentItem(1);
                        return true;

                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(2);
                        return true;

                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(3);
                        return true;

                    case R.id.navigation_Settings:
                        viewPager.setCurrentItem(4);
                        //mAuth.signOut();
                        return true;

                }

                return false;

            }
        });

    }

    public void viewPagerTabChangeListner(){

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){

                    case 0:
                        bNavView.setSelectedItemId(R.id.navigation_home);
                        break;

                    case 1:
                        bNavView.setSelectedItemId(R.id.navigation_history);
                        break;

                    case 2:
                        bNavView.setSelectedItemId(R.id.navigation_dashboard);
                        break;

                    case 3:
                        bNavView.setSelectedItemId(R.id.navigation_notifications);
                        break;

                    case 4:
                        bNavView.setSelectedItemId(R.id.navigation_Settings);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new HistoryFragment(), "History");
        adapter.addFragment(new DashboardFragment(), "Dashboard");
        adapter.addFragment(new MessageFragment(), "Home");
        adapter.addFragment(new SettingsFragment(), "Settings");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            //getMyLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            //getMyLocation();
        }
    }

}