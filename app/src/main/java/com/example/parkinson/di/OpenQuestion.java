package com.example.parkinson.di;

public class OpenQuestion extends Question {
    private String m_Answer;

    public OpenQuestion(String title) {
        super(title);
    }

    public String getAnswer() {
        return m_Answer;
    }

    public void setAnswer(String answer) {
        m_Answer = answer;
    }
}
