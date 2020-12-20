package com.example.parkinson.features.main;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.features.splash.SplashViewModel;
import com.example.parkinson.model.user_models.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

public class MainViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    MutableLiveData<Patient> patientEvent;
    MutableLiveData<NavigationEvent> navigationEvent;

    public enum NavigationEvent {
        OPEN_ON_BOARDING_ACTIVITY
    }


    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public MainViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
        patientEvent = new MutableLiveData<>();
        navigationEvent = new MutableLiveData<>();
    }

    public void initData() {
        dataRepository.getPatient(setPatientDataListener());
    }

    private ValueEventListener setPatientDataListener(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Patient patient_Info = snapshot.getValue(Patient.class);
                        patientEvent.postValue(patient_Info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

    public void logOut() {
        userRepository.logout();
        navigationEvent.postValue(NavigationEvent.OPEN_ON_BOARDING_ACTIVITY);
    }
}
