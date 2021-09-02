package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
//import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class PatientMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView hospitalCardView, pharmacyCardView, doctorCardView, premiumCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        hospitalCardView = (CardView) findViewById(R.id.hospitalCard);
        pharmacyCardView = (CardView) findViewById(R.id.pharmacyCard);
        doctorCardView = (CardView) findViewById(R.id.doctorCard);
        premiumCardView = (CardView) findViewById(R.id.premiumCard);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.pharmacist_nav_home);

        hospitalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMenu.this, PatientNearbyHospitals.class);
                startActivity(intent);
            }
        });
        pharmacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPharmacyPopup(v);
            }
        });
        doctorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMenu.this, PatientMakeAppointment.class);
                startActivity(intent);
            }
        });
        premiumCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMenu.this, PatientUpgradeToPremium.class);
                startActivity(intent);
            }
        });
    }
    public void showPharmacyPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.patient_pharmacy_popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nearbyPharmacies:
                Intent intent = new Intent(PatientMenu.this, PatientNearbyPharmacies.class);
                startActivity(intent);
                break;
            case R.id.orderProducts:
                Intent intent2 = new Intent(PatientMenu.this, PatientOrderProducts.class);
                startActivity(intent2);
                break;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_hospital:
                Intent nearbyHospitalsIntent = new Intent(PatientMenu.this, PatientNearbyHospitals.class);
                startActivity(nearbyHospitalsIntent);
                break;
            case R.id.nav_pharmacy:
                Intent nearbyPharmaciesIntent = new Intent(PatientMenu.this, PatientNearbyPharmacies.class);
                startActivity(nearbyPharmaciesIntent);
                break;
            case R.id.nav_doctor:
                Intent makeDoctorsAppointmentIntent = new Intent(PatientMenu.this, PatientMakeAppointment.class);
                startActivity(makeDoctorsAppointmentIntent);
                break;
        }

        return true;
    }
}