package com.example.parkinson.features.on_boarding.login;

import android.util.Log;

import com.example.parkinson.data.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

public class LoginViewModel {

    private final UserRepository userRepository;

    String email;
    String password;

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void onLoginClick() {
        userRepository.login(email,password, setLoginListener());
    }

    public OnCompleteListener setLoginListener(){
        return (OnCompleteListener<AuthResult>) task -> {
            if (task.isSuccessful()) {
                Log.d("wowLoginVM", "sign in successful");
                userRepository.updateCurrentUser();
            }  else{
                Log.d("wowLoginVM", "sign in Not successful");
            }
        };
    }








//    private void setAuthListener() {
//        Auth_Listener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                currentUser = firebaseAuth.getCurrentUser();
//
//                if (currentUser != null) {
//                    loadData();
//                } else {
//                    clearData();
//                }
//            }
//        };
//    }


    //main activity
//    private void loadData() {
//        //todo get quastion and info about the user
//
//        user_Info_Database_Table.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        patient_Info = snapshot.getValue(Patient.class);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        Log.d("wowLoginVM", "currentUser" + currentUser.getEmail());
//
//    }

    private void clearData() {

    }


//    public void addRegisterListenerAuthentication() {
//        Authentication_Server.addAuthStateListener(Auth_Listener);
//    }
//
//    public void removeRegisterListenerAuthentication() {
//        Authentication_Server.removeAuthStateListener(Auth_Listener);
//    }






}
