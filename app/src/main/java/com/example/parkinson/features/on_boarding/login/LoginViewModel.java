package com.example.parkinson.features.on_boarding.login;

import androidx.annotation.NonNull;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
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
//        setAuthListener();
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String email) {
        this.password = password;
    }

//    public void onLoginClick(){
//        signIn(email,password);
//    }


//
//    //Auth
//
//    private FirebaseAuth Authentication_Server = FirebaseAuth.getInstance();;
//    private FirebaseAuth.AuthStateListener Auth_Listener;
//    FirebaseUser currentUser ;
//    FirebaseDatabase user_Database = FirebaseDatabase.getInstance();
//    DatabaseReference user_Info_Database_Table = user_Database.getReference("info");
//    Patient patient_Info;
//
//
//    private void setAuthListener()
//    {
//        Auth_Listener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                currentUser = firebaseAuth.getCurrentUser();
//
//                if(currentUser != null)
//                {
//                    signInListener();
//                }
//                else
//                {
//                    signOutListener();
//                }
//            }
//        };
//    }
//
//    private void signInListener()
//    {
//        //todo get quastion and info about the user
//
//        user_Info_Database_Table.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if(dataSnapshot.exists())
//                {
//                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
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
//
//
//    }
//
//    private void signOutListener()
//    {
//
//    }
//
//
//    public void addRegisterListenerAuthentication()
//    {
//        Authentication_Server.addAuthStateListener(Auth_Listener);
//    }
//
//    public void removeRegisterListenerAuthentication()
//    {
//        Authentication_Server.removeAuthStateListener(Auth_Listener);
//    }
//
//
//    public void signIn(String username, String password)
//    {
//        Authentication_Server.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                //if(task.isSuccessful())
//                //Snackbar.make(coordinatorLayout,"Sign in successful",Snackbar.LENGTH_SHORT).show();
//                // else
//                //Snackbar.make(coordinatorLayout,"Sign in failed",Snackbar.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//    public void signOu()
//    {
//        Authentication_Server.signOut();
//    }
//
//
//

}
