package com.example.parkinson.model.general_models;

import java.util.List;

public class MedicationCategory {
   String  categoryType;
   List<Medication> medicationList;

    public MedicationCategory() {
    }

    public MedicationCategory(String categoryType, List<Medication> medicationList) {
        this.categoryType = categoryType;
        this.medicationList = medicationList;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }
}
