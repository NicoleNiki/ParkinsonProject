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
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MedicineFragment extends Fragment {

    @Inject
    MedicineViewModel medicineViewModel;

    RecyclerView recyclerView;
    MedicineMainAdapter adapter;

    public MedicineFragment(){
        super(R.layout.fragment_medicine);
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
        recyclerView = view.findViewById(R.id.medicineFragRecycler);
    }

    private void initUi(View view) {
        adapter = new MedicineMainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        CardView addMedicine = view.findViewById(R.id.medicineFragAddBtn);
        addMedicine.setOnClickListener(v -> {
            NavDirections action = MedicineFragmentDirections.actionMedicineFragmentToMedicineCategoryFragment();
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
        List<MedicationCategory> medicationCategoryList = new ArrayList<>();
        List<Medication> medicationList = new ArrayList<>();

        FirebaseDatabase MEDICATION_data_base = FirebaseDatabase.getInstance();
        DatabaseReference templates = MEDICATION_data_base.getReference("Data");





//         medopar list
        medicationList.add(new Medication("","" ,"מדופאר 62.5" , 0,null));
        medicationList.add(new Medication("","" ,"מדופאר 50" , 0,null));
        medicationList.add(new Medication("","" ,"מדופאר 125" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 12.5" , 0,null));
        medicationList.add(new Medication("","" , "לבופר פלוס 125" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 250" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 200" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 50" , 0,null));
        medicationList.add(new Medication("","" , "לבופר פלוס 250" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 60.5 בתרחיף" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 50 בתרחיף" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 12.5 בתרחיף" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 125 בתרחיף" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 100 בתרחיף" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר 25 בתרחיף" , 0,null));
        medicationList.add(new Medication("","" , "מדופאר CR" , 0,null));
        medicationCategoryList.add(new MedicationCategory("מדופאר",medicationList));

        // sinmat list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication("","", " 50 סינמט LS" , 0,null));
        medicationList.add(new Medication("","", "12.5 סינמט LS" , 0,null));
        medicationList.add(new Medication("","", "סינמט 110" , 0,null));
        medicationList.add(new Medication("","", "סינמט 100" , 0,null));
        medicationList.add(new Medication("","", "סינמט 10" , 0,null));
        medicationList.add(new Medication("","", "סינמט 275" , 0,null));
        medicationList.add(new Medication("","", "סינמט 250" , 0,null));
        medicationList.add(new Medication("","", "סינמט 25" , 0,null));
        medicationList.add(new Medication("","", "דופיקר" , 0,null));
        medicationList.add(new Medication("","", "סינמט פלוס 100" , 0,null));
        medicationList.add(new Medication("","", "סינמט פלוס 25" , 0,null));
        medicationList.add(new Medication("","", "חצי סינמט CR 100" , 0,null));
        medicationList.add(new Medication("","", "חצי סינמט CR 25" , 0,null));
        medicationList.add(new Medication("","", "200 סינמט CR" , 0,null));
        medicationList.add(new Medication("","", "50 סינמט CR" , 0,null));
        medicationCategoryList.add(new MedicationCategory("סינמט",medicationList));

        // Dopamine Agonists list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication("","", "ברומוקריפטין" , 0,null));
        medicationList.add(new Medication("","", "פרלודל, פרילק" , 0,null));
        medicationList.add(new Medication("","", "קברגולין" , 0,null));
        medicationList.add(new Medication("","", "קבסר" , 0,null));
        medicationList.add(new Medication("","", "ליזוריד" , 0,null));
        medicationList.add(new Medication("","", "דופרגין" , 0,null));
        medicationList.add(new Medication("","", "פרגוליד" , 0,null));
        medicationList.add(new Medication("","", "רופינרול" , 0,null));
        medicationList.add(new Medication("","", "רקוויפ" , 0,null));
        medicationList.add(new Medication("","", "פרמיפקסול" , 0,null));

        medicationCategoryList.add(new MedicationCategory("דופמין אגוניסטים",medicationList));

        // Amantadine list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication("","", "אדמנטנאמין סולפט" , 0,null));
        medicationList.add(new Medication("","", "א-פרקין" , 0,null));
        medicationList.add(new Medication("","", "אמנטדין הידרוכלוריד" , 0,null));
        medicationList.add(new Medication("","", "פריטרל" , 0,null));

        medicationCategoryList.add(new MedicationCategory("אמנטדין",medicationList));

        // Anticholinergics list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication("","", "טריהקסיפנידיל" , 0,null));
        medicationList.add(new Medication("","", "ארטן" , 0,null));
        medicationList.add(new Medication("","", "פרטן" , 0,null));
        medicationList.add(new Medication("","", "בנזטרופין" , 0,null));
        medicationList.add(new Medication("","", "קוגנטין" , 0,null));
        medicationList.add(new Medication("","", "ביפרידן" , 0,null));
        medicationList.add(new Medication("","", "דהקינט" , 0,null));
        medicationList.add(new Medication("","", "פרוציקלידין" , 0,null));
        medicationList.add(new Medication("","", "קמדרין" , 0,null));
        medicationList.add(new Medication("","", "אורפנדרין" , 0,null));
        medicationList.add(new Medication("","","דיסיפל" , 0,null));
        medicationCategoryList.add(new MedicationCategory("אנטי כולינרגיות",medicationList));

        // COMT  list
        medicationList = new ArrayList<>();
        medicationList.add(new Medication("","", "אנטקפון" , 0,null));
        medicationList.add(new Medication("","", "קומטן" , 0,null));
        medicationList.add(new Medication("","", "טולקפון" , 0,null));
        medicationList.add(new Medication("","", "סלג'ילין" , 0,null));
        medicationList.add(new Medication("","", "יומקס" , 0,null));
        medicationCategoryList.add(new MedicationCategory("מעכבי האנזים",medicationList));


        for(MedicationCategory category: medicationCategoryList){
            String categoryID = templates.child("Test").push().getKey();
            MedicationCategory temp = new MedicationCategory(category.getCategoryName(),null);
            templates.child("Test").child(categoryID).setValue(temp);
            for(Medication medication:category.getMedicationList()){
                String id = templates.child("Test").child(categoryID).child("medicationList").push().getKey();
                medication.setId(id);
                medication.setCategoryId(categoryID);
                templates.child("Test").child(categoryID).child("medicationList").child(id).setValue(medication);
            }
        }


    }
}
