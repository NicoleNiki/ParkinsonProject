package com.example.parkinson.model.question_models;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire {
    private List<Question> questionList = new ArrayList<>();

    public Questionnaire() {
    }

    public Questionnaire(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
