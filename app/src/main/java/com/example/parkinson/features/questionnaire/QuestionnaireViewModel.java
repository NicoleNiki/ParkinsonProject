package com.example.parkinson.features.questionnaire;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.QuestionnaireScope;
import com.example.parkinson.features.questionnaire.single_question.models.MultipleChoiceAnswer;
import com.example.parkinson.model.enums.EQuestionType;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@QuestionnaireScope
public class QuestionnaireViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    private Questionnaire questionnaire;

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public QuestionnaireViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void getQuestionnaireData(){
        ArrayList<Question> list = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        ans.add("This is an answer");
        ans.add("This is an answer");
        ans.add("This is an answer");
        ans.add("This is an answer");
        list.add(new MultipleChoiceQuestion("This is the first question", EQuestionType.MultipleChoiceQuestion,ans,true));
        list.add(new MultipleChoiceQuestion("This is the second question",EQuestionType.MultipleChoiceQuestion,ans,false));
        list.add(new MultipleChoiceQuestion("This is the third question",EQuestionType.MultipleChoiceQuestion,ans,false));
        list.add(new OpenQuestion("This is the forth question", EQuestionType.OpenQuestion));
        questionnaire = new Questionnaire(list);
    }

    public Question getDataByPosition(int position) {
        if(questionnaire.getQuestionList().size() > position){
            return questionnaire.getQuestionList().get(position);
        } else {
            return null;
        }
    }

    public void updateMultipleChoiceAnswer(int position, List<MultipleChoiceAnswer> chosenAnswers) {
        List<Integer> positionList = new ArrayList<>();
        for (MultipleChoiceAnswer answer :chosenAnswers){
            positionList.add(answer.getPosition());
        }
        //((MultipleChoiceQuestion)questionnaire.getQuestionList().get(position)).setChoices(positionList);
    }
}
