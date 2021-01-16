package com.example.parkinson.model.general_models;

import java.util.List;

public class Medication {
    private String name;
    private int dosage;
    List<Time> hoursArr;

    public Medication(){

    }

    public Medication(String name, int dosage, List<Time> hoursArr) {
        this.name = name;
        this.dosage = dosage;
        this.hoursArr = hoursArr;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public List<Time> getHoursArr() {
        return hoursArr;
    }

    public void setHoursArr(List<Time> hoursArr) {
        this.hoursArr = hoursArr;
    }
}
