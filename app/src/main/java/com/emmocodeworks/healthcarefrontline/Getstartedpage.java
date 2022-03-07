
package com.emmocodeworks.healthcarefrontline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Getstartedpage extends AppCompatActivity {


    Button getstartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstartedpage);

        getstartedBtn = findViewById(R.id.getstartedbutton);

        getstartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Getstartedpage.this,Signinsignupfacebookgoogle.class);
                startActivity(i);
            }
        });

    }
}