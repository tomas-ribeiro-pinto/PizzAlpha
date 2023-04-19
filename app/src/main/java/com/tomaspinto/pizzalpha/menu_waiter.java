package com.tomaspinto.pizzalpha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.Table;

import java.util.ArrayList;
import java.util.Date;

public class menu_waiter extends AppCompatActivity {

    TextView textView;
    TextView price;
    TextView qty;
    ImageButton review;
    Order order;
    double total = 0;
    int currentQty = 0;
    ArrayList<OrderProduct> orderProducts;
    private AppRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_waiter);

        db = new AppRepository(AppDatabase.getDatabase(getApplicationContext()));

        // Setting this order table to table retrieved from last Intent
        textView = findViewById(R.id.tableLabel);
        Bundle bundle = getIntent().getExtras();
        Table table = bundle.getParcelable("table");
        textView.setText(table.tableName);

        // Setting other variables
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        review = findViewById(R.id.review);

        review.setOnClickListener(v -> {
            Intent intent = new Intent(menu_waiter.this, reviewOrder.class);
            intent.putParcelableArrayListExtra("orderProducts", orderProducts);
            intent.putExtra("total", price.getText());
            startActivity(intent);
        });

        createMenu(table);
    }

    public void createMenu(Table table)
    {
        LinearLayout layout = findViewById(R.id.layout);
        LinearLayout layout1 = findViewById(R.id.layout2);
        LinearLayout layout2 = findViewById(R.id.layout3);

        ArrayList<Button> buttonList = new ArrayList<>();
        orderProducts = new ArrayList<OrderProduct>();

        int count = 1;

        for(Product product : db.getProductList())
        {
            // Create Button Dynamically
            Button btn = new Button(layout.getContext());
            btn.setText(product.name);
            btn.setTypeface(null, Typeface.BOLD);
            btn.setTextColor(getResources().getColor(R.color.white));
            if(product.category == Category.PIZZAS)
                btn.setBackgroundTintList(getColorStateList(R.color.orange));
            else
                btn.setBackgroundTintList(getColorStateList(R.color.black));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            btn.setLayoutParams(params);

            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            btn.setTextSize(15);
            btn.setWidth((int)(115 * scale));
            btn.setHeight((int)(115 * scale));

            btn.setOnClickListener(v -> {
                // Creating a new order and setting its table
                order = new Order();
                order.date = new Date();
                order.table = table;
                order.waiter = "John";

                OrderProduct item = new OrderProduct();
                item.order = order;
                item.o_product = product;
                currentQty++;
                qty.setText(String.valueOf(currentQty));
                total += item.o_product.basePrice;
                // set price label to 2 decimal places value of total
                price.setText("£" + String.format("%.2f", total));
                orderProducts.add(item);
            });

            // Add Button to LinearLayout
            if (layout != null) {
                if(count < 4)
                    layout.addView(btn);
                else if (count < 7)
                    layout1.addView(btn);
                else
                    layout2.addView(btn);
            }
            count++;

        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Do you want to cancel the order?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alert.setNegativeButton(Html.fromHtml("<b>"+"Cancel"+"</b>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();

    }
}