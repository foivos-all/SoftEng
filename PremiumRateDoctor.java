package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PremiumRateDoctor extends AppCompatActivity {

    ArrayList<String> doctorsList = new ArrayList<>();
    RatingBar ratingBar;
    Button submitRating;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_rate_doctor);

        AppCompatSpinner spinnerDoctors = (AppCompatSpinner) findViewById(R.id.spinner_doctors);
        ratingBar = (RatingBar) findViewById(R.id.doctorRatingBar);
        submitRating = (Button) findViewById(R.id.button_submit_doctor_rating);

        String data[][] = new String[0][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getDoctors";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++){
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("user_id"); // column name
                data[i][1] = jsonObject.getString("fullname"); // column name
                String details = data[i][0] + " " + data[i][1];
                doctorsList.add(details);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, doctorsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctors.setAdapter(arrayAdapter);
        spinnerDoctors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                String doctor_id = finalData[selected][0];
                String type = "rateDoctor";
                ConnectionHelper connectionHelper = new ConnectionHelper(PremiumRateDoctor.this);
                connectionHelper.execute(type, doctor_id, rating);
            }
        });
    }
}