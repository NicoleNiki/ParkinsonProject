package com.example.parkinson.model.question_models;

import com.example.parkinson.model.enums.EQuestionType;

public class OpenQuestion extends Question {
    private String m_Answer;

    public OpenQuestion() {
        super("", null);
    }

    public OpenQuestion(String m_Title, EQuestionType type, String m_Answer) {
        super(m_Title, type);
        this.m_Answer = m_Answer;
    }
    public OpenQuestion(String m_Title, EQuestionType type) {
        super(m_Title, type);
        this.m_Answer = null;
    }
    public String getAnswer() {
        return m_Answer;
    }

    public void setAnswer(String answer) {
        m_Answer = answer;
    }
}
