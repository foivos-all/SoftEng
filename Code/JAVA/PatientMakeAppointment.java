package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PatientMakeAppointment extends AppCompatActivity implements View.OnClickListener{

    ArrayList<String> doctorsList = new ArrayList<>();
    String doctor_id;
    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_make_appointment);

        AppCompatSpinner spinnerDoctor = (AppCompatSpinner) findViewById(R.id.spinner_doctor);
        timePicker = (TimePicker) findViewById(R.id.patientTimePicker);
        datePicker = (DatePicker) findViewById(R.id.patientDatePicker);

        String data[][] = null, receivedData = "";
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
                doctorsList.add(data[i][1]);
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
        spinnerDoctor.setAdapter(arrayAdapter);
        String[][] finalData = data;
        spinnerDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctor_id = finalData[position][0];
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        String hour = String.valueOf(timePicker.getCurrentHour());
        String min = String.valueOf(timePicker.getCurrentMinute());
        String day = String.valueOf(datePicker.getDayOfMonth());
        String month = String.valueOf(datePicker.getMonth() + 1);
        String year = String.valueOf(datePicker.getYear());

        String hours = hour + ":" + min;
        String date = day + "/" + month + "/" + year;
        String appointment = date + " " + hours;

        String type = "makeAppointment";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, doctor_id, Login.user_id, appointment);
    }
}