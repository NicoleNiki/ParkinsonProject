package com.example.parkinson.features.questionnaire.single_question.binders;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.parkinson.R;
import com.example.parkinson.features.questionnaire.single_question.models.MultipleChoiceAnswer;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;


public class QuestionBinderChoiceAnswer extends
        ItemBinder<MultipleChoiceAnswer, QuestionBinderChoiceAnswer.viewHolder> {

    @Override
    public viewHolder createViewHolder(ViewGroup parent) {
        return new viewHolder(inflate(parent, R.layout.item_question_multi_choice));
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof MultipleChoiceAnswer;
    }

    @Override public int getSpanSize(int maxSpanCount) {
        return maxSpanCount;
    }

    @Override
    public void bindViewHolder(final viewHolder holder, final MultipleChoiceAnswer answer) {
        holder.answer.setText(answer.getAnswer());
        if(holder.isItemSelected()){
            holder.mainLayout.setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.getContext(), R.color.faded_green));
            holder.checkIcon.setVisibility(View.VISIBLE);
        } else {
            holder.mainLayout.setBackgroundColor(Color.TRANSPARENT);
            holder.checkIcon.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener( v -> holder.toggleItemSelection());
    }

    static class viewHolder extends ItemViewHolder<MultipleChoiceAnswer> {
        View view;
        LinearLayout mainLayout;
        ImageView checkIcon;
        TextView answer;

        public viewHolder(final View itemView) {
            super(itemView);
            view = itemView;
            mainLayout = itemView.findViewById(R.id.itemQueMultiChoiceLayout);
            checkIcon = itemView.findViewById(R.id.itemQueMultiChoiceCheckIcon);
            answer = itemView.findViewById(R.id.itemQueMultiChoiceAnswer);
        }
    }

}