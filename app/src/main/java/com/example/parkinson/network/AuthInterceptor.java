package com.example.parkinson.network;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class AuthInterceptor {
    FirebaseAuth Authentication_Server;
    FirebaseUser currentUser;

    @Inject
    public AuthInterceptor() {
        Authentication_Server = FirebaseAuth.getInstance();
        currentUser = getAuthentication().getCurrentUser();
    }

    public FirebaseAuth getAuthentication() {
        return Authentication_Server;
    }

    public FirebaseUser getCurrentUser() { return currentUser; }

    public void updateCurrentUser(){
        currentUser = getAuthentication().getCurrentUser();
    }
}
