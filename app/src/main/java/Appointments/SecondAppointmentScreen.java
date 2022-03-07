package Appointments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emmocodeworks.healthcarefrontline.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import classes.MedicalHistoryClass;

public class SecondAppointmentScreen extends AppCompatActivity {

    Button continueBtn;

    EditText subjectEdtx,enterDetailsEdtx;


    String subject,details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_appointment_screen);

        subjectEdtx  = findViewById(R.id.subjectAppointmentEdx);
        enterDetailsEdtx = findViewById(R.id.enterDetailsEdtx);


        continueBtn = findViewById(R.id.conti);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = subjectEdtx.getText().toString();
                details = enterDetailsEdtx.getText().toString();

                if (TextUtils.isEmpty(subject)) {
                    Toast.makeText(SecondAppointmentScreen.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                    subjectEdtx.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(details)) {
                    Toast.makeText(SecondAppointmentScreen.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                    enterDetailsEdtx.requestFocus();
                    return;
                }


                //Toast.makeText(SecondAppointmentScreen.this, subject + " " + details, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SecondAppointmentScreen.this, CalenderActivity.class);
                i.putExtra("subjectEdx",subject);
                i.putExtra("detailsEdx",details);
                startActivity(i);
            }
        });

    }

}