package com.example.parkinson.data;


import com.example.parkinson.network.AuthInterceptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;


import javax.inject.Inject;

public class UserRepository {
    private final AuthInterceptor authenticator;


    @Inject
    public UserRepository(AuthInterceptor authenticator) {
        this.authenticator = authenticator;
    }

    public FirebaseUser getCurrentUser() {
        return authenticator.getCurrentUser();
    }

    public void login(String username, String password, OnCompleteListener listener) {
        if (!username.isEmpty() && !password.isEmpty()) {
            authenticator.getAuthentication().signInWithEmailAndPassword(username, password).addOnCompleteListener(listener);
        }
    }

    public void updateCurrentUser(){
        authenticator.updateCurrentUser();
    }

    public void logout() {
        authenticator.getAuthentication().signOut();
    }

}
