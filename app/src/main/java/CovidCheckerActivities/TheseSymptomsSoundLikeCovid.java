package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emmocodeworks.healthcarefrontline.R;

import Checker.SymptomCheckerFirstScreen;

public class TheseSymptomsSoundLikeCovid extends AppCompatActivity {

    Button startnewCheck;
    TextView callTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_these_symptoms_sound_like_covid);

        startnewCheck = findViewById(R.id.startnewSymptomcheck_SoundlikeCovid);
        callTextView = findViewById(R.id.callTxt);

        startnewCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TheseSymptomsSoundLikeCovid.this, SymptomCheckerFirstScreen.class);
                startActivity(i);
            }
        });

        callTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });

    }

}