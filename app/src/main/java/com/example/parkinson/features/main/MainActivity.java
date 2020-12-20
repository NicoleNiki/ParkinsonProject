package com.example.parkinson.features.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;
import com.example.parkinson.features.on_boarding.OnBoardingViewModel;
import com.example.parkinson.model.enums.EClinics;
import com.example.parkinson.model.user_models.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((ParkinsonApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObservers();
//        FirebaseAuth Authentication_Server = FirebaseAuth.getInstance();
//        FirebaseDatabase user_Database = FirebaseDatabase.getInstance();
//        DatabaseReference user_Info_Database_Table = user_Database.getReference("Users");
//        Patient patient = new Patient("Noam","2","ISRAEL","05266","noam@gmail.com","supe clalit",5,new Date(1,2,3),EClinics.Clalit);
//        user_Info_Database_Table.child(Authentication_Server.getCurrentUser().getUid()).setValue(patient);

    }

    private void initObservers(){
        mainViewModel.navigationEvent.observe(this, new Observer<MainViewModel.NavigationEvent>() {
            @Override
            public void onChanged(MainViewModel.NavigationEvent navigationEvent) {
                switch (navigationEvent){
                    case OPEN_ON_BOARDING_ACTIVITY:
                        openOnBoarding();
                        break;
                }
            }
        });
    }

    private void openOnBoarding(){
        Intent intent = new Intent(this, OnBoardingActivity.class);
        startActivity(intent);
        finish();
    }
}