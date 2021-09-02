package com.example.healthcare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class PharmacistSignUp extends AppCompatActivity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView diplomaToUpload;
    EditText etPharmacistFullName, etPharmacistAddress, etPharmacistPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_sign_up);

        diplomaToUpload = (ImageView) findViewById(R.id.pdiploma);
        diplomaToUpload.setOnClickListener(this);

        etPharmacistFullName = findViewById(R.id.pharmacistFullname);
        etPharmacistAddress = findViewById(R.id.pharmacistAddress);
        etPharmacistPhone = findViewById(R.id.pharmacistPhone);
    }

    public void OnPharmacistSignUp(View view){
        String fullname = etPharmacistFullName.getText().toString();
        String address = etPharmacistAddress.getText().toString();
        String phone = etPharmacistPhone.getText().toString();

        String type = "pharmacistProfile";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, fullname, address, phone);
    }

    @Override
    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            diplomaToUpload.setImageURI(selectedImage);
        }
    }
}