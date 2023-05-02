package com.tomaspinto.pizzalpha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.ProductDao;
import com.tomaspinto.pizzalpha.Data.Table;
import com.tomaspinto.pizzalpha.Slip.SlipAdapter;
import com.tomaspinto.pizzalpha.Slip.SlipItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class reviewOrder extends AppCompatActivity {

    AppRepository db;
    ArrayList<OrderProduct> orderProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new AppRepository(AppDatabase.getDatabase(getApplicationContext()));

        setContentView(R.layout.activity_review_order);
        Bundle bundle = getIntent().getExtras();
        orderProducts = bundle.getParcelableArrayList("orderProducts");
        Table table = bundle.getParcelable("table");
        Order order = orderProducts.get(0).order;

        String total = bundle.getString("total");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Review Order");

        createReviewSlip(total);

        Button sendButton = findViewById(R.id.sendButton);
        Button editButton = findViewById(R.id.editButton);

        sendButton.setOnClickListener(v -> {
            order.date = new Date();
            order.table = table;
            order.waiter = "John";
            db.insert(order);
            Order orderSaved = db.getOrderList().get(db.getOrderList().size() - 1);
            for(OrderProduct product : orderProducts)
            {
                product.order = orderSaved;
                db.insert(product);
            }
            Intent intent = new Intent(reviewOrder.this, MainActivity.class);
            startActivity(intent);
        });

        editButton.setOnClickListener(v -> {
            finish();
        });

    }

    public void createReviewSlip(String total)
    {
        HashMap<Integer,Integer> idSlip = OrderProduct.getSlipDetails(orderProducts);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<SlipItem> items = new ArrayList<SlipItem>();

        // Take List and add labels to review order
        for(HashMap.Entry<Integer, Integer> entry : idSlip.entrySet())
        {
            Product item = db.getProduct(entry.getKey());

            int qty = entry.getValue();

            double price = qty * item.basePrice;

            items.add(new SlipItem(qty,item.name,price));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SlipAdapter(getApplicationContext(),items));

        TextView price = findViewById(R.id.price2);
        price.setText(total);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}