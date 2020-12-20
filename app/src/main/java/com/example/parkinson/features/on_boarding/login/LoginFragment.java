package com.example.parkinson.features.on_boarding.login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.model.enums.EClinics;
import com.example.parkinson.model.general_models.Time;
import com.example.parkinson.model.user_models.Patient;

import java.sql.Date;
import java.util.Objects;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    LoginViewModel loginViewModel;

    EditText userName;
    EditText password;
    TextView login;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((ParkinsonApplication) requireActivity().getApplicationContext()).appComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUi(view);
        initObservers();
    }

    private void initUi(View view){
        userName = view.findViewById(R.id.loginFragUserName);
        password = view.findViewById(R.id.loginFragPassword);
        login = view.findViewById(R.id.loginFragLoginBtn);


        login.setOnClickListener(v -> loginViewModel.onLoginClick());

        userName.addTextChangedListener( new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                loginViewModel.setEmail(email);
            }
        });

        password.addTextChangedListener( new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                loginViewModel.setPassword(email);
            }
        });
    }

    private void initObservers(){

    }
}
