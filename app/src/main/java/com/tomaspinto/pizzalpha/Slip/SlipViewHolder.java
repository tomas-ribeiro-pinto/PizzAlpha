package com.tomaspinto.pizzalpha.Slip;


import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

public class SlipViewHolder extends RecyclerView.ViewHolder {

    EditText quantity;
    TextView totalPrice;
    TextView product;
    Button delete;

    public SlipViewHolder(@NonNull View itemView) {
        super(itemView);
        quantity = itemView.findViewById(R.id.quantity);
        totalPrice = itemView.findViewById(R.id.table);
        product = itemView.findViewById(R.id.product);
        delete = itemView.findViewById(R.id.delete);
    }
}
