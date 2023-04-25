package com.tomaspinto.pizzalpha;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.DbSeeder;
import com.tomaspinto.pizzalpha.Data.Product;
import com.tomaspinto.pizzalpha.Data.Table;
import com.tomaspinto.pizzalpha.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private AppRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new AppRepository(AppDatabase.getDatabase(getApplicationContext()));

        if (savedInstanceState != null) {
            createTables();
        }
        // When creating Activity seed db in case is not seeded
        DbSeeder.seedDb(db);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createTables();

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void createTables()
    {
        LinearLayout layout = findViewById(R.id.layout);
        LinearLayout layout1 = findViewById(R.id.layout2);
        LinearLayout layout2 = findViewById(R.id.layout3);

        ArrayList<Button> buttonList = new ArrayList<>();

        int count = 1;

        for(Table table : db.getTableList())
        {
            // Create Button Dynamically
            Button btn = new Button(layout.getContext());
            btn.setText(table.tableName);
            btn.setTextSize(20);
            btn.setTypeface(null, Typeface.BOLD);
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundTintList(getColorStateList(R.color.orange));
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            btn.setWidth((int)(115 * scale));
            btn.setHeight((int)(115 * scale));

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, menu_waiter.class);
                    intent.putExtra("table", table);
                    startActivity(intent);
                }
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
}