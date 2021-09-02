package com.example.healthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PremiumRatePharmacy extends AppCompatActivity {

    ArrayList<String> pharmaciesList = new ArrayList<>();
    RatingBar ratingBar;
    Button submitRating;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_rate_pharmacy);

        AppCompatSpinner spinnerPharmacies = (AppCompatSpinner) findViewById(R.id.spinner_pharmacies);
        ratingBar = (RatingBar) findViewById(R.id.pharmacyRatingBar);
        submitRating = (Button) findViewById(R.id.button_submit_rating);

        String data[][] = new String[0][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getPharmacies";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++){
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("id"); // column name
                data[i][1] = jsonObject.getString("name"); // column name
                String details = data[i][0] + " " + data[i][1];
                pharmaciesList.add(details);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pharmaciesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPharmacies.setAdapter(arrayAdapter);
        spinnerPharmacies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                ratingBar.setRating(0F);
                ratingBar.setFocusable(true);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        String[][] finalData = data;
        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = String.valueOf(ratingBar.getRating());
                System.out.println(finalData[selected][0]);
                String pharmacy_id = finalData[selected][0];
                String type = "ratePharmacy";
                ConnectionHelper connectionHelper = new ConnectionHelper(PremiumRatePharmacy.this);
                connectionHelper.execute(type, pharmacy_id, rating);
            }
        });
    }
}