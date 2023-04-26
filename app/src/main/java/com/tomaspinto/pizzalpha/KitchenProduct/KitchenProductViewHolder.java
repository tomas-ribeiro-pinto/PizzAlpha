package com.tomaspinto.pizzalpha.KitchenProduct;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

public class KitchenProductViewHolder extends RecyclerView.ViewHolder {

    TextView quantity;
    TextView product;

    public KitchenProductViewHolder(@NonNull View itemView) {
        super(itemView);
        quantity = itemView.findViewById(R.id.kitchenQuantity);
        product = itemView.findViewById(R.id.kitchenProduct);
    }
}
