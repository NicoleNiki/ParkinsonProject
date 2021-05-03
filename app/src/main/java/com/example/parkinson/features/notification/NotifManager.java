package com.example.parkinson.features.notification;


import androidx.annotation.NonNull;

import com.example.parkinson.data.UserRepository;
import com.example.parkinson.data.enums.EDataSourceData;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.MedicineReport;
import com.example.parkinson.model.general_models.Time;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

public class NotifManager {

    enum EnotifId {
        H5(0),
        H5_30(1),
        H6(3),
        H6_30(4),
        H7(5),
        H7_30(6),
        H8(7),
        H8_30(8),
        H9(9),
        H9_30(10),
        H10(11),
        H10_30(12),
        Hg11(13),
        H11_30(14),
        H12(15),
        H12_30(16),
        H13(17),
        H13_30(18),
        H14(19),
        H14_30(20),
        H15(21),
        H15_30(22),
        H16(23),
        H16_30(24),
        H17(25),
        H17_30(26),
        H18(27),
        H18_30(28),
        H19(29),
        H19_30(30),
        H20(31),
        H20_30(32),
        H21(33),
        H21_30(34),
        H22(35),
        H22_30(36),
        H23(37),
        H23_30(38);

        int id;
        EnotifId(int i) {
            this.id = i;
        }
    }


    NotifMangerInteface listner;
    DatabaseReference userTable = FirebaseDatabase.getInstance().getReference("Patients");
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    static NotifManager notifMangaer = new NotifManager();

    public static NotifManager getNotifManager() {
        return notifMangaer;
    }
    public interface NotifMangerInteface {
        void postNotifaction(List<Medicine> medicines, int id, String notifHour);
        void closeNotif(int id);
    }

    public void getMedication(int notifId,String notifJHour) {
        this.getMedicationListNotif(getListner(notifId,notifJHour));
    }

    private ValueEventListener getListner(int notifId,String notifHour) {

        String id = notifHour;
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
                        listner.postNotifaction(list,notifId,id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public void report(int notifId,String notifHour) {
        getMedicationListNotif(getListnerForCheck(notifId,notifHour));
    }

    private ValueEventListener getListnerForCheck(int notifId,String notifHour) {
        final String id = notifHour;
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
                        pushReports(list,notifId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    private void pushReports(List<Medicine> list, int notifId) {
        List<MedicineReport> reportList = new ArrayList<>();
        for (Medicine med : list) {
            reportList.add(new MedicineReport(med.getId(), Calendar.getInstance().getTimeInMillis(), med.getName()));
        }
        pushMedicineReport(reportList, getFinishReportListner(notifId));
    }

    private ValueEventListener getFinishReportListner (int id)
    {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listner!=null)
                {
                    listner.closeNotif(id);
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

    public void getMedicationListNotif(ValueEventListener listener){
        userTable.child(currentUser.getUid()).child(EDataSourceData.MEDICINE_LIST.name).addValueEventListener(listener);
    }

    public void pushMedicineReport(List<MedicineReport> reportList, ValueEventListener listener) {
        String id = userTable.child(currentUser.getUid()).child("Medicine Reports").push().getKey();
        userTable.child(currentUser.getUid()).child("Medicine Reports").child(id).setValue(reportList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listner.closeNotif(1);
            }
        });
    }
}
