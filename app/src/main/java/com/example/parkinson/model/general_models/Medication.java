package com.example.parkinson.model.general_models;

import java.util.List;

public class Medication {
    private String id;
    private String categoryId;

    private String name;
    private int dosage;
    List<Time> hoursArr;

    public Medication(){

    }

    public Medication(String id, String categoryId,String name, int dosage, List<Time> hoursArr) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.dosage = dosage;
        this.hoursArr = hoursArr;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
