package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.emmocodeworks.healthcarefrontline.R;

public class RiskFactorsDoyouhaveanyoftheFollowing extends AppCompatActivity {

    Button continueBtn;

    CheckBox organTransplantCbx, boneTransplantCbx, kidneyAndDialysisCbx, currentlyHavingChemoCbx, noneOfTheseCbx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_factors_doyouhaveanyofthe_following);

        continueBtn = findViewById(R.id.continueRiskFactors);

        organTransplantCbx = findViewById(R.id.organTransplant);
        boneTransplantCbx = findViewById(R.id.boneTransplant);
        kidneyAndDialysisCbx = findViewById(R.id.kidneyDisease_OnDialysis);
        currentlyHavingChemoCbx = findViewById(R.id.Currentlychemo);
        noneOfTheseCbx = findViewById(R.id.noneoftheseRiskFactors);

        noneOfTheseCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneOfTheseCbx.isChecked()){
                    organTransplantCbx.setChecked(false);
                    boneTransplantCbx.setChecked(false);
                    kidneyAndDialysisCbx.setChecked(false);
                    currentlyHavingChemoCbx.setChecked(false);
                }
            }
        });

        currentlyHavingChemoCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentlyHavingChemoCbx.isChecked()){
                    noneOfTheseCbx.setChecked(false);
                }
            }
        });

        kidneyAndDialysisCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kidneyAndDialysisCbx.isChecked()){
                    noneOfTheseCbx.setChecked(false);
                }
            }
        });

        boneTransplantCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boneTransplantCbx.isChecked()){
                    noneOfTheseCbx.setChecked(false);
                }
            }
        });

        organTransplantCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (organTransplantCbx.isChecked()){
                    noneOfTheseCbx.setChecked(false);
                }
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (currentlyHavingChemoCbx.isChecked() || kidneyAndDialysisCbx.isChecked() || boneTransplantCbx.isChecked()
                   || organTransplantCbx.isChecked()){
                  Intent i = new Intent(RiskFactorsDoyouhaveanyoftheFollowing.this , BasedOnWhatYouToldMe.class);
                  startActivity(i);
              }

              else if(noneOfTheseCbx.isChecked()){
                  Intent i = new Intent(RiskFactorsDoyouhaveanyoftheFollowing.this , BasedOnWhatYouToldMe.class);
                  startActivity(i);
              }

            }
        });

    }
}