package Checker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class SymptomCheckerFirstScreen extends AppCompatActivity {

    Button startChecking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker_first_screen);

        startChecking = findViewById(R.id.startCheck);
        startChecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SymptomCheckerFirstScreen.this,CovidChecker.class);
                startActivity(i);
            }
        });
    }
}