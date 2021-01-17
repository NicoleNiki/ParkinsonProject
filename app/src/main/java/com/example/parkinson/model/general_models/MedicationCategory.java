package com.example.parkinson.model.general_models;

import java.util.List;

public class MedicationCategory {

   String categoryName;
   List<Medication> medicationList;

    public MedicationCategory() {
    }

    public MedicationCategory(String categoryName, List<Medication> medicationList) {
        this.categoryName = categoryName;
        this.medicationList = medicationList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }
}
