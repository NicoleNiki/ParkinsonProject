package com.example.parkinson.features.medicine;

import com.example.parkinson.features.medicine.binder.MedicineBinderHeader;
import com.example.parkinson.features.medicine.binder.MedicineBinderMedicine;
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;

import java.util.List;
import java.util.Locale;

import mva2.adapter.HeaderSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class MedicineMainAdapter extends MultiViewAdapter {

    MedicineMainAdapter() {
        init();
    }

    private void init() {
        this.registerItemBinders(new MedicineBinderHeader(), new MedicineBinderMedicine());
    }

    void updateMedicineList(List<MedicationCategory> list) {
        for (MedicationCategory category : list) {
            HeaderSection<String> headerSection = new HeaderSection<>(category.getCategoryType());
            ListSection<Medication> medicationListSection = new ListSection<>();
            medicationListSection.set(category.getMedicationList());
            headerSection.addSection(medicationListSection);
            this.addSection(headerSection);
        }
    }


}
