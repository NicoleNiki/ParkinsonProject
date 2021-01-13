package com.example.parkinson.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.di.MainComponent;
import com.example.parkinson.di.OnBoardingComponent;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;
import com.example.parkinson.features.on_boarding.OnBoardingViewModel;
import com.example.parkinson.model.enums.EClinics;
import com.example.parkinson.model.enums.EQuestionType;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;
import com.example.parkinson.model.user_models.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public MainComponent mainComponent;
    Question question;
    List<Question> qustionsList = new ArrayList<>();
    Questionnaire questionnaire;
    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent = ((ParkinsonApplication) getApplicationContext()).appComponent.mainComponent().create();
        mainComponent.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObservers();
        //TODO delete after check


        /*List<String> answer = new ArrayList<>();
        answer.add("222222");
        answer.add("22");
        answer.add("8");
        answer.add("5");

        List<Integer> ansPositons = new ArrayList<>();
        ansPositons.add(20);
        ansPositons.add(50);
        ansPositons.add(60);
        ansPositons.add(1);


        question = new MultipleChoiceQuestion("whats your name",EQuestionType.MultipleChoiceQuestion,answer);
        ((MultipleChoiceQuestion)question).setAnsPositions(ansPositons);
        qustionsList.add(question);
        question = new OpenQuestion("whats your name",EQuestionType.OpenQuestion);
        qustionsList.add(question);
        question = new OpenQuestion("whats your name",EQuestionType.OpenQuestion,"the aswer is true");
        qustionsList.add(question);

        //questionnaire = new Questionnaire(qustionsList);
        FirebaseDatabase questions_data_base = FirebaseDatabase.getInstance();
        DatabaseReference user_Info_Database_Table = questions_data_base.getReference("questionnaire");
        user_Info_Database_Table.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(qustionsList);
/*
        user_Info_Database_Table.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()) {
                    qustionsList.clear();
                   // questionnaire = dataSnapshot.getValue(Questionnaire.class);
                   // qustionsList = questionnaire.getQuestionList();
                    //Toast.makeText(MainActivity.this,dataSnapshot, Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        question = snapshot.getValue(Question.class);
                        if (question.getType().equals(EQuestionType.MultipleChoiceQuestion)) {
                            question = snapshot.getValue(MultipleChoiceQuestion.class);
                        } else {
                            question = snapshot.getValue(OpenQuestion.class);
                        }

                        qustionsList.add(question);
                    }
                    //missions.add(mission);
                    //Toast.makeText(MainActivity.this, question.getClass().toString(), Toast.LENGTH_SHORT).show();

                    //MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion)question;

                    //Toast.makeText(MainActivity.this, multipleChoiceQuestion.getAnsPosition(), Toast.LENGTH_SHORT).show();

                    for (Question tenopQuestion : qustionsList) {
                        if (tenopQuestion instanceof MultipleChoiceQuestion) {
                            //Toast.makeText(MainActivity.this, "hahahahahaha", Toast.LENGTH_SHORT).show();
                            //MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
                            Toast.makeText(MainActivity.this, ((MultipleChoiceQuestion)tenopQuestion).getChoices().toString()+"", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, ((OpenQuestion)tenopQuestion).getAnswer()+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

*/

    }

    private void initObservers(){
        mainViewModel.navigationEvent.observe(this, new Observer<MainViewModel.NavigationEvent>() {
            @Override
            public void onChanged(MainViewModel.NavigationEvent navigationEvent) {
                switch (navigationEvent){
                    case OPEN_ON_BOARDING_ACTIVITY:
                        openOnBoarding();
                        break;
                }
            }
        });
    }

    private void openOnBoarding(){
        Intent intent = new Intent(this, OnBoardingActivity.class);
        startActivity(intent);
        finish();
    }
}