package com.example.parkinson.features.questionnaire;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.QuestionnaireScope;
import com.example.parkinson.features.questionnaire.single_question.models.MultipleChoiceAnswer;
import com.example.parkinson.model.enums.EQuestionType;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@QuestionnaireScope
public class QuestionnaireViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    private Questionnaire questionnaire;
    MutableLiveData<Questionnaire> questionnaireDataEvent = new MutableLiveData<>();

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public QuestionnaireViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void getQuestionnaireData() {
        dataRepository.getFollowUpQuestionnaire(setQuestionnaireListener());
    }

    public Question getDataByPosition(int position) {
        if (questionnaire.getQuestionList().size() > position) {
            return questionnaire.getQuestionList().get(position);
        } else {
            return null;
        }
    }

    public void updateMultipleChoiceAnswer(int position, List<MultipleChoiceAnswer> chosenAnswers) {
        List<Integer> positionList = new ArrayList<>();
        for (MultipleChoiceAnswer answer : chosenAnswers) {
            positionList.add(answer.getPosition());
        }
        ((MultipleChoiceQuestion) questionnaire.getQuestionList().get(position)).setAnsPositions(positionList);
    }

    private ValueEventListener setQuestionnaireListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Question> questionList = new ArrayList<>();
                        for (DataSnapshot questionnaire : dataSnapshot.child("questionList").getChildren()) {

                            Question question = questionnaire.getValue(Question.class);

                            if (question.getType().equals(EQuestionType.MultipleChoiceQuestion)) {
                                question = questionnaire.getValue(MultipleChoiceQuestion.class);

                            } else {

                                if (question.getType().equals(EQuestionType.OpenQuestion)) {
                                    question = questionnaire.getValue(OpenQuestion.class);
                                }
                            }
                            questionList.add(question);
                        }

                    questionnaire = new Questionnaire(questionList,null);
                    questionnaireDataEvent.setValue(questionnaire);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

}
