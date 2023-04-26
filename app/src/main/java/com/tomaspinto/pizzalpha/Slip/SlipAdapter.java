package com.tomaspinto.pizzalpha.Slip;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

import java.util.List;

public class SlipAdapter extends RecyclerView.Adapter<SlipViewHolder> {

    Context context;
    List<SlipItem> items;

    public SlipAdapter(Context context, List<SlipItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public SlipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlipViewHolder(LayoutInflater.from(context).inflate(R.layout.slip_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlipViewHolder holder, int position) {
        holder.product.setText(items.get(position).getProduct());
        holder.quantity.setText(items.get(position).getQuantity() + " x");
        holder.totalPrice.setText("£" + String.format("%.2f", items.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }
}
