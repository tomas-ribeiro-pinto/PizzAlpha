package com.tomaspinto.pizzalpha.OrderDetail;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailViewHolder> {

    Context context;
    List<OrderDetailItem> items;

    public OrderDetailAdapter(Context context, List<OrderDetailItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.orders_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        String pattern = "dd-MM-yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        holder.date.setText(simpleDateFormat.format(items.get(position).getDate()));
        holder.orderId.setText("#" + items.get(position).getId());
        holder.totalPrice.setText("£" + String.format("%.2f", items.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }
}
