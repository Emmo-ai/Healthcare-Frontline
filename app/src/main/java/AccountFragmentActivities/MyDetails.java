package AccountFragmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emmocodeworks.healthcarefrontline.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.HashMap;

import classes.ProfileImageClass;
import extras.Tools;


public class MyDetails extends AppCompatActivity {

    ImageView profileImage;
    EditText name,email,phone,gender,dob,height,weight;
    Button saveBtn;
    RadioGroup mRadioGroup;
    RadioButton mRadioButton;

    DatabaseReference mDatabaseReference ;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


       mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String fullname = dataSnapshot.child("fullName").getValue().toString();
                name.setText(fullname);
                String emailDB = dataSnapshot.child("email").getValue().toString();
                email.setText(emailDB);

                //Use later for DB

                if (dataSnapshot.child("phonenumber").exists()) {
                    //do ur stuff
                    String phoneNumber = dataSnapshot.child("phonenumber").getValue().toString();
                    phone.setText(phoneNumber);
                } else {
                    //do something if not exists
                    Toast.makeText(MyDetails.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                }

                if (dataSnapshot.child("weight").exists()) {
                    //do ur stuff
                    String Weight = dataSnapshot.child("weight").getValue().toString();
                    weight.setText(Weight);
                } else {
                    //do something if not exists
                    Toast.makeText(MyDetails.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                }

                if (dataSnapshot.child("height").exists()) {
                    //do ur stuff
                    String Height = dataSnapshot.child("height").getValue().toString();
                    height.setText(Height);
                } else {
                    //do something if not exists
                }

                if (dataSnapshot.child("Date of Birth").exists()) {
                    //do ur stuff
                    String DirthofBirth = dataSnapshot.child("Date of Birth").getValue().toString();
                    dob.setText(DirthofBirth);
                } else {
                    //do something if not exists
                }

             /*   if (dataSnapshot.child("Gender").exists()) {
                    String gender = dataSnapshot.child("Gender").getValue().toString();
                    if(gender.equals("Male")){
                        mRadioButton.setChecked(true);
                    }else if(gender.equals("Female")){
                        mRadioButton.setChecked(true);
                    }
                } else {
                    //do something if not exists
                }*/

        /*        String gender = dataSnapshot.child("gender").getValue().toString();
                if(gender.equalsIgnoreCase("Man")){
                    mGenderMan.setChecked(true);
                }else if(gender.equalsIgnoreCase("Woman")){
                    mGenderWoman.setChecked(true);
                }*/




/*

                if(isAdded()){
                    ProfileImageClass user = dataSnapshot.getValue(ProfileImageClass.class);
                    if (user.getImageUrl() != null && user.getImageUrl().equals("default")){
                        image_profile.setImageResource(R.drawable.ic_launcher_background);
                    } else {
                        Glide.with(getContext()).load(user.getImageUrl()).into(image_profile);
                    }
                }
*/






            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MyDetails.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();

        profileImage = findViewById(R.id.circleImageviewProfile);
        name = findViewById(R.id.nameofuser_mydetails);
        email = findViewById(R.id.emailofuser_mydetails);
        phone = findViewById(R.id.phoneofuserdetails);
        dob = findViewById(R.id.dob_user_details);
        height = findViewById(R.id.height_userdetails);
        weight = findViewById(R.id.weight_mydetails);
        saveBtn = findViewById(R.id.donebtn);

        mRadioGroup = findViewById(R.id.gender);




        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePickerLight();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataCredentials();
            }
        });





    }

    private void getDataCredentials(){
        int radioid = mRadioGroup.getCheckedRadioButtonId();
        mRadioButton = findViewById(radioid);


        String fname = name.getText().toString().trim();

        String DOB = dob.getText().toString().trim();
        String gender = mRadioButton.getText().toString();

        String phonenumber = phone.getText().toString();
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
  /*      float heightValue = Float.parseFloat(heightStr) / 100;
        float weightValue = Float.parseFloat(weightStr);*/


        String id = mDatabaseReference.push().getKey();
        String mUser =mFirebaseAuth.getUid();








        HashMap hashMap = new HashMap();
       // hashMap.put("Firstname", fname);
        hashMap.put("Date of Birth", DOB);
        hashMap.put("Gender", gender);
        hashMap.put("height", heightStr);
        hashMap.put("weight", weightStr);
        hashMap.put("phonenumber",phonenumber);

//child(mUser)

        mDatabaseReference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(MyDetails.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyDetails.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });



        //UserEmailandPassword userCredentials = new UserEmailandPassword(fname,lname,dob,gender,mUser,height1,weight1);

        // mDatabaseReference.setValue(userCredentials);



    }

    public void checkButon(View view) {
        int radioid = mRadioGroup.getCheckedRadioButtonId();

        mRadioButton = findViewById(radioid);

        Toast.makeText(this, "Selected Radio Button" + mRadioButton.getText(), Toast.LENGTH_SHORT).show();
    }


    private void dialogDatePickerLight() {
        Calendar cur_calender = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePicker = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        ((TextView) findViewById(R.id.dob_user_details)).setText(Tools.getFormattedDateSimple(date_ship_millis));



                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.lightgreen));
        //  datePicker.setMinDate(cur_calender);
        datePicker.show(getFragmentManager(), "Datepickerdialog");

    }
}