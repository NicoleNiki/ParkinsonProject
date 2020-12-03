package com.example.parkinson.features.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkinson.R;
import com.example.parkinson.features.on_boarding.OnBoardingViewModel;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
    }
}