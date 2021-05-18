package com.example.parkinson.data;


import com.example.parkinson.data.enums.EDataSourceData;
import com.example.parkinson.network.DatabaseManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.example.parkinson.data.enums.EDataSourceData.MEDICINE_LIST;
import static com.example.parkinson.data.enums.EDataSourceData.QUESTIONNAIRE_FOLLOW_UP;
import static com.example.parkinson.data.enums.EDataSourceData.QUESTIONNAIRE_NEW_PATIENT;

@Singleton
public class DataRepository {
    DatabaseReference dataTable;// הטבלה הראשיתצ של הנתונים שלנו בפיירבייס מחזיקה את השאלון רשימת תרופות וכדומה

    @Inject
    public DataRepository(DatabaseManager databaseManager) {
        dataTable = databaseManager.getDatabase().getReference("Data");
    }

    /** Get new patient questionnaire - answered only once */
    public void getNewPatientQuestionnaire(ValueEventListener listener){
        dataTable.child(QUESTIONNAIRE_NEW_PATIENT.name).addListenerForSingleValueEvent(listener);
    }

    /** Get follow up questionnaire - answered after every meeting with the doctor */

    /*
    קבלת השאלון המעודכן והנכון שאותו אנו מציגים למטופל
     */
    public void getFollowUpQuestionnaire(ValueEventListener listener){
        dataTable.child(QUESTIONNAIRE_FOLLOW_UP.name).addListenerForSingleValueEvent(listener);
    }

    /** Get data of all medicine list - for taken medicine report */
    /*
    קבלת רשימת התרופות המלאה, על מנת שהיוזר יוכל לעדכן את תרופותיו
     */
    public void getMedicineList(ValueEventListener listener){
        dataTable.child(MEDICINE_LIST.name).addListenerForSingleValueEvent(listener);
    }

}
