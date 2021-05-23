package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class PharmacistMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView addProductsCardView, viewOrdersCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_menu);

        drawerLayout = findViewById(R.id.drawer_pharmacist_layout);
        navigationView = findViewById(R.id.pharmacist_nav_view);
        toolbar = findViewById(R.id.pharmacist_toolbar);
        addProductsCardView = (CardView) findViewById(R.id.addProductsCard);
        viewOrdersCardView = (CardView) findViewById(R.id.viewOrdersCard);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.pharmacist_nav_home);

        addProductsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PharmacistMenu.this, PharmacistsAddProducts.class);
                startActivity(intent);
            }
        });

        viewOrdersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PharmacistMenu.this, PharmacistViewOrders.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.pharmacist_nav_home:
                break;
            case R.id.pharmacist_nav_add_products:
                Intent intent = new Intent(PharmacistMenu.this, PharmacistsAddProducts.class);
                startActivity(intent);
                break;
            case R.id.pharmacist_nav_view_orders:
                Intent intent2 = new Intent(PharmacistMenu.this, PharmacistViewOrders.class);
                startActivity(intent2);
                break;
        }

        return true;
    }
}