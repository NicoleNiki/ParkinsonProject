package com.example.parkinson.model.general_models;

public class MedicineReport {

    String medicineId;
    long takenTime;
    String medicineName;

    public MedicineReport() {
    }

    public MedicineReport(String medicineId, long takenTime, String medicineName) {
        this.medicineId = medicineId;
        this.takenTime = takenTime;
        this.medicineName = medicineName;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public long getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(long takenTime) {
        this.takenTime = takenTime;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
