package com.example.parkinson.features.medicine.add_new_medicine.medication_list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinson.R;
import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.medicine.MedicineViewModel;
import com.example.parkinson.features.medicine.add_new_medicine.medication_list.MedicineListAdapter.MedicineListAdapterListener;
import com.example.parkinson.model.general_models.MedicationCategory;

import java.util.Locale;
import java.util.Observer;

import javax.inject.Inject;

public class MedicineListFragment extends Fragment {

    @Inject
    MedicineViewModel medicineViewModel;

    RecyclerView recyclerView;
    MedicineListAdapter adapter;
    MedicationCategory category;

    public MedicineListFragment(){
        super(R.layout.fragment_medicine_list);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((MainActivity) getActivity()).mainComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initUi(view);
        initObservers();

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.medicineListFragRecycler);
    }

    private void initUi(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private MedicineListAdapterListener getAdapterListener() {
        return medicationCategory -> {

        };
    }

    private void initObservers() {
        medicineViewModel.filteredCategory.observe(getViewLifecycleOwner(), category->{
            MedicineListAdapterListener listener = getAdapterListener();
            adapter = new MedicineListAdapter(category, listener);
            recyclerView.setAdapter(adapter);

            TextView title = getView().findViewById(R.id.medicineListFragTitle);
            title.setText(category.getCategoryName());
        });
    }
}
