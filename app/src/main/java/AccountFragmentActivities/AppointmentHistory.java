package AccountFragmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.emmocodeworks.healthcarefrontline.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.AppointmentHistoryAdapter;
import Adapters.CountryAdapter;
import classes.AppointmentClass;
import classes.CountryModelClass;
import classes.MedicalHistoryClass;

public class AppointmentHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AppointmentClass> appointmentClassList;
    private AppointmentHistoryAdapter adapter;



    DatabaseReference mDatabaseReference ;
    FirebaseAuth mFirebaseAuth;
    String mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        recyclerView = findViewById(R.id.recylcerApp);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser().getUid();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appointmentClassList = new ArrayList<>();



        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser).child("Appointments");//.child("Medical History");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    AppointmentClass appointmentClassCreating = postSnapshot.getValue(AppointmentClass.class);
                    appointmentClassList.add(appointmentClassCreating);
                }
                adapter = new AppointmentHistoryAdapter(AppointmentHistory.this,appointmentClassList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentHistory.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}