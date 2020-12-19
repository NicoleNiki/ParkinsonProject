package com.example.parkinson.model.question_models;

public class Question {
    private  String m_Title;

    public Question(String title) {
        m_Title = title;
    }

    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String title) {
        m_Title = title;
    }
}
