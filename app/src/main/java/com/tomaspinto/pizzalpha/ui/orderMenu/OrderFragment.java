package com.tomaspinto.pizzalpha.ui.orderMenu;

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
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.OrderDetail.OrderDetailAdapter;
import com.tomaspinto.pizzalpha.OrderDetail.OrderDetailItem;
import com.tomaspinto.pizzalpha.R;
import com.tomaspinto.pizzalpha.databinding.FragmentOrdersBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private FragmentOrdersBinding binding;
    private AppRepository db;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrderViewModel slideshowViewModel =
                new ViewModelProvider(this).get(OrderViewModel.class);

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        v = binding.getRoot();

        db = new AppRepository(AppDatabase.getDatabase(getContext()));

        createOrderList();

        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void createOrderList()
    {
        List<Order> orders = db.getOrderList();

        if(!orders.isEmpty())
        {
            RecyclerView recyclerView = binding.getRoot().findViewById(R.id.ordersList);

            List<OrderDetailItem> items = new ArrayList<>();

            // Take List and add labels to review order
            for(Order order : orders)
            {
                List<OrderProduct> orderProducts = db.getOrderProductList(order.orderId);

                int id = order.orderId;

                double price = Order.getOrderTotal(orderProducts);

                items.add(new OrderDetailItem(id,order.date,price));
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new OrderDetailAdapter(getContext(),items));
        }
    }
}