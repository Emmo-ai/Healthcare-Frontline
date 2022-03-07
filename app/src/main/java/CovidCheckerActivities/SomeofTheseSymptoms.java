package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emmocodeworks.healthcarefrontline.R;

import Checker.CovidChecker;

public class SomeofTheseSymptoms extends AppCompatActivity {

   Button startSymptomCheckerBtn;
   TextView callTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someof_these_symptoms);

        startSymptomCheckerBtn = findViewById(R.id.startnewSymptomcheck_someofthessyptomes);
        startSymptomCheckerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SomeofTheseSymptoms.this , CovidChecker.class);
                startActivity(i);
            }
        });

        callTxt = findViewById(R.id.CALL112);
        callTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });

    }
}