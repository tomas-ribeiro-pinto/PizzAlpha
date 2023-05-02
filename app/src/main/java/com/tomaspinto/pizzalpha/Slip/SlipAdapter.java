package com.tomaspinto.pizzalpha.Slip;


import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.MenuProduct.MenuProductAdapter;
import com.tomaspinto.pizzalpha.R;

import java.util.List;

public class SlipAdapter extends RecyclerView.Adapter<SlipViewHolder> {

    Context context;
    List<SlipItem> items;
    OnQuantityChangeListener listener;
    private AppRepository db;

    public SlipAdapter(Context context, List<SlipItem> items) {
        this.context = context;
        this.items = items;
        db = new AppRepository(AppDatabase.getDatabase(context));
    }

    @NonNull
    @Override
    public SlipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlipViewHolder(LayoutInflater.from(context).inflate(R.layout.slip_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlipViewHolder holder, int position) {
        holder.product.setText(items.get(position).getProduct());
        holder.quantity.setText(String.valueOf(items.get(position).getQuantity()));
        holder.totalPrice.setText("£" + String.format("%.2f", items.get(position).getPrice()));

        Product product = db.getProduct((String) holder.product.getText());

        holder.quantity.setOnEditorActionListener(new EditText.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(holder.quantity.getText().toString().isEmpty())
                        holder.quantity.setText("1");

                    int qty = Integer.parseInt(holder.quantity.getText().toString());
                    listener.onQuantityChangeListener(product, qty);
                    return true;
                }
                return false;
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                {
                    listener.onDeleteListener(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }

    public void setQuantityChangeListener(OnQuantityChangeListener listener) {
        this.listener = listener;
    }

    public interface OnQuantityChangeListener {

        public void onQuantityChangeListener(Product product, int qty);
        public void onDeleteListener(Product product);
    }
}
