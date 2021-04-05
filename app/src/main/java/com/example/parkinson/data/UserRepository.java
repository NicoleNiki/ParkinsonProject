package com.example.parkinson.data;


import com.example.parkinson.data.enums.EDataSourceData;
import com.example.parkinson.data.enums.EDataSourceUser;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.MedicineReport;
import com.example.parkinson.model.general_models.Report;
import com.example.parkinson.model.question_models.Questionnaire;
import com.example.parkinson.model.user_models.Patient;
import com.example.parkinson.network.Authentication;
import com.example.parkinson.network.DatabaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final Authentication authenticator;
    private final DatabaseReference userTable;


    @Inject
    public UserRepository(Authentication authenticator, DatabaseManager databaseManager) {
        this.authenticator = authenticator;
        userTable = databaseManager.getDatabase().getReference("Patients");
    }

    /** Get full patient details */
    public void getPatientDetails(ValueEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).addListenerForSingleValueEvent(listener);
    }

    public void postPatientDetails(Patient patient){
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).setValue(patient);
    }

    /** Get last answered questionnaire.
     * If null - new patient
     */
    public void getQuestionnaire(ValueEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.QUESTIONNAIRE.name).addListenerForSingleValueEvent(listener);
    }

    /** Get answered questionnaire and update server
     */
    public void postQuestionnaire(Questionnaire questionnaire) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.QUESTIONNAIRE.name).setValue(questionnaire);
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).child("hasUnansweredQuestionnaire").setValue(false);
    }

    public void getMedicationList(ChildEventListener listener){
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceData.MEDICINE_LIST.name).addChildEventListener(listener);
    }

    public void postMedication(Medicine medicine){
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).child("needToUpdateMedicine").setValue(false);
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.MEDICINE_LIST.name).child(medicine.getId()).setValue(medicine);
    }

    public void deleteMedication(Medicine medicine){
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.MEDICINE_LIST.name).child(medicine.getId()).setValue(null);
    }

    public void postReport(Report report) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.REPORTS.name).push().setValue(report);
    }

    public void getReportsList(ChildEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.REPORTS.name).addChildEventListener(listener);
    }


    /** Login to firebase with username and password **/
    public void login(String username, String password, OnCompleteListener listener) {
        if (!username.isEmpty() && !password.isEmpty()) {
            authenticator.getAuthentication().signInWithEmailAndPassword(username, password).addOnCompleteListener(listener);
        }
    }

    /** Logout of firebase **/
    public void logout() {
        authenticator.getAuthentication().signOut();
    }

    /** Get current stored user **/
    public FirebaseUser getCurrentUser() {
        return authenticator.getCurrentUser();
    }

    /** Fetch new user instance from firebase **/
    public void updateCurrentUser() {
        authenticator.updateCurrentUser();
    }

    public void getMedicationListNotif(ValueEventListener listener){
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceData.MEDICINE_LIST.name).addValueEventListener(listener);
    }

    public void pushMedicineReport(List<MedicineReport> reportList, ValueEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child("Medicine Reports").addValueEventListener(listener);
    }
}
