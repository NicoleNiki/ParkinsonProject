package com.example.parkinson.features.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;
import com.example.parkinson.model.user_models.Patient;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    MainViewModel mainViewModel;

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    TextView userName;
    TextView email;
    Button logOutBtn;

    @Override
    public void onAttach(@NonNull Context context) {
        ((MainActivity) getActivity()).mainComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel.initData();
        initUi(view);
        initObservers();
    }

    private void initUi(View view) {
        userName = view.findViewById(R.id.mainFragUserName);
        email = view.findViewById(R.id.mainFragEmail);
        logOutBtn = view.findViewById(R.id.mainFragLogoutBtn);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.logOut();
            }
        });

    }

    private void initObservers() {
        mainViewModel.patientEvent.observe(getViewLifecycleOwner(), new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                userName.setText(patient.getName());
                email.setText(patient.getEmail());
            }
        });

    }
}
