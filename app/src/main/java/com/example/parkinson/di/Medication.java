package com.example.parkinson.di;

import java.util.List;

public class Medication {
    private final String m_Name;
    private int m_Dosage;
    List<Time> m_Hours_Arr;

    public Medication(String name, int dosage, List<Time> hours_Arr) {
        m_Name = name;
        m_Dosage = dosage;
        m_Hours_Arr = hours_Arr;
    }

    public String getName() {
        return m_Name;
    }

    public int getDosage() {
        return m_Dosage;
    }

    public void setDosage(int dosage) {
        m_Dosage = dosage;
    }

    public List<Time> getHours_Arr() {
        return m_Hours_Arr;
    }

    public void setHours_Arr(List<Time> hours_Arr) {
        m_Hours_Arr = hours_Arr;
    }
}
