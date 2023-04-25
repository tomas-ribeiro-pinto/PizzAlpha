package com.tomaspinto.pizzalpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.ProductDao;

import java.util.ArrayList;
import java.util.HashMap;

public class reviewOrder extends AppCompatActivity {

    AppRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new AppRepository(AppDatabase.getDatabase(getApplicationContext()));

        setContentView(R.layout.activity_review_order);
        Bundle bundle = getIntent().getExtras();
        ArrayList<OrderProduct> orderProducts = bundle.getParcelableArrayList("orderProducts");
        Order order = orderProducts.get(0).order;

        HashMap<Integer,Integer> idSlip = OrderProduct.getSlipDetails(orderProducts);

        // Take List and add labels to review order
        for(HashMap.Entry<Integer, Integer> entry : idSlip.entrySet())
        {
            Product item = db.getProduct(entry.getKey());

            int qty = entry.getValue();

            TextView textView = new TextView(this);
            // create product label
            String orderDetails = qty + " x " + item.name + " - " + String.format("%.2f", item.basePrice*qty);

            LinearLayout layout = findViewById(R.id.layout);
            layout.addView(textView);
            textView.setText(orderDetails);
        }

        String total = bundle.getString("total");
        TextView price = findViewById(R.id.price2);
        price.setText(total);
    }
}