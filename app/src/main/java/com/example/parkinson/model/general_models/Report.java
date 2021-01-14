package com.example.parkinson.model.general_models;

import com.example.parkinson.model.enums.EStatus;

public class Report {
    private Time reportTime;
    private EStatus status;

    public Report(Time reportTime, EStatus status) {
        this.reportTime = reportTime;
        this.status = status;
    }

    public Time getReportTime() {
        return reportTime;
    }

    public void setReportTime(Time reportTime) {
        this.reportTime = reportTime;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
