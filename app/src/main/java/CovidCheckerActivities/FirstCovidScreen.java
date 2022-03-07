package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.emmocodeworks.healthcarefrontline.R;

public class FirstCovidScreen extends AppCompatActivity {

    CheckBox severeChestPain,severeDifficultyBreathing,suddenSwellingofEyes,slurredSpeech,rashDoesntGo,noneofThese;
    Button continueBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_covid_screen);

        severeChestPain = findViewById(R.id.chestPain);
        severeDifficultyBreathing = findViewById(R.id.difficultyBreathing);
        suddenSwellingofEyes = findViewById(R.id.swellingOfEyes);
        slurredSpeech = findViewById(R.id.slurredSpeech1);
        rashDoesntGo = findViewById(R.id.rashwontgo);
        noneofThese = findViewById(R.id.noneOfthesE);

        continueBtn = findViewById(R.id.continueBtn_firstcovidScreen);


        noneofThese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneofThese.isChecked()){
                    severeChestPain.setChecked(false);
                    severeDifficultyBreathing.setChecked(false);
                    suddenSwellingofEyes.setChecked(false);
                    slurredSpeech.setChecked(false);
                    rashDoesntGo.setChecked(false);
                }
            }
        });

        severeChestPain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (severeChestPain.isChecked()){
                    noneofThese.setChecked(false);
                }
            }
        });

        severeDifficultyBreathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (severeDifficultyBreathing.isChecked()){
                    noneofThese.setChecked(false);
                }
            }
        });

        suddenSwellingofEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suddenSwellingofEyes.isChecked()){
                    noneofThese.setChecked(false);
                }
            }
        });


        slurredSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slurredSpeech.isChecked()){
                    noneofThese.setChecked(false);
                }
            }
        });

        rashDoesntGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rashDoesntGo.isChecked()){
                    noneofThese.setChecked(false);
                }
            }
        });


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneofThese.isChecked()){
                    Intent i = new Intent(FirstCovidScreen.this,Doyouhaveanyofthese.class);
                    startActivity(i);
                }
                else if(severeChestPain.isChecked() || severeChestPain.isChecked() || suddenSwellingofEyes.isChecked() ||
                        slurredSpeech.isChecked() || rashDoesntGo.isChecked()
                        ){
                        noneofThese.setChecked(false);
                    Intent i  = new Intent(FirstCovidScreen.this,SomeofTheseSymptoms.class);
                    startActivity(i);
                }
            }
        });


    }
}