package com.example.parkinson.features.questionnaire;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.data.enums.EDataSourceData;
import com.example.parkinson.di.QuestionnaireComponent;
import com.example.parkinson.features.questionnaire.single_question.SingleQuestionFragment;
import com.example.parkinson.model.enums.EChoiceType;
import com.example.parkinson.model.enums.EQuestionType;
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class QuestionnaireActivity extends FragmentActivity {

    public QuestionnaireComponent questionnaireComponent;

    @Inject
    QuestionnaireViewModel questionnaireViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        questionnaireComponent = ((ParkinsonApplication) getApplicationContext()).appComponent.questionnaireComponent().create();
        questionnaireComponent.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        questionnaireViewModel.getQuestionnaireData();
        //test();
        initObservers();
        //AddMedicane();
    }

    private void initObservers() {
        questionnaireViewModel.questionnaireDataEvent.observe(this, new Observer<Questionnaire>() {
            @Override
            public void onChanged(Questionnaire questionnaire) {
                if(questionnaire != null){
                    initViewPager();
                }
            }
        });
    }

    private void AddMedicane()
    {
        List<MedicationCategory> medicationCategoryList = new ArrayList<>();
        List<Medication> medicationList = new ArrayList<>();


        // medopar list
        medicationList.add(new Medication( "מדופאר 62.5" , 0,null));
        medicationList.add(new Medication( "מדופאר 50" , 0,null));
        medicationList.add(new Medication( "מדופאר 125" , 0,null));
        medicationList.add(new Medication( "מדופאר 12.5" , 0,null));
        medicationList.add(new Medication( "לבופר פלוס 125" , 0,null));
        medicationList.add(new Medication( "מדופאר 250" , 0,null));
        medicationList.add(new Medication( "מדופאר 200" , 0,null));
        medicationList.add(new Medication( "מדופאר 50" , 0,null));
        medicationList.add(new Medication( "לבופר פלוס 250" , 0,null));
        medicationList.add(new Medication( "מדופאר 60.5 בתרחיף" , 0,null));
        medicationList.add(new Medication( "מדופאר 50 בתרחיף" , 0,null));
        medicationList.add(new Medication( "מדופאר 12.5 בתרחיף" , 0,null));
        medicationList.add(new Medication( "מדופאר 125 בתרחיף" , 0,null));
        medicationList.add(new Medication( "מדופאר 100 בתרחיף" , 0,null));
        medicationList.add(new Medication( "מדופאר 25 בתרחיף" , 0,null));
        medicationList.add(new Medication( "מדופאר CR" , 0,null));
        medicationCategoryList.add(new MedicationCategory("מדופאר",medicationList));



        // sinmat list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication( " 50 סינמט LS" , 0,null));
        medicationList.add(new Medication( "12.5 סינמט LS" , 0,null));
        medicationList.add(new Medication( "סינמט 110" , 0,null));
        medicationList.add(new Medication( "סינמט 100" , 0,null));
        medicationList.add(new Medication( "סינמט 10" , 0,null));
        medicationList.add(new Medication( "סינמט 275" , 0,null));
        medicationList.add(new Medication( "סינמט 250" , 0,null));
        medicationList.add(new Medication( "סינמט 25" , 0,null));
        medicationList.add(new Medication( "דופיקר" , 0,null));
        medicationList.add(new Medication( "סינמט פלוס 100" , 0,null));
        medicationList.add(new Medication( "סינמט פלוס 25" , 0,null));
        medicationList.add(new Medication( "חצי סינמט CR 100" , 0,null));
        medicationList.add(new Medication( "חצי סינמט CR 25" , 0,null));
        medicationList.add(new Medication( "200 סינמט CR" , 0,null));
        medicationList.add(new Medication( "50 סינמט CR" , 0,null));
        medicationCategoryList.add(new MedicationCategory("סינמט",medicationList));

        // Dopamine Agonists list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication( "ברומוקריפטין" , 0,null));
        medicationList.add(new Medication( "פרלודל, פרילק" , 0,null));
        medicationList.add(new Medication( "קברגולין" , 0,null));
        medicationList.add(new Medication( "קבסר" , 0,null));
        medicationList.add(new Medication( "ליזוריד" , 0,null));
        medicationList.add(new Medication( "דופרגין" , 0,null));
        medicationList.add(new Medication( "פרגוליד" , 0,null));
        medicationList.add(new Medication( "רופינרול" , 0,null));
        medicationList.add(new Medication( "רקוויפ" , 0,null));
        medicationList.add(new Medication( "פרמיפקסול" , 0,null));

        medicationCategoryList.add(new MedicationCategory("דופמין אגוניסטים",medicationList));

        // Amantadine list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication( "אדמנטנאמין סולפט" , 0,null));
        medicationList.add(new Medication( "א-פרקין" , 0,null));
        medicationList.add(new Medication( "אמנטדין הידרוכלוריד" , 0,null));
        medicationList.add(new Medication( "פריטרל" , 0,null));

        medicationCategoryList.add(new MedicationCategory("אמנטדין",medicationList));

        // Anticholinergics list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication( "טריהקסיפנידיל" , 0,null));
        medicationList.add(new Medication( "ארטן" , 0,null));
        medicationList.add(new Medication( "פרטן" , 0,null));
        medicationList.add(new Medication( "בנזטרופין" , 0,null));
        medicationList.add(new Medication( "קוגנטין" , 0,null));
        medicationList.add(new Medication( "ביפרידן" , 0,null));
        medicationList.add(new Medication( "דהקינט" , 0,null));
        medicationList.add(new Medication( "פרוציקלידין" , 0,null));
        medicationList.add(new Medication( "קמדרין" , 0,null));
        medicationList.add(new Medication( "אורפנדרין" , 0,null));
        medicationList.add(new Medication( "דיסיפל" , 0,null));
        medicationCategoryList.add(new MedicationCategory("אנטי כולינרגיות",medicationList));

        // COMT  list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication( "אנטקפון" , 0,null));
        medicationList.add(new Medication( "קומטן" , 0,null));
        medicationList.add(new Medication( "טולקפון" , 0,null));
        medicationList.add(new Medication( "סלג'ילין" , 0,null));
        medicationList.add(new Medication( "יומקס" , 0,null));

        medicationCategoryList.add(new MedicationCategory("מעכבי האנזים",medicationList));




        FirebaseDatabase MEDICATION_data_base = FirebaseDatabase.getInstance();
        DatabaseReference templates = MEDICATION_data_base.getReference("Data");
        templates.child(EDataSourceData.MEDICINE_LIST.name).setValue(medicationCategoryList);
    }
    private void test(){
        Question question;
        List<Question> qustionsList = new ArrayList<>();
        Questionnaire questionnaire;
        List<Integer> ansPositons = new ArrayList<>();
        List<String> answer = new ArrayList<>();

        answer= new ArrayList<>();
        answer.add("כחודש");
        answer.add("כחצי שנה");
        answer.add("כשנה");
        answer.add("כשנתיים");
        question = new MultipleChoiceQuestion("לפני כמה זמן אובחנת במחלה ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.SingleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);

        answer= new ArrayList<>();
        answer.add("כחודש");
        answer.add("כחצי שנה");
        answer.add("כשנה");
        answer.add("כשנתיים");
        question = new MultipleChoiceQuestion("לפני כמה זמן החל התסמין הראשון ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.SingleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);

        answer= new ArrayList<>();
        answer.add("רגל");
        answer.add("יד");
        answer.add("רעד");
        answer.add("נוקשות");
        answer.add("נפילות");
        answer.add("איטיות");
        question = new MultipleChoiceQuestion("כיצד המחלה באה לידי ביטוי ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.MultipleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);

        answer= new ArrayList<>();
        answer.add("ימין");
        answer.add("שמאל");
        question = new MultipleChoiceQuestion("באיזה צד החלו התסמינים ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.SingleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);

        answer= new ArrayList<>();
        answer.add("הזיות ראיה");
        answer.add("הקאות");
        answer.add("בחילות");
        answer.add("כאבי בטן");
        answer.add("שינה פתאומית");
        answer.add("התנהגות ללא עקבות");
        question = new MultipleChoiceQuestion("מהן תופעות הלוואי שחווית ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.MultipleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);


        answer= new ArrayList<>();
        answer.add("שבוע");
        answer.add("חודש");
        answer.add("חצי שנה");
        answer.add("שנה");
        answer.add("שנתיים אחרונות");
        question = new MultipleChoiceQuestion("מתי בוצע שינוי תרופתי אחרון ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.SingleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);


        /*question = new MultipleChoiceQuestion("כיצד המחלה באה לידי ביטוי ?", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.MultipleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);
        question = new OpenQuestion("Whats your name?",EQuestionType.OpenQuestion);
        qustionsList.add(question);
        question = new MultipleChoiceQuestion("whats your name", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.MultipleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);
        question = new OpenQuestion("whats your name",EQuestionType.OpenQuestion,"the aswer is true");
        qustionsList.add(question);*/

        questionnaire = new Questionnaire(qustionsList, Calendar.getInstance().getTime());
        FirebaseDatabase questions_data_base = FirebaseDatabase.getInstance();
        DatabaseReference templates = questions_data_base.getReference("Data");
        templates.child(EDataSourceData.QUESTIONNAIRE_FOLLOW_UP.name).setValue(questionnaire);




    }

    /** Instantiate a ViewPager and a PagerAdapter.
     * Only when questionnaire already has value
     */
    private void initViewPager() {
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    //TODO intizlie to the list size
    /** The number of pages (wizard steps) to show in this demo.**/
    private static final int NUM_PAGES = 4;

    /** The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps. **/
    private ViewPager2 viewPager;

    /** The pager adapter, which provides the pages to the view pager widget. **/
    private FragmentStateAdapter pagerAdapter;


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /** A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in sequence. */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new SingleQuestionFragment(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}

