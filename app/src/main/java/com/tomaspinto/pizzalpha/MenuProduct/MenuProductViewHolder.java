package com.tomaspinto.pizzalpha.MenuProduct;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

public class MenuProductViewHolder extends RecyclerView.ViewHolder {

    Button product;

    public MenuProductViewHolder(@NonNull View itemView) {
        super(itemView);
        product = itemView.findViewById(R.id.pizzabtn);
    }
}
