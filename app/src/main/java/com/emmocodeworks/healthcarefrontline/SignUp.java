package com.emmocodeworks.healthcarefrontline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Appointments.AppointmentFirstScreen;
import Appointments.SecondAppointmentScreen;
import classes.UserEmailandPassword;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    TextView signinTxt;
    Button signUpbtn;

    EditText fullnameEdt,emailEdt,passwordEdt;

    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initviewsandonclick();


    }

    private void initviewsandonclick() {

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        signinTxt = findViewById(R.id.signintextview);
        signUpbtn = findViewById(R.id.signupactivitybtn);

        fullnameEdt = findViewById(R.id.fullname);
        emailEdt = findViewById(R.id.email);
        passwordEdt = findViewById(R.id.password);


        signinTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, Login.class);
              //Intent a =   new Intent(SignUp.this, AppointmentFirstScreen.class);
                startActivity(i);
            }
        });

      signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*Intent i = new Intent(SignUp.this, DashboadActivity.class);
                startActivity(i);*/
                registerUser();
            }
        });

        //signUpbtn.setOnClickListener(this);


    }

    private void registerUser(){

        final String fullname = fullnameEdt.getText().toString().trim();
        final String email = emailEdt.getText().toString().trim();
        final String password = passwordEdt.getText().toString().trim();

        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show();
            fullnameEdt.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //stopping the function execution
            emailEdt.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            passwordEdt.requestFocus();
            return;

        }







        progressDialog.setMessage("Signing Up,please wait...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Registered", Toast.LENGTH_SHORT).show();

                    String id = mDatabaseReference.push().getKey();
                    UserEmailandPassword useremailandpassword = new UserEmailandPassword(fullname,email,password);
                    String mUser =firebaseAuth.getUid();
                    String userId = mDatabaseReference.push().getKey();
                    mDatabaseReference.child(mUser).setValue(useremailandpassword);

                   Intent i = new Intent(getApplicationContext(),DashboadActivity.class);
                    startActivity(i);

                }

                else{
                    Toast.makeText(SignUp.this, "error", Toast.LENGTH_SHORT).show();
                } progressDialog.hide();
            }
        });









    }


    @Override
    public void onClick(View v) {

    }
}