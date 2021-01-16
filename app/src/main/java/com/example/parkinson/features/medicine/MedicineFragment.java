package com.example.parkinson.features.medicine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinson.R;
import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.model.general_models.MedicationCategory;

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

        medicineViewModel.initData();
        initViews(view);
        initUi();
        initObservers();

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.medicineFragRecycler);
    }

    private void initUi() {
        adapter = new MedicineMainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initObservers() {
        medicineViewModel.isLoading.observe(getViewLifecycleOwner(), isLoading-> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.updateLoadingScreen(isLoading);
        });
        medicineViewModel.categoryListData.observe(getViewLifecycleOwner(), medicationCategories -> {
            adapter.updateMedicineList(medicationCategories);
        });
    }
}
