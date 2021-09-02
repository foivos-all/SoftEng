package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    EditText etEmail, etPass;
    static String user_id;
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

    public void OnLogin(View view) {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        String type = "login";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            String receivedData = connectionHelper.execute(type, email, pass).get();
            user_id = receivedData.substring(0, 1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}