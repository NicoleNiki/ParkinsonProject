package com.example.parkinson.features.medicine;

import com.example.parkinson.features.medicine.binder.MedicineBinderHeader;
import com.example.parkinson.features.medicine.binder.MedicineBinderMedicine;
import com.example.parkinson.model.general_models.Medicine;

import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class MyMedicinesMainAdapter extends MultiViewAdapter {

    MyMedicinesMainAdapter() {
        init();
    }

    private void init() {
        this.registerItemBinders(new MedicineBinderHeader(), new MedicineBinderMedicine());
    }

    void updateMedicineList(List<Medicine> list) {
////        for (MedicationCategory category : list) {
////            HeaderSection<String> headerSection = new HeaderSection<>(category.getCategoryName());
//            ListSection<Medication> medicationListSection = new ListSection<>();
//            List<Medication>medicationList = category.getMedicationList();
//            List<Medication>MyMedicationList = new ArrayList<>();
//
////            for(Medication medication:medicationList)
////            {
////                if (myhasmp.isExizst(medication.getId()))
////                    MyMedicationList.add(medication)
////                    else
////                        notExzistList.add(medication)
////            }
//            medicationListSection.set(category.getMedicationList());
//            headerSection.addSection(medicationListSection);
//            this.addSection(headerSection);

            this.removeAllSections();
            ListSection<Medicine> medicationListSection = new ListSection<>();
            medicationListSection.set(list);
            this.addSection(medicationListSection);

        }


}
