package com.example.parkinson.features.questionnaire.single_question;

import com.example.parkinson.features.questionnaire.single_question.binders.QuestionBinderChoiceAnswer;
import com.example.parkinson.features.questionnaire.single_question.binders.QuestionBinderOpenAnswer;
import com.example.parkinson.features.questionnaire.single_question.binders.QuestionBinderOpenAnswer.QuestionBinderOpenAnswerListener;
import com.example.parkinson.features.questionnaire.single_question.models.MultipleChoiceAnswer;
import com.example.parkinson.features.questionnaire.single_question.models.OpenAnswer;
import com.example.parkinson.model.question_models.OpenQuestion;

import java.util.List;

import mva2.adapter.ItemSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;
import mva2.adapter.util.OnSelectionChangedListener;

/**
 * This is a very nice library for creating multiple view list - MultiViewAdapter
 * We have 2 view types :
 * 1. Open answer single ItemSection
 * 2. Multiple Choice answer ListSection - with single/multiple selection mode
 * for more information about implementation visit = https://devahamed.github.io/MultiViewAdapter/#/
 * enjoy :)
 */
public class SingleQuestionMainAdapter extends MultiViewAdapter {

    SingleQuestionMainAdapterListener adapterListener;
    /** Interface for both sections clicks **/
    interface SingleQuestionMainAdapterListener extends QuestionBinderOpenAnswerListener {
        void onMultipleChoiceAnswerChanged(List<MultipleChoiceAnswer> answers);
    }

    public SingleQuestionMainAdapter(SingleQuestionMainAdapterListener adapterListener) {
        initAdapter();
        this.adapterListener = adapterListener;
    }


    /** 2 type of sections - for open question and for multiple choice questions**/
    private ListSection<MultipleChoiceAnswer> multipleChoiceAnswerSection = new ListSection<>();
    private ItemSection<OpenAnswer> openAnswerSection = new ItemSection<>();

    private void initAdapter() {
        this.registerItemBinders(new QuestionBinderOpenAnswer(adapterListener), new QuestionBinderChoiceAnswer());

        this.addSection(multipleChoiceAnswerSection);
        this.addSection(openAnswerSection);
        hideAllSections();
    }

    /**
     * updating data for multipleChoiceAnswerSection from fragment
     **/
    public void updateSectionMultiChoiceAnswers(Boolean isSingleSelection, List<String> list) {
        if (!list.isEmpty()) {
            if (isSingleSelection) {
                multipleChoiceAnswerSection.setSelectionMode(Mode.SINGLE);
            } else {
                multipleChoiceAnswerSection.setSelectionMode(Mode.MULTIPLE);
            }
            for (int i = 0; i < list.size(); i++) {
                String answer = list.get(i);
                multipleChoiceAnswerSection.add(new MultipleChoiceAnswer(answer, i));
            }
            addSelectionListener();
            multipleChoiceAnswerSection.showSection();
        }
    }

    /** getting all selections from section **/
    private void addSelectionListener() {
        OnSelectionChangedListener<MultipleChoiceAnswer> listener = (item, isSelected, selectedItems) -> {
            adapterListener.onMultipleChoiceAnswerChanged(selectedItems);
        };

        multipleChoiceAnswerSection.setOnSelectionChangedListener(listener);
    }

    /**
     * updating data for openAnswerSection from fragment
     **/
    public void updateSectionOpenAnswer() {
        openAnswerSection.setItem(new OpenAnswer());
        openAnswerSection.showSection();
    }

    /**
     * hiding all sections because only one selection is visible in a list
     **/
    private void hideAllSections() {
        multipleChoiceAnswerSection.hideSection();
        openAnswerSection.hideSection();
    }

}
