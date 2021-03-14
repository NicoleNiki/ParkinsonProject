package com.example.parkinson.features.servises;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.parkinson.data.UserRepository;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.Time;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotifManger {


    interface NotifMangerInteface {
        void postNotifaction(List<Medicine> medicines,int id);
    }

    private UserRepository userRepository;
    NotifMangerInteface listner;
    @Inject
    public NotifManger(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void getMedication(int notifId) {
        userRepository.getMedicationListNotif(getListner(notifId));
    }

    private ValueEventListener getListner(int notifId) {
        String id = notifId +"";
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    List<Medicine> list = new ArrayList<>();

                    for(DataSnapshot med : snapshot.getChildren())
                    {
                        Medicine tempMed = med.getValue(Medicine.class);
                        if (tempMed.getHoursArr()!= null) {
                            for (Time time : tempMed.getHoursArr()) {
                                if (time.toString().equals(id))
                                    list.add(tempMed);
                            }
                        }
                    }

                    if(listner!=null)
                    listner.postNotifaction(list,notifId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void report(int notifId) {

    }

    private ValueEventListener getListnerForCheck(int notifId) {
        String id = notifId +"";
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    List<Medicine> list = new ArrayList<>();

                    for(DataSnapshot med : snapshot.getChildren())
                    {
                        Medicine tempMed = med.getValue(Medicine.class);
                        if (tempMed.getHoursArr()!= null) {
                            for (Time time : tempMed.getHoursArr()) {
                                if (time.toString().equals(id))
                                    list.add(tempMed);
                            }
                        }
                    }

                    if(listner!=null)
                        listner.postNotifaction(list,notifId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void setListner(NotifMangerInteface listner) {
        this.listner = listner;
    }
}
