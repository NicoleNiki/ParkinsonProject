package com.example.parkinson.features.medicine.add_new_medicine.single_medicine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinson.R;
import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.Time;

import java.util.ArrayList;

import javax.inject.Inject;

public class SingleMedicineFragment extends Fragment {

    @Inject
    SingleMedicineViewModel singleMedicineViewModel;

    public SingleMedicineFragment() {
        super(R.layout.fragment_single_medicine);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((MainActivity) getActivity()).mainComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            singleMedicineViewModel.initData(
                    SingleMedicineFragmentArgs.fromBundle(getArguments()).getMedicine()
            );
        }

        initUi();
        initObservers();

    }

    private void initUi() {
        getView().findViewById(R.id.singleMedicineFragExitBtn).setOnClickListener(v->{
            getActivity().onBackPressed();
        });

        getView().findViewById(R.id.myMedicinesFragDeleteButton).setOnClickListener(v->{
            singleMedicineViewModel.deleteMedicine();
            getActivity().onBackPressed();
        });

        getView().findViewById(R.id.myMedicinesFragSaveButton).setOnClickListener(v->{
            singleMedicineViewModel.saveMedicine();
            getActivity().onBackPressed();
        });
    }

    private void initObservers() {
        singleMedicineViewModel.medicineData.observe(getViewLifecycleOwner(), it -> {
            handleMedicineData(it);
        });
    }

    private void handleMedicineData(Medicine medicine) {
        TextView title = getView().findViewById(R.id.singleMedicineFragTitle);
        RecyclerView recyclerView = getView().findViewById(R.id.singleMedicineFragRecycler);
        TextView timeNumber = getView().findViewById(R.id.singleMedicineFragTimeNumber);
        ImageButton addButton = getView().findViewById(R.id.singleMedicineFragAddTime);
        ImageButton removeButton = getView().findViewById(R.id.singleMedicineFragRemoveTime);
        MedicineTimeAdapter adapter = new MedicineTimeAdapter(medicine.getHoursArr());
        recyclerView.setAdapter(adapter);

        title.setText(medicine.getName());
        timeNumber.setText(String.valueOf(adapter.getItemCount()));

        addButton.setOnClickListener(v->{
            adapter.addTime();
            timeNumber.setText(String.valueOf(adapter.getItemCount()));
        });
        removeButton.setOnClickListener(v->{
            adapter.removeTime();
            timeNumber.setText(String.valueOf(adapter.getItemCount()));
        });
        initDosageButtons(medicine.getDosage());
    }

    private void initDosageButtons(Double selectedDosage) {
        final RadioGroup rgTop = getView().findViewById(R.id.rg_exponent_top);
        final RadioGroup rgBottom = getView().findViewById(R.id.rg_exponent_bottom);
        final RadioButton radioOne = getView().findViewById(R.id.radioOne);
        final RadioButton radioTwo = getView().findViewById(R.id.radioTwo);
        final RadioButton radioThree = getView().findViewById(R.id.radioThree);
        final RadioButton radioForth = getView().findViewById(R.id.radioForth);

        radioOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioOne.setChecked(true);
                rgBottom.clearCheck();
                singleMedicineViewModel.setDosage(0.5);
            }
        });
        radioTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioTwo.setChecked(true);
                rgBottom.clearCheck();
                singleMedicineViewModel.setDosage(1.0);

            }
        });
        radioThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioThree.setChecked(true);
                rgTop.clearCheck();
                singleMedicineViewModel.setDosage(1.5);

            }
        });
        radioForth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioForth.setChecked(true);
                rgTop.clearCheck();
                singleMedicineViewModel.setDosage(2.0);
            }
        });

        if(selectedDosage != null){
            if (selectedDosage == 0.5) {
                radioOne.setChecked(true);
            } else if (selectedDosage == 1.0) {
                radioTwo.setChecked(true);
            } else if (selectedDosage == 1.5) {
                radioThree.setChecked(true);
            } else if (selectedDosage == 2.0) {
                radioForth.setChecked(true);
            }
        }

    }
}
