package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emmocodeworks.healthcarefrontline.R;

public class DoesAnyApplytoyou extends AppCompatActivity {

    CheckBox organTransplantCbx,boneTransplantCbx,kidneydialisisCbx,chemoCbx,noneOfTheseCbx;
    Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_does_any_applytoyou);

        organTransplantCbx = findViewById(R.id.heartRelatedCheckbox);
        boneTransplantCbx = findViewById(R.id.boneTransplant);
        kidneydialisisCbx = findViewById(R.id.kidneyDisease);
        chemoCbx = findViewById(R.id.chemo);
        noneOfTheseCbx = findViewById(R.id.noneofThese_DoesAnyApplytoYou);

        chemoCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneOfTheseCbx.setChecked(false);
            }
        });

        kidneydialisisCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneOfTheseCbx.setChecked(false);
            }
        });

        organTransplantCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneOfTheseCbx.setChecked(false);
            }
        });

        boneTransplantCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneOfTheseCbx.setChecked(false);
            }
        });

        noneOfTheseCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                organTransplantCbx.setChecked(false);
                boneTransplantCbx.setChecked(false);
                kidneydialisisCbx.setChecked(false);
                chemoCbx.setChecked(false);
            }
        });

        continueBtn = findViewById(R.id.continue_doesanyapplyToYou);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneOfTheseCbx.isChecked()){
                    Intent i = new Intent(DoesAnyApplytoyou.this, BasedOnWhatYouToldMe.class);
                    startActivity(i);
                }

                else if (organTransplantCbx.isChecked() || boneTransplantCbx.isChecked() || kidneydialisisCbx.isChecked() || chemoCbx.isChecked()){
                    Intent i = new Intent(DoesAnyApplytoyou.this, BasedOnWhatYouToldMe.class);
                    startActivity(i);
                }

            }
        });

    }
}