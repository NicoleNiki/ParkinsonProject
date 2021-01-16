package com.example.parkinson.di;

import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.main.MainFragment;
import com.example.parkinson.features.medicine.MedicineFragment;
import com.example.parkinson.features.questionnaire.QuestionnaireFragment;
import com.example.parkinson.features.questionnaire.single_question.SingleQuestionFragment;

import dagger.Subcomponent;

@MainScope
@Subcomponent
public interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        MainComponent create();
    }

    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
    void inject(QuestionnaireFragment questionnaireFragment);
    void inject(SingleQuestionFragment singleQuestionFragment);
    void inject(MedicineFragment medicineFragment);

}
