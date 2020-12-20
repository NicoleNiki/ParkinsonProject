package com.example.parkinson.features.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.features.on_boarding.OnBoardingViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    OnBoardingViewModel MainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((ParkinsonApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}