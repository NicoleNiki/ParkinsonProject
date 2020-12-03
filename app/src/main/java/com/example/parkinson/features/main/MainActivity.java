package com.example.parkinson.features.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {



    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((ParkinsonApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel.asd();

    }
}