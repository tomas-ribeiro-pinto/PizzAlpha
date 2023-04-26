package com.tomaspinto.pizzalpha.OrderDetail;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

public class OrderDetailViewHolder extends RecyclerView.ViewHolder {

    TextView orderId;
    TextView totalPrice;
    TextView date;

    public OrderDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        orderId = itemView.findViewById(R.id.order);
        totalPrice = itemView.findViewById(R.id.table);
        date = itemView.findViewById(R.id.date);
    }
}
