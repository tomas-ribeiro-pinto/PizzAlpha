package com.tomaspinto.pizzalpha.KitchenOrder;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.KitchenProduct.KitchenProductAdapter;
import com.tomaspinto.pizzalpha.KitchenProduct.KitchenProductItem;
import com.tomaspinto.pizzalpha.R;
import com.tomaspinto.pizzalpha.Slip.SlipAdapter;
import com.tomaspinto.pizzalpha.Slip.SlipItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class KitchenOrderAdapter extends RecyclerView.Adapter<KitchenOrderViewHolder> {

    Context context;
    List<KitchenOrderItem> items;

    public KitchenOrderAdapter(Context context, List<KitchenOrderItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public KitchenOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KitchenOrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kitchen_slip_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenOrderViewHolder holder, int position) {
        holder.timer.setText(items.get(position).getSeconds());
        int time = Integer.parseInt(items.get(position).getSeconds());
        if(time > 300 && time <= 600)
        {
            holder.tcolor.setBackgroundTintList(context.getColorStateList(R.color.yellow));
        }
        else if(time > 600)
        {
            holder.tcolor.setBackgroundTintList(context.getColorStateList(R.color.red));
        }
        holder.order.setText("#" + items.get(position).getId());
        holder.table.setText(items.get(position).getTable());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String str = (String) holder.order.getText();
                String[] arrOfStr = str.split("#", 2);
                int id = Integer.parseInt(arrOfStr[1]);
                AppRepository db = new AppRepository(AppDatabase.getDatabase(v.getContext()));

                db.updateOrder(true, id);
                setData(db.getOrderList());
            }
        });

        AppRepository db = new AppRepository(AppDatabase.getDatabase(context.getApplicationContext()));

        List<OrderProduct> orderProducts = db.getOrderProductList(items.get(position).getId());

        HashMap<Integer,Integer> idSlip = OrderProduct.getSlipDetails(orderProducts);

        List<KitchenProductItem> items = new ArrayList<KitchenProductItem>();

        // Take List and add labels to review order
        for(HashMap.Entry<Integer, Integer> entry : idSlip.entrySet())
        {
            Product item = db.getProduct(entry.getKey());

            int qty = entry.getValue();

            items.add(new KitchenProductItem(qty,item.name));
        }

        holder.recycler.setLayoutManager(new LinearLayoutManager(context));
        holder.recycler.setAdapter(new KitchenProductAdapter(holder.recycler.getContext(),items));

    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }

    public void setData(List<Order> orders) {
        if (!orders.isEmpty()) {
            List<KitchenOrderItem> itemsChanged = new ArrayList<>();

            // Take List and add labels to review order
            for (Order order : orders) {
                if (!order.is_cooked) {
                    int id = order.orderId;

                    Date currentDate = new Date();
                    String seconds = String.valueOf(((currentDate.getTime() - order.date.getTime()) / 1000));

                    String table = String.valueOf(order.table.tableName);

                    itemsChanged.add(new KitchenOrderItem(id, seconds, table));
                }
            }
            this.items = itemsChanged;
            notifyDataSetChanged();
        }
    }

}
