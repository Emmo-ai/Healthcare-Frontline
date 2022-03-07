package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class ChemotherapyTreatment extends AppCompatActivity {

    Button yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemotherapy_treatment);

        yes = findViewById(R.id.yes_chemo);
        no = findViewById(R.id.nochemo);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChemotherapyTreatment.this, Sepsis.class);
                startActivity(i);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChemotherapyTreatment.this, AreyouFeelingUnwell.class);
                startActivity(i);
            }
        });
    }
}