package com.example.parkinson.data;

import com.example.parkinson.model.user_models.Patient;
import com.example.parkinson.network.AuthInterceptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;


public class DataRepository {
    private final AuthInterceptor authenticator;

    FirebaseDatabase user_Database = FirebaseDatabase.getInstance();
    DatabaseReference user_Info_Database_Table = user_Database.getReference("Users");

    @Inject
    public DataRepository(AuthInterceptor authenticator) {
        this.authenticator = authenticator;
    }

    public void getPatient(ValueEventListener listener){
        user_Info_Database_Table.child(authenticator.getCurrentUser().getUid()).addListenerForSingleValueEvent(listener);
    }





}
