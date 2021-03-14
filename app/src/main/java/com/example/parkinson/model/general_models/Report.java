package com.example.parkinson.model.general_models;

import com.example.parkinson.model.enums.EStatus;

import java.util.Date;

public class Report {
    private Long reportTime;
    private EStatus status;

    public Report(){

    }

    public Report(Long reportTime, EStatus status) {
        this.reportTime = reportTime;
        this.status = status;
    }

    public Long getReportTime() {
        return reportTime;
    }

    public void setReportTime(Long reportTime) {
        this.reportTime = reportTime;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
