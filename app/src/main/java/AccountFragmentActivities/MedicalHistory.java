package AccountFragmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmocodeworks.healthcarefrontline.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import classes.MedicalHistoryClass;

public class MedicalHistory extends AppCompatActivity {

    DatabaseReference mDatabaseReference ;
    FirebaseAuth mFirebaseAuth;

    EditText medicalHistory;
    Button doneBtn;

    String mUser;

    MedicalHistoryClass mMedicalHistoryClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser().getUid();

        mMedicalHistoryClass = new MedicalHistoryClass();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser).child("Medical History");//.child("Medical History");




        medicalHistory = findViewById(R.id.medical_history_medicalhistory);
        doneBtn = findViewById(R.id.doneBtn_medicalHistory);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medHistory = medicalHistory.getText().toString();
                mMedicalHistoryClass.setMedical_History(medHistory);
              //  mDatabaseReference.push().setValue(mMedicalHistoryClass);
                mDatabaseReference.setValue(mMedicalHistoryClass);
                Toast.makeText(MedicalHistory.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("medical_History").exists()) {
                    //do ur stuff
                    String medHistory = snapshot.child("medical_History").getValue().toString();
                    medicalHistory.setText(medHistory);
                } else {
                    //do something if not exists
                    Toast.makeText(MedicalHistory.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MedicalHistory.this, "Error loading data", Toast.LENGTH_SHORT).show();

            }
        });


    }
















}