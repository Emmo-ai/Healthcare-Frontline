package com.emmocodeworks.healthcarefrontline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(MainActivity.this,
                        Getstartedpage.class);
                startActivity(i);
                finish();
            }
        }, 500);*/

        auth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!= null){
                    splashrun();
                   /* Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(intent);
                    finish();*/
                    // return;
                }
                else{
                    splashrun1();
                }
            }
        };





    }

    private void splashrun() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(MainActivity.this,
                        DashboadActivity.class);
                startActivity(i);
                finish();
            }
        }, 500);
    }


    private void splashrun1() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this,
                        SignUp.class);
                startActivity(i);
                finish();
            }
        }, 500);
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(firebaseAuthListener);
    }
}