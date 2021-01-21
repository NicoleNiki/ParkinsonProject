package com.example.parkinson.features.medicine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinson.R;
import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.MedicineCategory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyMedicinesFragment extends Fragment {

    @Inject
    MedicineViewModel medicineViewModel;

    RecyclerView recyclerView;
    MyMedicinesMainAdapter adapter;

    public MyMedicinesFragment(){
        super(R.layout.fragment_my_medicines);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((MainActivity) getActivity()).mainComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medicineViewModel.initMedicineData();
        initViews(view);
        initUi(view);
        initObservers();

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.myMedicinesFragRecycler);
    }

    private void initUi(View view) {
        adapter = new MyMedicinesMainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        CardView addMedicine = view.findViewById(R.id.myMedicinesFragAddBtn);
        addMedicine.setOnClickListener(v -> {
            NavDirections action = MyMedicinesFragmentDirections.actionMedicineFragmentToMedicineCategoryFragment();
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void initObservers() {
        medicineViewModel.isLoading.observe(getViewLifecycleOwner(), isLoading-> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.updateLoadingScreen(isLoading);
        });
        medicineViewModel.myMedicationData.observe(getViewLifecycleOwner(), medicationCategories -> {
            adapter.updateMedicineList(medicationCategories);
        });
    }


    private void AddMedicane()
    {
        List<MedicineCategory> medicineCategoryList = new ArrayList<>();
        List<Medicine> medicineList = new ArrayList<>();

        FirebaseDatabase MEDICATION_data_base = FirebaseDatabase.getInstance();
        DatabaseReference templates = MEDICATION_data_base.getReference("Data");





//         medopar list
        medicineList.add(new Medicine("","" ,"מדופאר 62.5" , 0,null));
        medicineList.add(new Medicine("","" ,"מדופאר 50" , 0,null));
        medicineList.add(new Medicine("","" ,"מדופאר 125" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 12.5" , 0,null));
        medicineList.add(new Medicine("","" , "לבופר פלוס 125" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 250" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 200" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 50" , 0,null));
        medicineList.add(new Medicine("","" , "לבופר פלוס 250" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 60.5 בתרחיף" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 50 בתרחיף" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 12.5 בתרחיף" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 125 בתרחיף" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 100 בתרחיף" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר 25 בתרחיף" , 0,null));
        medicineList.add(new Medicine("","" , "מדופאר CR" , 0,null));
        medicineCategoryList.add(new MedicineCategory("מדופאר", medicineList));

        // sinmat list
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine("","", " 50 סינמט LS" , 0,null));
        medicineList.add(new Medicine("","", "12.5 סינמט LS" , 0,null));
        medicineList.add(new Medicine("","", "סינמט 110" , 0,null));
        medicineList.add(new Medicine("","", "סינמט 100" , 0,null));
        medicineList.add(new Medicine("","", "סינמט 10" , 0,null));
        medicineList.add(new Medicine("","", "סינמט 275" , 0,null));
        medicineList.add(new Medicine("","", "סינמט 250" , 0,null));
        medicineList.add(new Medicine("","", "סינמט 25" , 0,null));
        medicineList.add(new Medicine("","", "דופיקר" , 0,null));
        medicineList.add(new Medicine("","", "סינמט פלוס 100" , 0,null));
        medicineList.add(new Medicine("","", "סינמט פלוס 25" , 0,null));
        medicineList.add(new Medicine("","", "חצי סינמט CR 100" , 0,null));
        medicineList.add(new Medicine("","", "חצי סינמט CR 25" , 0,null));
        medicineList.add(new Medicine("","", "200 סינמט CR" , 0,null));
        medicineList.add(new Medicine("","", "50 סינמט CR" , 0,null));
        medicineCategoryList.add(new MedicineCategory("סינמט", medicineList));

        // Dopamine Agonists list
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine("","", "ברומוקריפטין" , 0,null));
        medicineList.add(new Medicine("","", "פרלודל, פרילק" , 0,null));
        medicineList.add(new Medicine("","", "קברגולין" , 0,null));
        medicineList.add(new Medicine("","", "קבסר" , 0,null));
        medicineList.add(new Medicine("","", "ליזוריד" , 0,null));
        medicineList.add(new Medicine("","", "דופרגין" , 0,null));
        medicineList.add(new Medicine("","", "פרגוליד" , 0,null));
        medicineList.add(new Medicine("","", "רופינרול" , 0,null));
        medicineList.add(new Medicine("","", "רקוויפ" , 0,null));
        medicineList.add(new Medicine("","", "פרמיפקסול" , 0,null));

        medicineCategoryList.add(new MedicineCategory("דופמין אגוניסטים", medicineList));

        // Amantadine list
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine("","", "אדמנטנאמין סולפט" , 0,null));
        medicineList.add(new Medicine("","", "א-פרקין" , 0,null));
        medicineList.add(new Medicine("","", "אמנטדין הידרוכלוריד" , 0,null));
        medicineList.add(new Medicine("","", "פריטרל" , 0,null));

        medicineCategoryList.add(new MedicineCategory("אמנטדין", medicineList));

        // Anticholinergics list
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine("","", "טריהקסיפנידיל" , 0,null));
        medicineList.add(new Medicine("","", "ארטן" , 0,null));
        medicineList.add(new Medicine("","", "פרטן" , 0,null));
        medicineList.add(new Medicine("","", "בנזטרופין" , 0,null));
        medicineList.add(new Medicine("","", "קוגנטין" , 0,null));
        medicineList.add(new Medicine("","", "ביפרידן" , 0,null));
        medicineList.add(new Medicine("","", "דהקינט" , 0,null));
        medicineList.add(new Medicine("","", "פרוציקלידין" , 0,null));
        medicineList.add(new Medicine("","", "קמדרין" , 0,null));
        medicineList.add(new Medicine("","", "אורפנדרין" , 0,null));
        medicineList.add(new Medicine("","","דיסיפל" , 0,null));
        medicineCategoryList.add(new MedicineCategory("אנטי כולינרגיות", medicineList));

        // COMT  list
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine("","", "אנטקפון" , 0,null));
        medicineList.add(new Medicine("","", "קומטן" , 0,null));
        medicineList.add(new Medicine("","", "טולקפון" , 0,null));
        medicineList.add(new Medicine("","", "סלג'ילין" , 0,null));
        medicineList.add(new Medicine("","", "יומקס" , 0,null));
        medicineCategoryList.add(new MedicineCategory("מעכבי האנזים", medicineList));


        for(MedicineCategory category: medicineCategoryList){
            String categoryID = templates.child("Test").push().getKey();
            MedicineCategory temp = new MedicineCategory(category.getCategoryName(),null);
            templates.child("Test").child(categoryID).setValue(temp);
            for(Medicine medicine :category.getMedicineList()){
                String id = templates.child("Test").child(categoryID).child("medicationList").push().getKey();
                medicine.setId(id);
                medicine.setCategoryId(categoryID);
                templates.child("Test").child(categoryID).child("medicationList").child(id).setValue(medicine);
            }
        }


    }
}
