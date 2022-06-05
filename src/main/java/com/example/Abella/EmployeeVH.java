package com.example.Abella;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeVH extends RecyclerView.ViewHolder {
    public TextView txt_name,txt_age,txt_gender,txt_option;
    public EmployeeVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_age = itemView.findViewById(R.id.txt_age);
        txt_gender = itemView.findViewById(R.id.txt_gender);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}