package com.example.parkinson.model.user_models;

import com.example.parkinson.model.enums.EClinics;

import java.util.Date;

public class Patient {
    private  String m_Name, m_ID, m_Birth_Country;
    private String m_Phone_Number, m_Email, m_Clinic_Name;
    private int m_Education_Years;
    private Date m_Birth_Date;
    private EClinics m_Clinics;

    public Patient() {
    }

    public Patient(String name, String ID, String birth_Country, String phone_Number, String email, String clinic_Name, int education_Years, Date birth_Date, EClinics clinics) {
        m_Name = name;
        this.m_ID = ID;
        m_Birth_Country = birth_Country;
        m_Phone_Number = phone_Number;
        m_Email = email;
        m_Clinic_Name = clinic_Name;
        m_Education_Years = education_Years;
        m_Birth_Date = birth_Date;
        m_Clinics = clinics;
    }

    public String getName() {
        return m_Name;
    }

    public void setName(String m_Name) {
        this.m_Name = m_Name;
    }

    public String getID() {
        return m_ID;
    }

    public void setID(String m_ID) {
        this.m_ID = m_ID;
    }

    public String getBirth_Country() {
        return m_Birth_Country;
    }

    public void setBirth_Country(String m_Birth_Country) {
        this.m_Birth_Country = m_Birth_Country;
    }

    public Date getBirth_Date() {
        return m_Birth_Date;
    }

    public void setBirth_Date(Date m_Birth_Date) {
        this.m_Birth_Date = m_Birth_Date;
    }

    public String getPhone_Number() {
        return m_Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        m_Phone_Number = phone_Number;
    }

    public String getEmail() {
        return m_Email;
    }

    public void setEmail(String email) {
        m_Email = email;
    }

    public String getClinic_Name() {
        return m_Clinic_Name;
    }

    public void setClinic_Name(String clinic_Name) {
        m_Clinic_Name = clinic_Name;
    }

    public int getEducation_Years() {
        return m_Education_Years;
    }

    public void setEducation_Years(int education_Years) {
        m_Education_Years = education_Years;
    }

    public EClinics getClinics() {
        return m_Clinics;
    }

    public void setClinics(EClinics clinics) {
        m_Clinics = clinics;
    }


}