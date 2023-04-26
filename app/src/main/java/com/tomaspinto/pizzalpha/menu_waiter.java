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
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.Table;
import com.tomaspinto.pizzalpha.ui.PopUpClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class menu_waiter extends AppCompatActivity {

    TextView textView;
    TextView price;
    TextView qty;
    ImageButton review;
    Order order;
    double total = 0;
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
        textView.setText(String.valueOf(table.tableId));

        // Creating a new order and setting its table
        order = new Order();
        order.date = new Date();
        order.table = table;
        order.waiter = "John";

        // Setting other variables
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        review = findViewById(R.id.review);

        review.setOnClickListener(v -> {
            if(Integer.parseInt(String.valueOf(qty.getText())) != 0)
            {
                Intent intent = new Intent(menu_waiter.this, reviewOrder.class);
                intent.putParcelableArrayListExtra("orderProducts", orderProducts);
                intent.putExtra("total", price.getText());
                startActivity(intent);
            }
        });

        createMenu();
    }

    public void createMenu()
    {
        // Setting Layout for menu
        ScrollView scrollView = findViewById(R.id.scroll);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(layout);

        SearchView searchView = findViewById(R.id.searchView);

        Queue<Button> q = createProductButtons();
        createGrid(q,layout);
    }

    public Queue<Button> createProductButtons()
    {
        orderProducts = new ArrayList<OrderProduct>();
        Queue<Button> buttonList = new LinkedList<Button>();
        List<Product> productList = db.getProductList();

        for(Product product : productList)
        {
            // Create Button Dynamically
            Button btn = new Button(this);
            btn.setText(product.name);
            btn.setTypeface(null, Typeface.BOLD);
            btn.setTextColor(getResources().getColor(R.color.white));
            if(product.category == Category.PIZZAS)
                btn.setBackgroundTintList(getColorStateList(R.color.orange));
            else
                btn.setBackgroundTintList(getColorStateList(R.color.black));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            params.weight = 0;
            btn.setLayoutParams(params);

            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            btn.setTextSize(15);
            btn.setWidth((int)(115 * scale));
            btn.setHeight((int)(115 * scale));

            btn.setOnClickListener(v -> {
                PopUpClass popUp = new PopUpClass();
                popUp.showQuantityWindow(v, product, order);

                // Setup the listener for this object
                popUp.setPopUpClassListener(new PopUpClass.PopUpClassListener() {
                    @Override
                    public void onAdd(ArrayList<OrderProduct> items) {
                        for(OrderProduct item : items)
                        {
                            total += item.o_product.basePrice;
                            // set price label to 2 decimal places value of total
                            price.setText("£" + String.format("%.2f", total));
                            orderProducts.add(item);
                        }

                        int currentQty = Integer.parseInt(String.valueOf(qty.getText()));
                        currentQty += items.size();
                        qty.setText(String.valueOf(currentQty));
                    }
                });
            });

            buttonList.add(btn);
        }

        return buttonList;
    }

    public void createGrid(Queue<Button> q, LinearLayout layout)
    {
        int numRows = q.size()/3 + q.size()%3;
        for(int i = numRows; i > 0; i--)
        {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            for(int j = 1; j <=3; j++)
            {
                Button btn = q.peek();
                row.addView(btn);
                q.remove();
            }

            layout.addView(row);
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

        alert.setNegativeButton(Html.fromHtml("<b>"+"No"+"</b>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();

    }
}