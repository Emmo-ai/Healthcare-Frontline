package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emmocodeworks.healthcarefrontline.R;

public class Doyouhaveanyofthese extends AppCompatActivity {
    CheckBox cough,feverish,breathing,lossOfSmell,noneOfTheseCheck;
    Button continueBtn_doyouhaveanyofthese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doyouhaveanyofthese);

        cough = findViewById(R.id.coughCheckbox);
        feverish = findViewById(R.id.feverishCheckbox);
        breathing = findViewById(R.id.difficultyBreathingCheckbox);
        lossOfSmell = findViewById(R.id.lossofSmellCheckbox);
        noneOfTheseCheck = findViewById(R.id.noneOfThese);



        noneOfTheseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneOfTheseCheck.isChecked()){
                    cough.setChecked(false);
                    feverish.setChecked(false);
                    breathing.setChecked(false);
                    lossOfSmell.setChecked(false);
                }
            }
        });

        cough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cough.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                }
            }
        });

        feverish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feverish.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                }
            }
        });

        breathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (breathing.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                }
            }
        });


        lossOfSmell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lossOfSmell.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                }
            }
        });




        continueBtn_doyouhaveanyofthese  = findViewById(R.id.continueBtn_doyouhaveanyofthese);
        continueBtn_doyouhaveanyofthese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noneOfTheseCheck.isChecked()){
                    cough.setChecked(false);
                    feverish.setChecked(false);
                    breathing.setChecked(false);
                    lossOfSmell.setChecked(false);
                    Intent i = new Intent(Doyouhaveanyofthese.this,SomePPeople.class);
                    startActivity(i);
                }
                else if(cough.isChecked() /*|| feverish.isChecked() || breathing.isChecked() ||
                        lossOfSmell.isChecked()*/){
                    noneOfTheseCheck.setChecked(false);
                    Intent i  = new Intent(Doyouhaveanyofthese.this,DescribeCough.class);
                    startActivity(i);
                }

                else if(feverish.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                    Intent i = new Intent(Doyouhaveanyofthese.this,ChemotherapyTreatment.class);
                    startActivity(i);
                }

                else if(breathing.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                    Intent i = new Intent(Doyouhaveanyofthese.this,DifficultyBreathing_BreathingCompare.class);
                    startActivity(i);
                }

                else if (lossOfSmell.isChecked()){
                    noneOfTheseCheck.setChecked(false);
                    Intent i = new Intent(Doyouhaveanyofthese.this, AreyouFeelingUnwell.class);
                    startActivity(i);
                }
            }
        });




    }
}