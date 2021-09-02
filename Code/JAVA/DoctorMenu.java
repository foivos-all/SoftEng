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

public class DoctorMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView viewAppointmentsCardView, setHoursCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_menu);

        drawerLayout = findViewById(R.id.drawer_doctor_layout);
        navigationView = findViewById(R.id.doctor_nav_view);
        toolbar = findViewById(R.id.doctor_toolbar);
        viewAppointmentsCardView = (CardView) findViewById(R.id.viewAppointmentsCard);
        setHoursCardView = (CardView) findViewById(R.id.setHoursCard);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.pharmacist_nav_home);

        viewAppointmentsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorMenu.this, DoctorViewAppointments.class);
                startActivity(intent);
            }
        });

        setHoursCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorMenu.this, DoctorSetHours.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.doctor_nav_home:
                break;
            case R.id.doctor_nav_view_appointments:
                Intent intent = new Intent(DoctorMenu.this, DoctorViewAppointments.class);
                startActivity(intent);
                break;
            case R.id.doctor_nav_set_hours:
                Intent intent2 = new Intent(DoctorMenu.this, PharmacistViewOrders.class);
                startActivity(intent2);
                break;
        }

        return true;
    }
}