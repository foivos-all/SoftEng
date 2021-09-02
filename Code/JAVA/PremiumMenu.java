package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class PremiumMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PopupMenu.OnMenuItemClickListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView hospitalCardView, pharmacyCardView, doctorCardView, rateCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_menu);

        drawerLayout = findViewById(R.id.drawer_premium_layout);
        navigationView = findViewById(R.id.nav_premium_view);
        toolbar = findViewById(R.id.premium_toolbar);
        hospitalCardView = (CardView) findViewById(R.id.premiumHospitalCard);
        pharmacyCardView = (CardView) findViewById(R.id.premiumPharmacyCard);
        doctorCardView = (CardView) findViewById(R.id.premiumDoctorCard);
        rateCardView = (CardView) findViewById(R.id.premiumRateCard);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_premium_home);

        hospitalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PremiumMenu.this, PatientNearbyHospitals.class);
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
                Intent intent = new Intent(PremiumMenu.this, PatientMakeAppointment.class);
                startActivity(intent);
            }
        });
        rateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatePopup(v);
            }
        });
    }

    public void showPharmacyPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.patient_pharmacy_popup_menu);
        popupMenu.show();
    }

    public void showRatePopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.premium_rate_popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nearbyPharmacies:
                Intent intent = new Intent(PremiumMenu.this, PatientNearbyPharmacies.class);
                startActivity(intent);
                break;
            case R.id.orderProducts:
                Intent intent2 = new Intent(PremiumMenu.this, PatientOrderProducts.class);
                startActivity(intent2);
                break;
            case R.id.ratePharmacies:
                Intent intent3 = new Intent(PremiumMenu.this, PremiumRatePharmacy.class);
                startActivity(intent3);
                break;
            case R.id.rateDoctor:
                Intent intent4 = new Intent(PremiumMenu.this, PremiumRateDoctor.class);
                startActivity(intent4);
                break;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_premium_home:
                break;
            case R.id.nav_premium_hospital:
                Intent nearbyHospitalsIntent = new Intent(PremiumMenu.this, PatientNearbyHospitals.class);
                startActivity(nearbyHospitalsIntent);
                break;
            case R.id.nav_premium_pharmacy:
                Intent nearbyPharmaciesIntent = new Intent(PremiumMenu.this, PatientNearbyPharmacies.class);
                startActivity(nearbyPharmaciesIntent);
                break;
            case R.id.nav_premium_doctor:
                Intent makeDoctorsAppointmentIntent = new Intent(PremiumMenu.this, PatientMakeAppointment.class);
                startActivity(makeDoctorsAppointmentIntent);
                break;
            case R.id.nav_premium_rate:
                Intent ratePharmacyIntent = new Intent(PremiumMenu.this, PremiumRatePharmacy.class);
                startActivity(ratePharmacyIntent);
                break;
        }

        return true;
    }
}