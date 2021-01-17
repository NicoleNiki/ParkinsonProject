package com.example.parkinson.features.medicine;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.MainScope;
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

@MainScope
public class MedicineViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;


    MutableLiveData<List<Medication>> myMedicationData = new MutableLiveData<>();
    MutableLiveData<List<MedicationCategory>> categoryListData = new MutableLiveData<>();
    public MutableLiveData<MedicationCategory> filteredCategory = new MutableLiveData<>();
    List<MedicationCategory> medicationCategoriesList;

    MutableLiveData<Boolean> isLoading  = new MutableLiveData<>();
    HashMap<String,Medication> medicationHashMap = new HashMap<>();

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public MedicineViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void initData(){
        userRepository.getMedicationList(setMyMedicationListener());
//        userRepository.updateMedications();
//        isLoading.postValue(true);
        dataRepository.getMedicineList(setMedicationCategoryListener());
    }

    public List<MedicationCategory> getMedicationCategoriesList() {
        return medicationCategoriesList;
    }

    public void filterCategory(MedicationCategory category){
        for (Medication medication: category.getMedicationList()){
            if (medicationHashMap.containsKey(medication.getId())){
                medication = medicationHashMap.get(medication.getId());
            }
        }
        filteredCategory.setValue(category);
    }

    private ValueEventListener setMedicationCategoryListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //todo add no medication list
                if (dataSnapshot.exists()) {
                    medicationCategoriesList = new ArrayList<>();
                    for (DataSnapshot category : dataSnapshot.getChildren()) {
                        MedicationCategory currentCategory = new MedicationCategory();
                        String name = category.child("categoryName").getValue(String.class);
                        List<Medication> medicationList = new ArrayList<>();
                       for(DataSnapshot medication: category.child("medicationList").getChildren()){
                           medicationList.add(medication.getValue(Medication.class));
                       }
                       currentCategory.setCategoryName(name);
                       currentCategory.setMedicationList(medicationList);
                       medicationCategoriesList.add(currentCategory);
                    }
                    categoryListData.setValue(medicationCategoriesList);
                    isLoading.postValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isLoading.postValue(false);
            }
        };
    }

    private ValueEventListener setMyMedicationListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot medication : dataSnapshot.getChildren()) {
                        Medication med = medication.getValue(Medication.class);
                        medicationHashMap.put(med.getId(),med);
                    }
                    List<Medication> list = new ArrayList<Medication>(medicationHashMap.values());
                    myMedicationData.setValue(list);
                    isLoading.postValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isLoading.postValue(false);
            }
        };
    }

}
