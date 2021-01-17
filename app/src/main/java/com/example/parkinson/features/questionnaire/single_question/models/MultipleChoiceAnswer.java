package com.example.parkinson.features.questionnaire.single_question.models;

import com.example.parkinson.model.enums.EChoiceType;
import com.example.parkinson.model.enums.EQuestionType;

public class MultipleChoiceAnswer {
    private String answer;
    private int position;
    private EChoiceType choiceType;
    private Boolean isSelected;

    public MultipleChoiceAnswer(String answer, int position, EChoiceType choiceType, Boolean isSelected) {
        this.answer = answer;
        this.position = position;
        this.choiceType = choiceType;
        this.isSelected = isSelected;
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

    public EChoiceType getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(EChoiceType choiceType) {
        this.choiceType = choiceType;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
