package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.emmocodeworks.healthcarefrontline.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

import classes.AppointmentClass;
import classes.CountryModelClass;

public class AppointmentHistoryAdapter extends RecyclerView.Adapter<AppointmentHistoryAdapter.AppointmentHistoryAdapterViewHolder> {

    DatabaseReference mDatabaseReference ;
    FirebaseAuth mFirebaseAuth;
    String mUser;


    Context context;
    List<AppointmentClass> appointmentHistoryAdapters;

    public AppointmentHistoryAdapter(Context context, List<AppointmentClass> appointmentHistoryAdapters) {
        this.context = context;
        this.appointmentHistoryAdapters = appointmentHistoryAdapters;
    }

    @NonNull
    @Override
    public AppointmentHistoryAdapter.AppointmentHistoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointmen_item, parent, false);
        return new AppointmentHistoryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentHistoryAdapter.AppointmentHistoryAdapterViewHolder holder, int position) {
        AppointmentClass appointmentClass = appointmentHistoryAdapters.get(position);

        holder.subjectOfApp.setText(appointmentClass.getSubject());
        holder.detailsOfApp.setText(appointmentClass.getDetails());
        holder.dateofApp.setText(appointmentClass.getDate());
        holder.timeofApp.setText(appointmentClass.getTime());

        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "#" + (position + 1), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        holder.deleteApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth = FirebaseAuth.getInstance();
                mUser = mFirebaseAuth.getCurrentUser().getUid();


               //mDatabaseReference.child(pushId);
                //String pushId =  mDatabaseReference.push().getKey();

             /*   mDatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("Users").child(mUser).child("Appointments")
               .child(mDatabaseReference.push().getKey());
*/
                mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser).child("Appointments");












            //.child("Medical History");


               /* String pushId =  mDatabaseReference.push().getKey();
                mDatabaseReference.push().child(pushId);*/

             /*   Task<Void> mTask = mDatabaseReference.removeValue();
                appointmentHistoryAdapters.clear();
                mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error deleting", Toast.LENGTH_SHORT).show();
                    }
                });*/

                mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            DataSnapshot firstChild = snapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                            //mDatabaseReference.getRef().removeValue();
                            appointmentHistoryAdapters.clear();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentHistoryAdapters.size();
    }

    public class AppointmentHistoryAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView subjectOfApp,detailsOfApp,timeofApp,dateofApp;
        ConstraintLayout constraintLayout;
        ImageView deleteApp;

        public AppointmentHistoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectOfApp = itemView.findViewById(R.id.subjectApp);
            detailsOfApp = itemView.findViewById(R.id.detailsApp);
            timeofApp = itemView.findViewById(R.id.timeApp);
            dateofApp = itemView.findViewById(R.id.dateApp);
            constraintLayout = itemView.findViewById(R.id.constraintLayout_app);

            deleteApp = itemView.findViewById(R.id.deleteAppointment);

        }
    }
}
