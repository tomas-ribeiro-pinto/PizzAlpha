package com.tomaspinto.pizzalpha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.Table;
import com.tomaspinto.pizzalpha.MenuProduct.MenuProductAdapter;
import com.tomaspinto.pizzalpha.MenuProduct.MenuProductItem;
import com.tomaspinto.pizzalpha.Slip.SlipAdapter;
import com.tomaspinto.pizzalpha.Slip.SlipItem;
import com.tomaspinto.pizzalpha.ui.PopUpClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;

public class menu_waiter extends AppCompatActivity {

    SearchView searchView;
    TextView textView;
    TextView price;
    TextView qty;
    ImageButton review;
    Order order;
    Spinner dropdown;
    double total = 0;
    ArrayList<OrderProduct> orderProducts;
    private AppRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_waiter);

        db = new AppRepository(AppDatabase.getDatabase(getApplicationContext()));

        searchView = findViewById(R.id.searchView);

        // Setting this order table to table retrieved from last Intent
        textView = findViewById(R.id.tableLabel);
        Bundle bundle = getIntent().getExtras();
        Table table = bundle.getParcelable("table");
        textView.setText(String.valueOf(table.tableId));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Menu - " + table.tableName);

        // Creating a new order
        order = new Order();

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
                intent.putExtra("table", table);
                startActivityForResult(intent, 2);
            }
        });

        dropdown = findViewById(R.id.filter);
        //create a list of items for the spinner.
        String[] items = new String[]{"All", "Drinks", "Pizzas"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        List<Product> products = db.getProductList();

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                searchView.setQuery("", true);

                if (parent.getSelectedItemPosition() == 0)
                {
                    createMenuView(products);
                }
                else if (parent.getSelectedItemPosition() == 1)
                {
                    List<Product> drinks = new ArrayList<>();
                    for(Product product : products)
                    {
                        if(product.category == Category.DRINKS)
                            drinks.add(product);
                    }
                    createMenuView(drinks);
                }
                else if (parent.getSelectedItemPosition() == 2)
                {
                    List<Product> pizzas = new ArrayList<>();
                    for(Product product : products)
                    {
                        if(product.category == Category.PIZZAS)
                            pizzas.add(product);
                    }
                    createMenuView(pizzas);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                createMenuView(products);
            }
        });

        createMenuView(products);
    }

    // Call Back method  to get the orderProducts from other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            orderProducts = data.getParcelableArrayListExtra("orderProducts");
            qty.setText(String.valueOf(orderProducts.size()));
            total = Order.getOrderTotal(orderProducts);
            price.setText("£" + String.format("%.2f", total));
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

    public void createMenuView(List<Product> products)
    {
        orderProducts = new ArrayList<OrderProduct>();
        List<MenuProductItem> items = new ArrayList<MenuProductItem>();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.clearFocus();
                return true;
            }
        });

        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                searchView.clearFocus();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<MenuProductItem> newItems = new ArrayList<>();
                for(MenuProductItem product : items)
                {
                    if (product.getProduct().toLowerCase().contains(newText.toLowerCase()))
                        newItems.add(product);
                }

                setRecyclerView(newItems);

                return true;
            }
        });

        // Take List and add labels to review order
        for(Product product : products)
        {
            items.add(new MenuProductItem(product.name, product.basePrice));
        }

        setRecyclerView(items);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }

    public void setRecyclerView(List<MenuProductItem> items)
    {
        RecyclerView recyclerView = findViewById(R.id.menu_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        MenuProductAdapter adapter = new MenuProductAdapter(getApplicationContext(),items);
        recyclerView.setAdapter(adapter);
        View view = recyclerView.getRootView();
        adapter.setMenuListener(new MenuProductAdapter.MenuListener() {
            @Override
            public void onClickMenuItem(Product product) {
                if (searchView.hasFocus()) searchView.clearFocus();

                PopUpClass popUp = new PopUpClass();
                popUp.showQuantityWindow(view, product, order);

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
            }
        });
    }
}