package com.tomaspinto.pizzalpha.Slip;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

public class SlipViewHolder extends RecyclerView.ViewHolder {

    TextView quantity;
    TextView totalPrice;
    TextView product;

    public SlipViewHolder(@NonNull View itemView) {
        super(itemView);
        quantity = itemView.findViewById(R.id.quantity);
        totalPrice = itemView.findViewById(R.id.table);
        product = itemView.findViewById(R.id.product);
    }
}
