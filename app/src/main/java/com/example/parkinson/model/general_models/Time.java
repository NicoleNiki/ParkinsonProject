package com.example.parkinson.model.general_models;

public class Time {
    private int m_Minutes, m_Hour;

    public Time(int m_Minutes, int m_Hour) {
        this.m_Minutes = m_Minutes;
        this.m_Hour = m_Hour;
    }

    public int getMinutes() {
        return m_Minutes;
    }

    public void setMinutes(int m_Minutes) {
        this.m_Minutes = m_Minutes;
    }

    public int getHour() {
        return m_Hour;
    }

    public void setHour(int m_Hour) {
        this.m_Hour = m_Hour;
    }
}
