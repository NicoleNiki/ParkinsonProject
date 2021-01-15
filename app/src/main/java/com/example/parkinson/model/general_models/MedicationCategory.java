package com.example.parkinson.model.general_models;

import com.example.parkinson.model.enums.ECategoryMedicines;

import java.util.List;

public class MedicationCategory {
   ECategoryMedicines  categoryType;
   List<Medication> medicationList;

    public MedicationCategory() {
    }

    public MedicationCategory(ECategoryMedicines categoryType, List<Medication> medicationList) {
        this.categoryType = categoryType;
        this.medicationList = medicationList;
    }

    public ECategoryMedicines getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(ECategoryMedicines categoryType) {
        this.categoryType = categoryType;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }
}
