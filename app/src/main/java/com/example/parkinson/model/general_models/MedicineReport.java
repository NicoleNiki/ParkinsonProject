package com.example.parkinson.model.general_models;

public class MedicineReport {

    String medicineId;
    String takenTime;

    public MedicineReport() {
    }

    public MedicineReport(String medicineId, String takenTime) {
        this.medicineId = medicineId;
        this.takenTime = takenTime;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(String takenTime) {
        this.takenTime = takenTime;
    }
}
