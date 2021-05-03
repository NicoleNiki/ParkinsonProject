package com.example.parkinson.model.general_models;

import com.example.parkinson.model.enums.EStatus;

public class Report {
    private Long reportTime;
    private EStatus status;
    private Boolean hallucinations = false;
    private Boolean falls = false;

    public Report() { }

    public Report(Long reportTime, EStatus status, Boolean hallucinations, Boolean falls) {
        this.reportTime = reportTime;
        this.status = status;
        this.hallucinations = hallucinations;
        this.falls = falls;
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

    public Boolean getHallucinations() {
        return hallucinations;
    }

    public void setHallucinations(Boolean hallucinations) {
        hallucinations = hallucinations;
    }

    public Boolean getFalls() {
        return falls;
    }

    public void setFalls(Boolean withFalls) {
        this.falls = withFalls;
    }
}
