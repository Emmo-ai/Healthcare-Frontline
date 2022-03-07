package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emmocodeworks.healthcarefrontline.R;

public class DescribeCough extends AppCompatActivity {

    CheckBox newNormalCbx,continousCbx,moreCoughingfitsCbx,noneOftheseCbx;
    Button continueBtnDescribeCough;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe_cough);

        newNormalCbx = findViewById(R.id.worsethannewnormal);
        continousCbx = findViewById(R.id.coughingContinously);
        moreCoughingfitsCbx  = findViewById(R.id.threeormoreCoughs);
        noneOftheseCbx = findViewById(R.id.noneOfTheseDescribeCough);

        noneOftheseCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneOftheseCbx.isChecked()){
                    newNormalCbx.setChecked(false);
                    continousCbx.setChecked(false);
                    moreCoughingfitsCbx.setChecked(false);

                }
            }
        });

        newNormalCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newNormalCbx.isChecked()){
                    noneOftheseCbx.setChecked(false);
                }
            }
        });

        continousCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (continousCbx.isChecked()){
                    noneOftheseCbx.setChecked(false);
                }
            }
        });

        moreCoughingfitsCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moreCoughingfitsCbx.isChecked()){
                    noneOftheseCbx.setChecked(false);
                }
            }
        });

        continueBtnDescribeCough = findViewById(R.id.continue_describe_cough);
        continueBtnDescribeCough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noneOftheseCbx.isChecked()){
                    newNormalCbx.setChecked(false);
                    continousCbx.setChecked(false);
                    moreCoughingfitsCbx.setChecked(false);
                    Intent i = new Intent(DescribeCough.this, SomePPeople.class);
                    startActivity(i);
                }
                else if(newNormalCbx.isChecked() || continousCbx.isChecked() || moreCoughingfitsCbx.isChecked()){
                    noneOftheseCbx.setChecked(false);
                    Intent i = new Intent(DescribeCough.this,HaveYouBeenCoughingBlood.class );
                    startActivity(i);
                }
            }

        });
    }
}