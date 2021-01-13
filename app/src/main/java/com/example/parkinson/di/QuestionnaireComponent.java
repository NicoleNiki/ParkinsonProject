package com.example.parkinson.di;

import com.example.parkinson.features.questionnaire.QuestionnaireActivity;
import com.example.parkinson.features.questionnaire.single_question.SingleQuestionFragment;

import dagger.Subcomponent;

@QuestionnaireScope
@Subcomponent
public interface QuestionnaireComponent {

    @Subcomponent.Factory
    interface Factory {
        QuestionnaireComponent create();
    }

    void inject(QuestionnaireActivity questionnaireActivity);
    void inject(SingleQuestionFragment singleQuestionFragment);
}
