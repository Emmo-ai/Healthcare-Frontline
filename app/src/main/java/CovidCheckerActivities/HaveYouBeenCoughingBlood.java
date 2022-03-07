package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class HaveYouBeenCoughingBlood extends AppCompatActivity {

    Button yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_you_been_coughing_blood);

        yes = findViewById(R.id.yes_coughingBlood);
        no = findViewById(R.id.noCoughingBlood);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HaveYouBeenCoughingBlood.this,TheseSymptomsSoundLikeCovid.class);
                startActivity(i);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HaveYouBeenCoughingBlood.this,AreyouFeelingUnwell.class);
                startActivity(i);
            }
        });
    }
}