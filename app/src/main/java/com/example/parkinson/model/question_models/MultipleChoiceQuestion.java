package com.example.parkinson.model.question_models;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> m_Choices;

    public MultipleChoiceQuestion(String title, List<String> choices) {
        super(title);
        m_Choices = choices;
    }

    public List<String> getChoices() {
        return m_Choices;
    }

    public void setChoices(List<String> choices) {
        m_Choices = choices;
    }
}
