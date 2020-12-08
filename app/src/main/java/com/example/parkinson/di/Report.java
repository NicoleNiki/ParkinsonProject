package com.example.parkinson.di;

public class Report {
    private Time m_Report_Time;
    private EStatus m_Status;

    public Report(Time report_Time, EStatus status) {
        m_Report_Time = report_Time;
        this.m_Status = status;
    }

    public Time getReport_Time() {
        return m_Report_Time;
    }

    public void setReport_Time(Time report_Time) {
        m_Report_Time = report_Time;
    }

    public EStatus getStatus() {
        return m_Status;
    }

    public void setStatus(EStatus status) {
        this.m_Status = status;
    }
}
