package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emmocodeworks.healthcarefrontline.R;

public class HaveYouExperiencedAnyofthese extends AppCompatActivity {

    CheckBox dizziness,cold,bluelipsorface,notPeeingfor8hours,breathingveryfast,fainted,noneofthese;
    Button continue_Haveyou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_you_experienced_anyofthese);

        dizziness = findViewById(R.id.severeDiziness);
        cold = findViewById(R.id.usuallyCold);
        bluelipsorface = findViewById(R.id.blueLips);
        notPeeingfor8hours = findViewById(R.id.notPeeing);
        breathingveryfast = findViewById(R.id.breathingFast);
        fainted = findViewById(R.id.hadAfitorFainted);
        noneofthese = findViewById(R.id.noneoftheseHaveYouExperiencedAnyofThese);

        continue_Haveyou = findViewById(R.id.continueHaveYouExperiencedAnyofthese);

        dizziness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneofthese.setChecked(false);
            }
        });

        cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneofthese.setChecked(false);
            }
        });

        bluelipsorface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneofthese.setChecked(false);
            }
        });

        notPeeingfor8hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneofthese.setChecked(false);
            }
        });

        breathingveryfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneofthese.setChecked(false);
            }
        });

        fainted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneofthese.setChecked(false);
            }
        });

        noneofthese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dizziness.setChecked(false);
                cold.setChecked(false);
                bluelipsorface.setChecked(false);
                notPeeingfor8hours.setChecked(false);
                breathingveryfast.setChecked(false);
                fainted.setChecked(false);
            }
        });


        continue_Haveyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (noneofthese.isChecked()){

                   Intent i = new Intent(HaveYouExperiencedAnyofthese.this,TheseSymptomsSoundLikeCovid.class);
                   startActivity(i);
               }
               else if (dizziness.isChecked() || cold.isChecked() || bluelipsorface.isChecked() || notPeeingfor8hours.isChecked()
               || breathingveryfast.isChecked() || fainted.isChecked()){
                   Intent i = new Intent(HaveYouExperiencedAnyofthese.this,TheseSymptomsSoundLikeCovid.class);
                   startActivity(i);
                }
            }
        });

    }
}