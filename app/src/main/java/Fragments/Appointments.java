package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Appointments.SecondAppointmentScreen;
import Appointments.AppointmentFirstScreen;

import com.emmocodeworks.healthcarefrontline.R;
import com.emmocodeworks.healthcarefrontline.SignUp;


public class Appointments extends Fragment {

    Button bookAnAppointment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_appointments, container, false);

        bookAnAppointment  = v.findViewById(R.id.bookanappointment_AppointmentFragment);
        bookAnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =   new Intent(getActivity(), AppointmentFirstScreen.class);
                startActivity(a);
            }
        });



        return v;
    }
}