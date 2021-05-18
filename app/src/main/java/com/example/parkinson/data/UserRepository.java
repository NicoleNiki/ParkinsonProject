package com.example.parkinson.data;


import androidx.annotation.NonNull;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final Authentication authenticator;// אובייקט המחזיק את הfirbase authenicaton ויודע מהו היוזר הנוכחי שמחובר
    private final DatabaseReference userTable;// קישור לבסיס הנתונים בfirebase מקושר ישירות לסקיישן של patients

    Patient currentPatientDetails;

    @Inject
    public UserRepository(Authentication authenticator, DatabaseManager databaseManager) {
        this.authenticator = authenticator;
        userTable = databaseManager.getDatabase().getReference("Patients");
    }

    /**
     * Get full patient details
     * get ful deatials of the patients in evey place in the project without asking for the information from the database
     */
    public Patient getPatientDetails() {
        return currentPatientDetails;
    }

    /*
      ניתן לראות כי כאן אנו מעדכנים ישירות את הפרטים של המשתמש , פונקציה זו לא בשימוש אצלינו בפיירביס מכייוון שהפרטים מגיעים מהצוות השני של הרופא
     */
    public void postPatientDetails(Patient patient) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).setValue(patient);
    }

    /*
    כאשר היוזר מתחבר מחדש אנו מעדכנים את הטוקן שלו על מנת שיוכל לקבל
    נוטיפקציות על שאלונים חדשים זמינים וכדומה
    ניתן לראות כי אנו מעדכנים זאת בסקיישין הספציפי של אותו היוזר
     */
    public void updateUserToken(String token) {
        currentPatientDetails.setToken(token);
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).setValue(currentPatientDetails);
    }

    /**
     * Get last answered questionnaire.
     * If null - new patient
     */
    /*
    בקשה לשאלון , אם המטופל אכן ענה על שאלון
    אנו נקבל את האלון שהוא ענה עליו עם התשובות שלו
     */
    public void getQuestionnaire(ValueEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.QUESTIONNAIRE.name).addListenerForSingleValueEvent(listener);
    }

    /**
     * Get answered questionnaire and update server
     */

    /*
   כאשר המטופל ע ונה על שאלון אנו מעלים את השאלון ישירות לשרת
   ניתן לראות כי אנו מעדכנים גם שדה של האם הוא צריך לענות על שאלון לשלילי
   וזאת כדי שנדע אם הוא צריך לענות על שאלון חדש או לא
     */
    public void postQuestionnaire(Questionnaire questionnaire) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.QUESTIONNAIRE.name).setValue(questionnaire);
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).child("hasUnansweredQuestionnaire").setValue(false);
    }

    /*
    לכל מטופל יש רשימת תרופות אישית ועל מנת שנדע לעדכן אותה
    אנו מקבלים את הרשימה שלו האישית
    ניתן לראות כי אנו פונים לטבלה של הpatients ולאיזור האישי של אותו היוזר על מנת לקבל את התרופות שלו
     */
    public void getMedicationList(ChildEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceData.MEDICINE_LIST.name).addChildEventListener(listener);
    }

/*
המטופל יכול לעדכן ולהויסף תרופות חדשות לרשימה שלו
וכאן אנו מוסיפים את התרופה לרשימה
אנו מוסיפים את התרופה לרשימה ע"י מזהה יחודי של הID של התרופה
וזאת על מנת שנוכל לבצע גישה מהירה לתרופה בעת שנרצה לעדכנה
 */
    public void postMedication(Medicine medicine) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).child("needToUpdateMedicine").setValue(false);
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.MEDICINE_LIST.name).child(medicine.getId()).setValue(medicine);
    }
/*
אנו מוחקים את התרופה מהרשימה
ניתן לראות את הגישה הספצפית לתרופה ע"י הID היחודי של התרופה
 */
    public void deleteMedication(Medicine medicine) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.MEDICINE_LIST.name).child(medicine.getId()).setValue(null);
    }

    /*
    כאשר המטופל מדווח על מצבו אנו מעלים דיווח מלא על כך
     */
    public void postReport(Report report) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.REPORTS.name).push().setValue(report);
    }
/*
קבלת רשימת הדיווחים (באפליקציה שלנו לא בא לידי ביטוי)
 */
    public void getReportsList(ChildEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.REPORTS.name).addChildEventListener(listener);
    }


    /**
     * Login to firebase with username and password
     **/

    public void login(String username, String password, OnCompleteListener listener) {
        if (!username.isEmpty() && !password.isEmpty()) {
            authenticator.getAuthentication().signInWithEmailAndPassword(username, password).addOnCompleteListener(listener);
        }
    }

    /**
     * Get current stored user
     **/
    public FirebaseUser getCurrentUser() {
        return authenticator.getCurrentUser();
    }

    /**
     * Fetch new user instance from firebase
     **/
    public void updateCurrentUser() {
        authenticator.updateCurrentUser();
    }
/*
קבלת רשימת התרופות על מנת
לבדוק איזו תרופה צריכה לקפוץ בנוטיפקציה
 */
    public void getMedicationListNotif(ValueEventListener listener){
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceData.MEDICINE_LIST.name).addValueEventListener(listener);
    }

    public void pushMedicineReport(List<MedicineReport> reportList, ValueEventListener listener) {
        userTable.child(authenticator.getCurrentUser().getUid()).child("Medicine Reports").addValueEventListener(listener);
    }

    InitUserListener initUserListener;// ליסנר שנדע בדרגע שהמידע על היוזר הסתיים
    public interface InitUserListener{
        void finishedLoadingUser();
    }

    UpdateUserListener updateUserListener;// ליסנר שיודע לעדכן כאשר היוזר עצמו התעדכן , והמידע השתנה
    public interface UpdateUserListener{
        void updateUserListener(Patient currentPatientDetails);
    }

    public void initUserDetails(InitUserListener initUserListener){
        this.initUserListener = initUserListener;
        fetchPatientDetails();
    }

    public void initUpdateUserListener(UpdateUserListener updateUserListener){
        this.updateUserListener = updateUserListener;
    }
/*
ניתן לראות כי כאן אנו מושכים את המידע על היוזר ומעדכנים לאחר מכן ....
 */
    public void fetchPatientDetails() {
        userTable.child(authenticator.getCurrentUser().getUid()).child(EDataSourceUser.USER_DETAILS.name).addValueEventListener(setPatientDataListener());
    }

    /** Logout of firebase **/
    public void logout() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(getCurrentUser().getUid());
        String uid = (authenticator.getCurrentUser().getUid());
        userTable.child(uid).child(EDataSourceUser.USER_DETAILS.name).child("token").setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                authenticator.getAuthentication().signOut();
                currentPatientDetails = null;
            }
        });
    }

    /**
     * Posting PatientDetails data to observer
     **/

    /*
    ניתן לראות כי כאן ישנו ליסנטר ב רגע שבוצע שינוי בנתונים של היוזר
    ובאמצעות הליסנרים אנו מעדכנים את הנתונים בלייב
     */
    private ValueEventListener setPatientDataListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentPatientDetails = dataSnapshot.getValue(Patient.class);
                    if(initUserListener != null){
                        initUserListener.finishedLoadingUser();
                    }
                    if(updateUserListener != null){
                        updateUserListener.updateUserListener(currentPatientDetails);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }
}
