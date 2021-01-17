package com.example.parkinson.model.general_models;

import com.example.parkinson.model.enums.EStatus;

import java.util.Date;

public class Report {
    private Date reportTime;
    private EStatus status;

    public Report(Date reportTime, EStatus status) {
        this.reportTime = reportTime;
        this.status = status;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
