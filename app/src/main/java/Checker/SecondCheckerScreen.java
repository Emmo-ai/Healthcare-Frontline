package Checker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class SecondCheckerScreen extends AppCompatActivity {

    Button searchSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_checker_screen);

        searchSymptom = findViewById(R.id.searchforAsymptom);

        searchSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondCheckerScreen.this, SymptomCheckingActivity.class);
                startActivity(i);
               // finish();
            }
        });

    }
}