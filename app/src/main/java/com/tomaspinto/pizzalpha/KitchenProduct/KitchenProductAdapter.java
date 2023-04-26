package com.tomaspinto.pizzalpha.KitchenProduct;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

import java.util.List;

public class KitchenProductAdapter extends RecyclerView.Adapter<KitchenProductViewHolder> {

    Context context;
    List<KitchenProductItem> items;

    public KitchenProductAdapter(Context context, List<KitchenProductItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public KitchenProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KitchenProductViewHolder(LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.kitchen_slip_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenProductViewHolder holder, int position) {
        holder.product.setText(items.get(position).getProduct());
        holder.quantity.setText(items.get(position).getQuantity() + " x");
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }
}
