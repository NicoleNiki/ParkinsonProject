package com.example.parkinson.model.question_models;

import com.example.parkinson.model.enums.EQuestionType;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> m_Choices;
    private List <Integer> ansPositions = new ArrayList<>();
    private boolean isOneChoise;
    public MultipleChoiceQuestion() {
        super("", null);
        isOneChoise = true ;
    }


    public MultipleChoiceQuestion(String m_Title, EQuestionType type, List<String> m_Choices) {
        super(m_Title, type);
        this.m_Choices = m_Choices;
        this.ansPositions = new ArrayList<>();
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

    public boolean isOneChoise() {
        return isOneChoise;
    }

    public void setOneChoise(boolean oneChoise) {
        isOneChoise = oneChoise;
    }
}
