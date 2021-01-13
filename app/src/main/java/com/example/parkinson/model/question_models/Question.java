package com.example.parkinson.model.question_models;

import com.example.parkinson.model.enums.EQuestionType;

public class Question {
    private  String m_Title;
    private EQuestionType type;

    public Question() {
    }

    public Question(String m_Title, EQuestionType type) {
        this.m_Title = m_Title;
        this.type = type;
    }


    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String title) {
        m_Title = title;
    }

    public EQuestionType getType() {
        return type;
    }

    public void setType(EQuestionType type) {
        this.type = type;
    }
}
