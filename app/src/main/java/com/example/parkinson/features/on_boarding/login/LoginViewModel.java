package com.example.parkinson.features.on_boarding.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.model.enums.EClinics;
import com.example.parkinson.model.user_models.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

public class LoginViewModel {

    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    String email;
    String password;

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public LoginViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
        setAuthListener();
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void onLoginClick() {
        signIn(email, password);
    }


    //Auth

    private FirebaseAuth Authentication_Server = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener Auth_Listener;
    FirebaseUser currentUser;
    FirebaseDatabase user_Database = FirebaseDatabase.getInstance();
    DatabaseReference user_Info_Database_Table = user_Database.getReference("Users");
    Patient patient_Info;


    private void setAuthListener() {
        Auth_Listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null) {
                    loadData();
                } else {
                    clearData();
                }
            }
        };
    }

    private void loadData() {
        //todo get quastion and info about the user

        user_Info_Database_Table.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        patient_Info = snapshot.getValue(Patient.class);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("wowLoginVM", "currentUser" + currentUser.getEmail());

    }

    private void clearData() {

    }


    public void addRegisterListenerAuthentication() {
        Authentication_Server.addAuthStateListener(Auth_Listener);
    }

    public void removeRegisterListenerAuthentication() {
        Authentication_Server.removeAuthStateListener(Auth_Listener);
    }


    public void signIn(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            Authentication_Server.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Log.d("wowLoginVM", "sign in successful");

                        Patient patient = new Patient("Nicole", "1", "Israel", "05222222222", "nicole@gmail.com", "Super Macabi",
                                102, null, EClinics.Maccabi);
                        user_Info_Database_Table.child(currentUser.getUid()).setValue(patient);
                    }  else
                        Log.d("wowLoginVM", "sign in NOT successful");

                }
            });
        }
    }

    public void signOut() {
        Authentication_Server.signOut();
    }


}
