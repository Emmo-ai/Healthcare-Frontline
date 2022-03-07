package com.emmocodeworks.healthcarefrontline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button loginbtn;
    TextView signupview;

    EditText emailEdt,PasswordEdt;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initviewsandonclicks();

    }

    private void initviewsandonclicks() {
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


        loginbtn = findViewById(R.id.loginactivitybtn);
        signupview = findViewById(R.id.signuptextview);

        emailEdt = findViewById(R.id.email_login);
        PasswordEdt = findViewById(R.id.password_login);

        signupview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }


    private void userLogin(){
        String email = emailEdt.getText().toString().trim();
        String password = PasswordEdt.getText().toString().trim();



        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            emailEdt.requestFocus();
            //stopping the function execution
            return;
        }

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            PasswordEdt.requestFocus();
            return;
        }

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            finish();
                            //start homepage(main) activity
                            startActivity(new Intent(getApplicationContext(),DashboadActivity.class ));


                        }
                    }
                });
    }
}