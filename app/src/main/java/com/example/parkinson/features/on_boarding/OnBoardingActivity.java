package com.example.parkinson.features.on_boarding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;

import javax.inject.Inject;

public class OnBoardingActivity extends AppCompatActivity {

    @Inject
    OnBoardingViewModel onBoardingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((ParkinsonApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
    }
}