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
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        test();
        initObservers();
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

    private void test(){
        Question question;
        List<Question> qustionsList = new ArrayList<>();
        Questionnaire questionnaire;

        List<String> answer = new ArrayList<>();
        answer.add("This is the an answer");
        answer.add("This is the an answer");
        answer.add("This is the an answer");
        answer.add("This is the an answer");

        List<Integer> ansPositons = new ArrayList<>();
        ansPositons.add(20);
        ansPositons.add(50);
        ansPositons.add(60);
        ansPositons.add(1);


        question = new MultipleChoiceQuestion("This is a question", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.MultipleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);
        question = new OpenQuestion("Whats your name?",EQuestionType.OpenQuestion);
        qustionsList.add(question);
        question = new MultipleChoiceQuestion("whats your name", EQuestionType.MultipleChoiceQuestion,answer, ansPositons ,EChoiceType.MultipleChoice);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);
        question = new OpenQuestion("whats your name",EQuestionType.OpenQuestion,"the aswer is true");
        qustionsList.add(question);

        questionnaire = new Questionnaire(qustionsList, Calendar.getInstance().getTime());
        FirebaseDatabase questions_data_base = FirebaseDatabase.getInstance();
        DatabaseReference templates = questions_data_base.getReference("Data");
        templates.child(EDataSourceData.QUESTIONNAIRE_NEW_PATIENT.name).setValue(questionnaire);




    }

    /** Instantiate a ViewPager and a PagerAdapter.
     * Only when questionnaire already has value
     */
    private void initViewPager() {
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

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

