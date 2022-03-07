package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emmocodeworks.healthcarefrontline.R;

public class SomePPeople extends AppCompatActivity {
    CheckBox heartRelatedDiseases, diabetes,parkinsonsCheck,kindneylungliver,noneofthese;
    Button continue_SomepeopleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_p_people);

        heartRelatedDiseases = findViewById(R.id.heartRelatedCheckbox);
        diabetes = findViewById(R.id.diabetes_CheckBox);
        parkinsonsCheck = findViewById(R.id.parkinsonsCheckbox);
        kindneylungliver = findViewById(R.id.kidney_lung_checkBox);
        noneofthese = findViewById(R.id.noneOfThese_somePeopleCheckbox);

        heartRelatedDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heartRelatedDiseases.isChecked()){
                    noneofthese.setChecked(false);
                }

            }
        });

        diabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (diabetes.isChecked()){
                    noneofthese.setChecked(false);
                }

            }
        });

        parkinsonsCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parkinsonsCheck.isChecked()){
                    noneofthese.setChecked(false);
                }
            }
        });

        kindneylungliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kindneylungliver.isChecked()){
                    noneofthese.setChecked(false);
                }
            }
        });

        noneofthese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneofthese.isChecked()) {
                    heartRelatedDiseases.setChecked(false);
                    diabetes.setChecked(false);
                    parkinsonsCheck.setChecked(false);
                    kindneylungliver.setChecked(false);
                }
            }
        });


        continue_SomepeopleBtn = findViewById(R.id.continue_somePeople);
        continue_SomepeopleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneofthese.isChecked()){
                    heartRelatedDiseases.setChecked(false);
                    diabetes.setChecked(false);
                    parkinsonsCheck.setChecked(false);
                    kindneylungliver.setChecked(false);
                    Intent i= new Intent(SomePPeople.this, RiskFactorsDoyouhaveanyoftheFollowing.class);
                    startActivity(i);
                }

                else if(heartRelatedDiseases.isChecked() || diabetes.isChecked() || parkinsonsCheck.isChecked() | kindneylungliver.isChecked() ){
                    noneofthese.setChecked(false);
                    Intent i = new Intent(SomePPeople.this, RiskFactorsDoyouhaveanyoftheFollowing.class);
                    startActivity(i);
                }


            }
        });


    }
}