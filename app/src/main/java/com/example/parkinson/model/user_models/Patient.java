package com.example.parkinson.model.user_models;

import com.example.parkinson.model.enums.EClinics;
import com.example.parkinson.model.question_models.Questionnaire;

import java.util.Date;

public class Patient {
    private  String name, ID, birthCountry;
    private String phoneNumber, email, clinicName;
    private int educationYears;
    private Date birthDate;
    private EClinics clinics;

    private Boolean hasUnansweredQuestionnaire;

    private Boolean needToUpdateMedicine;
    public Patient() {}

    public Patient(String name, String ID, String birthCountry, String phoneNumber, String email, String clinicName, int educationYears, Date birthDate, EClinics clinics, Boolean hasUnansweredQuestionnaire, Boolean needToUpdateMedicine) {
        this.name = name;
        this.ID = ID;
        this.birthCountry = birthCountry;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.clinicName = clinicName;
        this.educationYears = educationYears;
        this.birthDate = birthDate;
        this.clinics = clinics;
        this.hasUnansweredQuestionnaire = hasUnansweredQuestionnaire;
        this.needToUpdateMedicine = needToUpdateMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public int getEducationYears() {
        return educationYears;
    }

    public void setEducationYears(int educationYears) {
        this.educationYears = educationYears;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public EClinics getClinics() {
        return clinics;
    }

    public void setClinics(EClinics clinics) {
        this.clinics = clinics;
    }

    public Boolean getHasUnansweredQuestionnaire() {
        if(hasUnansweredQuestionnaire != null){
            return hasUnansweredQuestionnaire;
        } else {
            return true;
        }
    }

    public void setHasUnansweredQuestionnaire(Boolean hasUnansweredQuestionnaire) {
        this.hasUnansweredQuestionnaire = hasUnansweredQuestionnaire;
    }

    public Boolean getNeedToUpdateMedicine() {
        if(needToUpdateMedicine != null){
            return hasUnansweredQuestionnaire;
        } else {
            return true;
        }
    }

    public void setNeedToUpdateMedicine(Boolean needToUpdateMedicine) {
        this.needToUpdateMedicine = needToUpdateMedicine;
    }

}