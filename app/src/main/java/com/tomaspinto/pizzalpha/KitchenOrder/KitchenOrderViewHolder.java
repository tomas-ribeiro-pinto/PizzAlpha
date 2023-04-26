package com.tomaspinto.pizzalpha.KitchenOrder;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.R;

public class KitchenOrderViewHolder extends RecyclerView.ViewHolder {

    TextView order;
    TextView table;
    TextView timer;
    ConstraintLayout tcolor;
    ConstraintLayout layout;
    public RecyclerView recycler;

    public KitchenOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.card);
        recycler = itemView.findViewById(R.id.kitchenItemList);
        order = itemView.findViewById(R.id.k_order);
        table = itemView.findViewById(R.id.table);
        timer = itemView.findViewById(R.id.timer);
        tcolor = itemView.findViewById(R.id.timerIndicator);
    }
}
