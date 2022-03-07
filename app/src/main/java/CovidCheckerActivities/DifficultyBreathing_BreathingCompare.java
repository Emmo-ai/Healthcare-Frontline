package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class DifficultyBreathing_BreathingCompare extends AppCompatActivity {

    Button bitworse,muchworse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_breathing__breathing_compare);

        bitworse = findViewById(R.id.bitworsethanusual);
        muchworse = findViewById(R.id.muchworsethanusual);

        bitworse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyBreathing_BreathingCompare.this, LongTermLungConditions.class);
                startActivity(i);
            }
        });

        muchworse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyBreathing_BreathingCompare.this,LongTermLungConditions.class);
                startActivity(i);
            }
        });

    }
}