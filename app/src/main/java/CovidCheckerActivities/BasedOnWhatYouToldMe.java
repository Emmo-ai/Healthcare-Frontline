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

public class BasedOnWhatYouToldMe extends AppCompatActivity {

    Button getInfoonCovid;
    TextView startNewSymptomCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_based_on_what_you_told_me);

        getInfoonCovid = findViewById(R.id.getMoreinfoOnCorona);

        startNewSymptomCheck = findViewById(R.id.startnewSymptomcheck);
        startNewSymptomCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BasedOnWhatYouToldMe.this, SymptomCheckerFirstScreen.class);
                startActivity(i);
            }
        });

        getInfoonCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.who.int/emergencies/diseases/novel-coronavirus-2019");
            }
        });


    }

    private  void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}