package CovidCheckerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmocodeworks.healthcarefrontline.R;

public class LongTermLungConditions extends AppCompatActivity {

    Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_term_lung_conditions);

        yes = findViewById(R.id.yesLongTerm);
        no = findViewById(R.id.noLongTerm);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LongTermLungConditions.this,AreyouFeelingUnwell.class);
                startActivity(i);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LongTermLungConditions.this,AreyouFeelingUnwell.class);
                startActivity(i);
            }
        });
    }
}