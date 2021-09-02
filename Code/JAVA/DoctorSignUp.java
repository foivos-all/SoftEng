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

public class DoctorSignUp extends AppCompatActivity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView diplomaToUpload;
    EditText etDoctorFullName, etDoctorSpecialty, etDoctorAddress, etDoctorPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        diplomaToUpload = (ImageView) findViewById(R.id.diploma);
        diplomaToUpload.setOnClickListener(this);

        etDoctorFullName = findViewById(R.id.doctorFullname);
        etDoctorSpecialty = findViewById(R.id.doctorSpecialty);
        etDoctorAddress = findViewById(R.id.doctorAddress);
        etDoctorPhone = findViewById(R.id.doctorPhone);
    }

    public void OnDoctorSignUp(View view){
        String fullname = etDoctorFullName.getText().toString();
        String specialty = etDoctorSpecialty.getText().toString();
        String address = etDoctorAddress.getText().toString();
        String phone = etDoctorPhone.getText().toString();

        String type = "doctorProfile";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, fullname, specialty, address, phone);
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