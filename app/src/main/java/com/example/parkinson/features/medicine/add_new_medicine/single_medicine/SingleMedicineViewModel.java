package com.example.parkinson.features.medicine.add_new_medicine.single_medicine;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.Time;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SingleMedicineViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Medicine> medicineData = new MutableLiveData<>();

    MutableLiveData<Boolean> nextBtnState = new MutableLiveData<>();

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public SingleMedicineViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void initData(Medicine medicine) {
        List<Time> hoursArr = new ArrayList<>();
        if(medicine.getHoursArr() != null){
            for(Time time : medicine.getHoursArr()){
                Time newTime = new Time(time.getMinutes(),time.getHour());
                hoursArr.add(newTime);
            }
        }

        Medicine newMedicine = new Medicine(
                medicine.getId(), medicine.getCategoryId(), medicine.getName(), medicine.getDosage(), hoursArr);
        medicineData.postValue(newMedicine);
    }

    public void setDosage(Double dosage) {
        medicineData.getValue().setDosage(dosage);
        nextBtnState.postValue(validateNextBtn());
    }

    /** checking next btn state on every change in medicine time arr **/
    public void checkTimeArr() {
        nextBtnState.postValue(validateNextBtn());
    }

    /** returns true only if user filled all requirements **/
    private Boolean validateNextBtn() {
        Medicine medicine = medicineData.getValue();
        if(medicine.getDosage() > 0 && medicine.getHoursArr().size() > 0) {
            for (Time time : medicine.getHoursArr()){
                if (time.getHour() == -1 && time.getMinutes() == -1){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void saveMedicine() {
        userRepository.postMedication(medicineData.getValue());
    }

    public void deleteMedicine() {
        userRepository.deleteMedication(medicineData.getValue());
    }

}
