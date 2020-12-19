package com.example.parkinson.features.on_boarding.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parkinson.R;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    LoginViewModel loginViewModel;

    EditText userName;
    EditText password;
    EditText login;

    public LoginFragment() {
        super(R.layout.fragment_login);
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
