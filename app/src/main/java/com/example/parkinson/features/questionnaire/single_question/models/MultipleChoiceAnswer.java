package com.example.parkinson.features.questionnaire.single_question.models;

public class MultipleChoiceAnswer {
    private String answer;
    private int position;

    public MultipleChoiceAnswer(String answer, int position) {
        this.answer = answer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
