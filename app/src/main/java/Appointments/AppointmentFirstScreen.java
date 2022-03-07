package Appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emmocodeworks.healthcarefrontline.R;

import Checker.CovidChecker;
import Fragments.Appointments;

public class AppointmentFirstScreen extends AppCompatActivity {

    ImageView closeBtn;
    TextView nameofuser;
    Button yesButton;
    ConstraintLayout covidConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_first_screen);


        yesButton = findViewById(R.id.yes_btn_appointmentFirstScreen);
        covidConstraintLayout = findViewById(R.id.covidConstraint);

  /*      closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentFirstScreen.this, Appointments.class);
                startActivity(i);
            }
        });*/

        covidConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentFirstScreen.this, CovidChecker.class);
                startActivity(i);
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentFirstScreen.this,SecondAppointmentScreen.class);
                startActivity(i);
            }
        });

    }
}