package com.example.parkinson.features.main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.parkinson.R;
import com.example.parkinson.model.user_models.Patient;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    MainViewModel mainViewModel;

    CardView medicineBtn;
    CardView questionnaireBtn;
    CardView reportBtn;

    ImageView medicineBadge;
    ImageView questionnaireBadge;

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((MainActivity) getActivity()).mainComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel.initData();
        initViews(view);
        initUi(view);
        initObservers();

    }

    private void initViews(View view) {
        medicineBtn = view.findViewById(R.id.mainFragMedicineBtn);
        questionnaireBtn = view.findViewById(R.id.mainFragQuestionnaireBtn);
        reportBtn = view.findViewById(R.id.mainFragReportBtn);
        medicineBadge = view.findViewById(R.id.mainFragMedicineBadge);
        questionnaireBadge = view.findViewById(R.id.mainFragQuestionnaireBadge);
    }

    private void initUi(View view) {
        medicineBtn.setOnClickListener(v -> {
            openMedicineFragment(view);
        });
        questionnaireBtn.setOnClickListener(v -> {
            openQuestionnaireFragment(view);
        });
        reportBtn.setOnClickListener(v -> {
            openReportFragment(view);
        });

    }

    private void initObservers() {
        mainViewModel.patientEvent.observe(getViewLifecycleOwner(), patient -> {
            handlePatientData(patient);
        });

    }

    private void handlePatientData(Patient patient) {
        if (patient.getHasUnansweredQuestionnaire()) {
            questionnaireBadge.setVisibility(View.VISIBLE);
        } else {
            questionnaireBadge.setVisibility(View.INVISIBLE);
        }

        if (patient.getNeedToUpdateMedicine()) {
            medicineBadge.setVisibility(View.VISIBLE);
        } else {
            medicineBadge.setVisibility(View.INVISIBLE);
        }
    }

    /** Navigates to QuestionnaireFragment with NavigationController with isNewQuestionnaire Args **/
    private void openQuestionnaireFragment(View view){
        NavDirections action = MainFragmentDirections.actionMainFragmentToQuestionnaireFragment(mainViewModel.patientEvent.getValue().getHasUnansweredQuestionnaire());
        Navigation.findNavController(view).navigate(action);
    }

    private void openMedicineFragment(View view) {
        NavDirections action = MainFragmentDirections.actionMainFragmentToMedicineFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void openReportFragment(View view) {
        NavDirections action = MainFragmentDirections.actionMainFragmentToNotificationFragment();
        Navigation.findNavController(view).navigate(action);
    }


}
