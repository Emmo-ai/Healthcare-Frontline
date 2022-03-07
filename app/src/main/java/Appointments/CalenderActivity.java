package Appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.emmocodeworks.healthcarefrontline.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import AccountFragmentActivities.AppointmentHistory;
import classes.AppointmentClass;
import classes.MedicalHistoryClass;

public class CalenderActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener {

    LinearLayout setTime;
    Button finishBtn;

    String time;

    String selectedDate;

    String detailsSA,subjectSA;

    DatabaseReference mDatabaseReference ;
    FirebaseAuth mFirebaseAuth;
    String mUser;


    AppointmentClass mAppointmentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser().getUid();

      //  mAppointmentClass = new AppointmentClass();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser).child("Appointments");//.child("Medical History");


        setTime = findViewById(R.id.custom_config);
        finishBtn = findViewById(R.id.Done_button);

        detailsSA = getIntent().getStringExtra("detailsEdx");
        subjectSA = getIntent().getStringExtra("subjectEdx");

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ataacrystals360.





                mAppointmentClass = new AppointmentClass(subjectSA,detailsSA,time,selectedDate);
                mDatabaseReference.push().setValue(mAppointmentClass);
                //mAppointmentClass.setMedical_History(medHistory);
                //  mDatabaseReference.push().setValue(mMedicalHistoryClass);
               // mDatabaseReference.setValue(mMedicalHistoryClass);

                Intent i = new Intent(CalenderActivity.this, AppointmentHistory.class);
                startActivity(i);
                //Toast.makeText(CalenderActivity.this, detailsSA + subjectSA, Toast.LENGTH_SHORT).show();

            }
        });


        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        CalendarPickerView datePicker = findViewById(R.id.calendar);
        datePicker.init(today, nextYear.getTime())
               // .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);

               /* String selectedDate = "" + calSelected.get(Calendar.DAY_OF_MONTH)
                        + " " + (calSelected.get(Calendar.MONTH) + 1)
                        + " " + calSelected.get(Calendar.YEAR);*/
                Toast.makeText(CalenderActivity.this, selectedDate, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onDateUnselected(Date date) {
            }
        });
    }

    public void chooseDate(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
       TextView setDate = findViewById(R.id.dateText);
        time = hourOfDay + ":" + minute ;
        setDate.setText( time);


    }



}

