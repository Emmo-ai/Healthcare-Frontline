package com.emmocodeworks.healthcarefrontline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.Account;
import Fragments.Appointments;
import Fragments.Diagnosis;
import Fragments.HomeFragment;

public class DashboadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);




        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainactivityid,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;

                     case R.id.appointment:
                            selectedFragment = new Appointments();
                            break;


                        case R.id.diagnosis:
                            selectedFragment = new Diagnosis();
                            break;


                        case R.id.account:
                            selectedFragment = new Account();
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.mainactivityid,
                            selectedFragment).commit();

                    return true;
                }


            };
}