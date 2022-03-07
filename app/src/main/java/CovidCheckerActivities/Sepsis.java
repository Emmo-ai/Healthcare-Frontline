package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

import Checker.SymptomCheckerFirstScreen;

public class Sepsis extends AppCompatActivity {

    Button startNew,callTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepsis);



        startNew = findViewById(R.id.startchck);
        startNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sepsis.this, SymptomCheckerFirstScreen.class);
                startActivity(i);
            }
        });

        callTxt = findViewById(R.id.calltext);
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