package com.example.parkinson.data;


import com.example.parkinson.network.Authentication;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataRepository {
    private final Authentication authenticator;

    FirebaseDatabase user_Database = FirebaseDatabase.getInstance();
    DatabaseReference user_Info_Database_Table = user_Database.getReference("Users");

    @Inject
    public DataRepository(Authentication authenticator) {
        this.authenticator = authenticator;
    }

    public void getPatient(ValueEventListener listener){
        user_Info_Database_Table.child(authenticator.getCurrentUser().getUid()).addListenerForSingleValueEvent(listener);
    }





}
