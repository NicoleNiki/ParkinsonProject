package com.example.parkinson.features.medicine.add_new_medicine.medication_list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinson.R;
import com.example.parkinson.model.general_models.Medication;
import com.example.parkinson.model.general_models.MedicationCategory;

import java.util.List;


public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.ViewHolder> {

    MedicationCategory category;
    MedicineListAdapterListener listener;

    interface MedicineListAdapterListener {
        void onMedicineClick(Medication medication);
    }

    public MedicineListAdapter(MedicationCategory category, MedicineListAdapterListener listener) {
        this.category = category;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return category.getMedicationList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Medication medicine = category.getMedicationList().get(position);
        holder.name.setText(medicine.getName());
        holder.itemView.setOnClickListener(v -> {
            listener.onMedicineClick(medicine);
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemMedicineName);
        }
    }

}