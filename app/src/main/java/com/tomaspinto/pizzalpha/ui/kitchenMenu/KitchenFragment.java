package com.tomaspinto.pizzalpha.ui.kitchenMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.KitchenOrder.KitchenOrderAdapter;
import com.tomaspinto.pizzalpha.KitchenOrder.KitchenOrderItem;
import com.tomaspinto.pizzalpha.KitchenOrder.KitchenOrderViewHolder;
import com.tomaspinto.pizzalpha.R;
import com.tomaspinto.pizzalpha.databinding.FragmentKitchenBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KitchenFragment extends Fragment {

    private FragmentKitchenBinding binding;
    private AppRepository db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        KitchenViewModel galleryViewModel =
                new ViewModelProvider(this).get(KitchenViewModel.class);

        binding = FragmentKitchenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = new AppRepository(AppDatabase.getDatabase(getContext()));

        createKitchenList();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void createKitchenList()
    {
        List<Order> orders = db.getOrderList();

        if(!orders.isEmpty())
        {
            RecyclerView recyclerView = binding.getRoot().findViewById(R.id.kitchenList);

            List<KitchenOrderItem> items = new ArrayList<>();

            // Take List and add labels to review order
            for(Order order : orders)
            {
                if(!order.is_cooked)
                {
                    int id = order.orderId;

                    Date currentDate = new Date();
                    String seconds = String.valueOf(((currentDate.getTime()-order.date.getTime())/1000));

                    String table = String.valueOf(order.table.tableName);

                    items.add(new KitchenOrderItem(id,seconds,table));
                }
            }

            KitchenOrderAdapter adapter = new KitchenOrderAdapter(getContext(),items);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
    }
}