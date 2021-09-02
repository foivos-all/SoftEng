package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PatientSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText etPatientFullName, etPatientAge, etPatientAddress, etPatientPhone, etPatientInfo;
    String text, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);

        Spinner spinner = findViewById(R.id.patientGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        etPatientFullName = findViewById(R.id.patientFullname);
        etPatientAge = findViewById(R.id.patientAge);
        etPatientAddress = findViewById(R.id.patientAddress);
        etPatientPhone = findViewById(R.id.patientPhone);
        etPatientInfo = findViewById(R.id.patientInfo);
    }

    public void OnPatientSignUp(View view){
        String fullname = etPatientFullName.getText().toString();
        String age = etPatientAge.getText().toString();
        String address = etPatientAddress.getText().toString();
        String phone = etPatientPhone.getText().toString();
        String info = etPatientInfo.getText().toString();

        if (text.equals("Αρσενικό"))
            gender = "M";
        else
            gender = "F";

        String type = "patientProfile";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, fullname, age, gender, address, phone, info);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}