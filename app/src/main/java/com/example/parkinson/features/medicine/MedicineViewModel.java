package com.example.parkinson.features.medicine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.MainScope;
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;
import com.google.firebase.database.ChildEventListener;
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


    public MutableLiveData<List<Medication>> myMedicationData = new MutableLiveData<>();
    public MutableLiveData<List<MedicationCategory>> categoryListData = new MutableLiveData<>();
    public MutableLiveData<MedicationCategory> filteredCategory = new MutableLiveData<>();

    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    HashMap<String, Medication> medicationHashMap = new HashMap<>();

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public MedicineViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void initMedicineData() {
        isLoading.postValue(true);
        userRepository.getMedicationList(setMyMedicationListener());
        dataRepository.getMedicineList(setMedicationCategoryListener());
    }

    public void addNewMedicine(Medication medication) {
        isLoading.postValue(true);
        userRepository.postMedication(medication);
    }

    public void removeMedicine(Medication medication) {
        isLoading.postValue(true);
        userRepository.deleteMedication(medication);
    }

    private int currentChosenCategoryPosition = -1;
    public void updateFilteredCategory() {
        if (currentChosenCategoryPosition > -1){
            filterCategory(currentChosenCategoryPosition);
        }
    }

    public void filterCategory(int chosenCategoryPosition) {
        currentChosenCategoryPosition = chosenCategoryPosition;
        MedicationCategory chosenCategory = categoryListData.getValue().get(chosenCategoryPosition);
        List<Medication> filteredList = new ArrayList<>();
        for (Medication medication : chosenCategory.getMedicationList()) {
            if (medicationHashMap.containsKey(medication.getId())) {
                filteredList.add(medicationHashMap.get(medication.getId()));
            } else {
                filteredList.add(medication);
            }
        }
        filteredCategory.postValue(new MedicationCategory(chosenCategory.getCategoryName(),filteredList));
    }

    private ValueEventListener setMedicationCategoryListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //todo add no medication list
                if (dataSnapshot.exists()) {
                    List<MedicationCategory> medicationCategoriesList = new ArrayList<>();
                    for (DataSnapshot category : dataSnapshot.getChildren()) {
                        MedicationCategory currentCategory = new MedicationCategory();
                        String name = category.child("categoryName").getValue(String.class);
                        List<Medication> medicationList = new ArrayList<>();
                        for (DataSnapshot medication : category.child("medicationList").getChildren()) {
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

    private ChildEventListener setMyMedicationListener() {
        return new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    Medication med = snapshot.getValue(Medication.class);
                    medicationHashMap.put(med.getId(), med);
                    List<Medication> list = new ArrayList<Medication>(medicationHashMap.values());
                    myMedicationData.setValue(list);
                    updateFilteredCategory();
                    isLoading.postValue(false);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    Medication med = snapshot.getValue(Medication.class);
                    medicationHashMap.put(med.getId(), med);
                }
                List<Medication> list = new ArrayList<Medication>(medicationHashMap.values());
                myMedicationData.setValue(list);
                updateFilteredCategory();
                isLoading.postValue(false);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Medication med = snapshot.getValue(Medication.class);
                    medicationHashMap.remove(med.getId());
                }
                List<Medication> list = new ArrayList<Medication>(medicationHashMap.values());
                myMedicationData.setValue(list);
                updateFilteredCategory();
                isLoading.postValue(false);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isLoading.postValue(false);
            }
        };
    }


}
