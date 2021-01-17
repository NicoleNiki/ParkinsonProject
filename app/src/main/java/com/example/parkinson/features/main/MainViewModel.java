package com.example.parkinson.features.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.MainScope;
import com.example.parkinson.model.enums.EQuestionType;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;
import com.example.parkinson.model.user_models.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


@MainScope
public class MainViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    MutableLiveData<Patient> patientEvent;
    MutableLiveData<Boolean> isLoading;

    /**
     * For navigation between fragments in main activity using NavigationComponent
     **/
    MutableLiveData<NavDirections> openFragmentEvent;

    /**
     * For navigation between activities
     **/
    MutableLiveData<OpenActivityEvent> openActivityEvent;

    public enum OpenActivityEvent {
        OPEN_ON_BOARDING_ACTIVITY
    }

    /**
     * Inject tells Dagger how to create instances of MainViewModel
     **/
    @Inject
    public MainViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
        patientEvent = new MutableLiveData<>();
        openFragmentEvent = new MutableLiveData<>();
        openActivityEvent = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    /** Getting PatientDetails from firebase**/
    public void initData() {
        isLoading.setValue(true);
        userRepository.getPatientDetails(setPatientDataListener());
    }

    /** Posting PatientDetails data to observer **/
    private ValueEventListener setPatientDataListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Patient patient_Info = dataSnapshot.getValue(Patient.class);
                    patientEvent.postValue(patient_Info);
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isLoading.setValue(false);
            }
        };
    }

    /** Logging out of current user and back to on boarding activity **/
    public void logOut() {
        userRepository.logout();
        openActivityEvent.postValue(OpenActivityEvent.OPEN_ON_BOARDING_ACTIVITY);
    }
}
