package com.emmocodeworks.healthcarefrontline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Signinsignupfacebookgoogle extends AppCompatActivity {

    Button signup,loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinsignupfacebookgoogle);

        initviews();


    }

    private void initviews() {
        signup = findViewById(R.id.signupbutton);
        loginbtn = findViewById(R.id.loginbtnfacebookgoogle);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signinsignupfacebookgoogle.this,SignUp.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signinsignupfacebookgoogle.this, Login.class);
                startActivity(i);
            }
        });
    }
}