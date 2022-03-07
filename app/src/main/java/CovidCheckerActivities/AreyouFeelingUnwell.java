package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class AreyouFeelingUnwell extends AppCompatActivity {

    Button yes,no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areyou_feeling_unwell);

        yes = findViewById(R.id.yes_feelingunwell);
        no = findViewById(R.id.nofeelingunwell);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AreyouFeelingUnwell.this, HaveYouExperiencedAnyofthese.class);
                startActivity(i);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AreyouFeelingUnwell.this, SomePPeople.class);
                startActivity(i);
            }
        });
    }
}