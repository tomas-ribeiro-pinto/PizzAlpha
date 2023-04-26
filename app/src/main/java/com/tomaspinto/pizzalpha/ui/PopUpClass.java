package com.tomaspinto.pizzalpha.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tomaspinto.pizzalpha.Category;
import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.Order;
import com.tomaspinto.pizzalpha.Data.OrderProduct;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.ProductIngredient;
import com.tomaspinto.pizzalpha.R;
import com.tomaspinto.pizzalpha.menu_waiter;

import java.util.ArrayList;
import java.util.List;

public class PopUpClass {

    ArrayList<OrderProduct> items;
    private PopUpClassListener listener;

    private AppRepository db;


    // Step 1 - This interface defines the type of messages I want to communicate to my owner
    public interface PopUpClassListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        public void onAdd(ArrayList<OrderProduct> items);
    }

    // Constructor where listener events are ignored
    public PopUpClass() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
    }

    // Assign the listener implementing events interface that will receive the events
    public void setPopUpClassListener(PopUpClassListener listener) {
        this.listener = listener;
    }

    //PopupWindow display method

    public void showQuantityWindow(final View view, Product product, Order order) {

        db = new AppRepository(AppDatabase.getDatabase(view.getContext()));

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.qty_menu, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler
        //Initialize list of products to be added to bill

        TextView productLabel = popupView.findViewById(R.id.productName);
        TextView ingredientsLabel = popupView.findViewById(R.id.ingredients);
        EditText currentQty = popupView.findViewById(R.id.itemQty);

        ArrayList<OrderProduct> items = new ArrayList<>();

        productLabel.setText(product.name);

        List<ProductIngredient> ingredients = db.getProductIngredientList(product.productId);

        String description = (String) ingredientsLabel.getText();

        for(ProductIngredient ing : ingredients)
        {
            // if ingredient is last don't include final comma
            if(ing == ingredients.get(ingredients.size()-1))
            {
                description += ing.ingredient.name + ".";
            }
            else
            {
                description += ing.ingredient.name + ", ";
            }

            ingredientsLabel.setText(description);
        }

        if(product.category == Category.DRINKS)
        {
            TextView ingredientsBold = popupView.findViewById(R.id.ingredientsBold);
            ingredientsBold.setText("Size: ");
            description = product.size;
            ingredientsLabel.setText(description);
        }

        Button addButton = popupView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(String.valueOf(currentQty.getText()));
                for(int i = current; i > 0 ;i--)
                {
                    OrderProduct item = new OrderProduct();
                    item.order = order;
                    item.o_product = product;
                    items.add(item);
                }

                if (listener != null)
                    listener.onAdd(items);

                popupWindow.dismiss();
            }
        });

        //Handler for clicking on plus or minus buttons

        Button plusButton = popupView.findViewById(R.id.plusButton);
        Button minusButton = popupView.findViewById(R.id.minusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(String.valueOf(currentQty.getText()));
                if(current < 10)
                {
                    current++;
                    currentQty.setText(String.valueOf(current));
                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(String.valueOf(currentQty.getText()));
                if(current > 1)
                {
                    current--;
                    currentQty.setText(String.valueOf(current));
                }
            }
        });


        //Handler for clicking on the inactive zone of the window

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

    }
}