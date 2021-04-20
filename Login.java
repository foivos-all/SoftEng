package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText etEmail, etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.Email);
        etPass = findViewById(R.id.Password);

        View signup = findViewById(R.id.SignUpText);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void OnLogin(View view){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        String type = "login";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, email, pass);

    }
}