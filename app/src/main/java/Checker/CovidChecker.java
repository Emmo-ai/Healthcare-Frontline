package Checker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.emmocodeworks.healthcarefrontline.R;

import java.util.ServiceConfigurationError;

import CovidCheckerActivities.FirstCovidScreen;

public class CovidChecker extends AppCompatActivity {

    Button yesCovid,noCovid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_checker);

        yesCovid = findViewById(R.id.yesCovidChecker);
        noCovid = findViewById(R.id.no_covidChecker);

        yesCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CovidChecker.this, FirstCovidScreen.class);
                startActivity(i);
            }
        });

        noCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CovidChecker.this, SecondCheckerScreen.class);
                startActivity(i);
            }
        });

    }
}