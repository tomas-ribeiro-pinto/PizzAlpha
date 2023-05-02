package com.tomaspinto.pizzalpha.MenuProduct;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.Category;
import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.R;
import com.tomaspinto.pizzalpha.ui.PopUpClass;

import java.util.ArrayList;
import java.util.List;

public class MenuProductAdapter extends RecyclerView.Adapter<MenuProductViewHolder> {

    Context context;
    List<MenuProductItem> items;
    private MenuListener listener;
    private AppRepository db;

    public MenuProductAdapter(Context context, List<MenuProductItem> items) {
        this.context = context;
        this.items = items;
        db = new AppRepository(AppDatabase.getDatabase(context));
    }

    @NonNull
    @Override
    public MenuProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuProductViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuProductViewHolder holder, int position) {
        holder.product.setText(items.get(position).getProduct());
        Product product = db.getProduct((String) holder.product.getText());
        holder.product.setTypeface(null, Typeface.BOLD);
        holder.product.setTextSize(15);
        if(product.category == Category.PIZZAS)
            holder.product.setBackgroundTintList(context.getColorStateList(R.color.orange));
        else
            holder.product.setBackgroundTintList(context.getColorStateList(R.color.black));
        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                {
                    listener.onClickMenuItem(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }

    public interface MenuListener {

        public void onClickMenuItem(Product product);
    }
}
