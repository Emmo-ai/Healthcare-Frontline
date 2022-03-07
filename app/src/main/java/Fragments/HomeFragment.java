package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emmocodeworks.healthcarefrontline.R;
import com.google.android.material.card.MaterialCardView;

import Checker.SymptomCheckerFirstScreen;
import HomeFragmentActivities.ConsultADoctor;
import HomeFragmentActivities.CovidActivity;
import HomeFragmentActivities.DiagnoseHomeActivity;

import Appointments.SecondAppointmentScreen;
import Appointments.AppointmentFirstScreen;


public class HomeFragment extends Fragment {

     MaterialCardView consultDoctor, Diagnose, covid_19;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        consultDoctor = v.findViewById(R.id.consultadoctor);
        Diagnose = v.findViewById(R.id.diagnose);
        covid_19 = v.findViewById(R.id.covid19);

        consultDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AppointmentFirstScreen.class);
                startActivity(i);
            }
        });

        Diagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SymptomCheckerFirstScreen.class);
                startActivity(i);
            }
        });


        covid_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CovidActivity.class);
                startActivity(i);
            }
        });




        return v;
    }
}