package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etEmail, etPass;
    String text, property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner spinner = findViewById(R.id.SignUpProperty);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.properties, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        etEmail = findViewById(R.id.SignUpEmail);
        etPass = findViewById(R.id.SignUpPassword);

        View account = findViewById(R.id.accountText);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void OnLogin(View view){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        if (text.equals("Ασθενής"))
            property = "A";
        else if (text.equals("Ιατρός"))
            property = "G";
        else if (text.equals("Φαρμακοποιός"))
            property = "F";

        String type = "register";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, email, pass, property);

        if (property == "A"){
            Intent intent = new Intent(SignUp.this, PatientSignUp.class);
            startActivity(intent);
        } else if (property == "G"){
            Intent intent = new Intent(SignUp.this, DoctorSignUp.class);
            startActivity(intent);
        } else if (property == "F"){
            Intent intent = new Intent(SignUp.this, PharmacistSignUp.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}