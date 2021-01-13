package com.example.parkinson.model.question_models;

import com.example.parkinson.model.enums.EQuestionType;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> m_Choices;
    private List <Integer> ansPositions = new ArrayList<>();
    private boolean isSingleChoice;

    public MultipleChoiceQuestion() {
        super("", null);
        isSingleChoice = true ;
    }


    public MultipleChoiceQuestion(String m_Title, EQuestionType type, List<String> m_Choices, Boolean isSingleChoice) {
        super(m_Title, type);
        this.m_Choices = m_Choices;
        this.ansPositions = new ArrayList<>();
        this.isSingleChoice = isSingleChoice;
    }

    public List <Integer> getAnsPositions() {
        return ansPositions;
    }

    public void setAnsPositions(List <Integer> ansPosition) {
        this.ansPositions = ansPosition;
    }

    public List<String> getChoices() {
        return m_Choices;
    }

    public void setChoices(List<String> choices) {
        m_Choices = choices;
    }

    public boolean isSingleChoice() {
        return isSingleChoice;
    }

    public void setSingleChoice(boolean singleChoice) {
        isSingleChoice = singleChoice;
    }
}
