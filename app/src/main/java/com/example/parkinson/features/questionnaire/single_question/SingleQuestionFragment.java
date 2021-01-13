package com.example.parkinson.features.questionnaire.single_question;

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
import com.example.parkinson.features.questionnaire.QuestionnaireActivity;
import com.example.parkinson.features.questionnaire.QuestionnaireViewModel;
import com.example.parkinson.features.questionnaire.single_question.SingleQuestionMainAdapter.SingleQuestionMainAdapterListener;
import com.example.parkinson.features.questionnaire.single_question.models.MultipleChoiceAnswer;
import com.example.parkinson.model.question_models.MultipleChoiceQuestion;
import com.example.parkinson.model.question_models.OpenQuestion;
import com.example.parkinson.model.question_models.Question;

import java.util.List;

import javax.inject.Inject;

public class SingleQuestionFragment extends Fragment {

    @Inject
    QuestionnaireViewModel questionnaireViewModel;
    int position;

    SingleQuestionMainAdapter adapter;

    TextView question;
    RecyclerView recyclerView;

    public SingleQuestionFragment(int position) {
        super(R.layout.fragment_single_question);
        this.position = position;
        adapter = new SingleQuestionMainAdapter(new SingleQuestionMainAdapterListener() {
            @Override
            public void onMultipleChoiceAnswerChanged(List<MultipleChoiceAnswer> answers) {
                questionnaireViewModel.updateMultipleChoiceAnswer(position, answers);
            }

            @Override
            public void onOpenAnswerChanged(String answer) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((QuestionnaireActivity) getActivity()).questionnaireComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initViews(view);
        initUi();
        initObservers();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.singleQueFragQueRecycler);
        question = view.findViewById(R.id.singleQueFragQueTitle);
    }

    private void initUi() {
        handleQuestionData(questionnaireViewModel.getDataByPosition(position));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initObservers() {
//        questionnaireViewModel.questionData.observe(getViewLifecycleOwner(), this::handleQuestionData);
    }

    private void handleQuestionData(Question question){
        if(question != null){
            this.question.setText(question.getTitle());
            if (question instanceof OpenQuestion){
                adapter.updateSectionOpenAnswer();
            } else if (question instanceof MultipleChoiceQuestion){
                adapter.updateSectionMultiChoiceAnswers(((MultipleChoiceQuestion) question).isSingleChoice(), ((MultipleChoiceQuestion) question).getChoices());
            }
        }
    }

}
