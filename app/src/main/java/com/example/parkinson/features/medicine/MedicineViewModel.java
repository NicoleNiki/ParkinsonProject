package com.example.parkinson.features.medicine;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.MainScope;
import com.example.parkinson.model.enums.EQuestionType;
import com.example.parkinson.model.general_models.MedicationCategory;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;
import com.example.parkinson.model.question_models.Questionnaire;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

@MainScope
public class MedicineViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    MutableLiveData<List<MedicationCategory>> categoryListData = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading  = new MutableLiveData<>();

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public MedicineViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void initData(){
        isLoading.postValue(true);
        dataRepository.getMedicineList(setMedicationCategoryListener());
    }

    private ValueEventListener setMedicationCategoryListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //todo add no medication list
                if (dataSnapshot.exists()) {
                    List<MedicationCategory> medicationCategories = new ArrayList<>();
                    for (DataSnapshot category : dataSnapshot.getChildren()) {

                        MedicationCategory medicationCategory = category.getValue(MedicationCategory.class);
                        medicationCategories.add(medicationCategory);
                    }
                    categoryListData.setValue(medicationCategories);
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
