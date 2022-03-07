package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emmocodeworks.healthcarefrontline.R;
import com.google.android.material.card.MaterialCardView;

import Checker.SecondCheckerScreen;

public class Diagnosis extends Fragment {

    MaterialCardView diagnosisCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_diagnosis, container, false);

        diagnosisCardView = v.findViewById(R.id.diagnosecardview);
        diagnosisCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , SecondCheckerScreen.class);
                startActivity(i);
            }
        });

        return  v;
    }
}