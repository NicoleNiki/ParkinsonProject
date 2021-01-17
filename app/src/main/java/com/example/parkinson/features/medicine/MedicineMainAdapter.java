package com.example.parkinson.features.medicine;

import com.example.parkinson.features.medicine.binder.MedicineBinderHeader;
import com.example.parkinson.features.medicine.binder.MedicineBinderMedicine;
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.HeaderSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.Section;

public class MedicineMainAdapter extends MultiViewAdapter {

    MedicineMainAdapter() {
        init();
    }

    private void init() {
        this.registerItemBinders(new MedicineBinderHeader(), new MedicineBinderMedicine());
    }

    void updateMedicineList(List<Medication> list) {
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
            ListSection<Medication> medicationListSection = new ListSection<>();
            medicationListSection.set(list);
            this.addSection(medicationListSection);

        }


}
