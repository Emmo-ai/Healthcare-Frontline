package Fragments;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emmocodeworks.healthcarefrontline.Login;
import com.emmocodeworks.healthcarefrontline.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import AccountFragmentActivities.AppointmentHistory;
import AccountFragmentActivities.MedicalHistory;
import AccountFragmentActivities.MyDetails;
import classes.ProfileImageClass;
import classes.UserEmailandPassword;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class Account extends Fragment {

    Button logoutBtn;
    FirebaseAuth firebaseAuth;

    CircleImageView image_profile;

    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;

    DatabaseReference mDatabaseReference;

    TextView name,email;

    ConstraintLayout myDetails,myappointments,medicalHistory,notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        firebasemethods(view);

        name = view.findViewById(R.id.nameofuser);
        email = view.findViewById(R.id.emailofuser);

        medicalHistory = view.findViewById(R.id.medical_history);
        myappointments = view.findViewById(R.id.appoitnmenthistory);

        myappointments.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), AppointmentHistory.class);
            startActivity(i);
        });

        medicalHistory.setOnClickListener(v -> {
            Intent i  = new Intent(getActivity(), MedicalHistory.class);
            startActivity(i);
        });

     /*   notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AppointmentHistory.class);
                startActivity(i);
            }
        });
*/

        myDetails = view.findViewById(R.id.mydetails);
        myDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getActivity(), MyDetails.class);
                startActivity(i);
            }
        });


        logoutBtn = view.findViewById(R.id.logoutbtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.getInstance().signOut();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), Login.class );
                startActivity(intent);
            }
        });

        image_profile = view.findViewById(R.id.profilepic);
        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });


        return view;
    }


    public void firebasemethods(View view) {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
        //  String uid = mFirebaseUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        //storageReference = FirebaseStorage.getInstance().getReference("uploads");



        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String fullname = dataSnapshot.child("fullName").getValue().toString();
                name.setText(fullname);
                String emailDB = dataSnapshot.child("email").getValue().toString();
                email.setText(emailDB);


                if(isAdded()){
                    ProfileImageClass user = dataSnapshot.getValue(ProfileImageClass.class);
                    if (user.getImageUrl() != null && user.getImageUrl().equals("default")){
                        image_profile.setImageResource(R.drawable.ic_launcher_background);
                    } else {
                        Glide.with(getContext()).load(user.getImageUrl()).into(image_profile);
                    }
                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });






    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));

        pd.setMessage("Uploading...");
        pd.show();

        if (imageUri != null){
            final  StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }

                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        // mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", ""+mUri);
                        mDatabaseReference.updateChildren(map);

                        pd.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        } else {
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();

            if (uploadTask != null && uploadTask.isInProgress()){
                Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
            } else {
                uploadImage();
            }
        }
    }




}
