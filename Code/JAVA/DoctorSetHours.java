package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class DoctorSetHours extends AppCompatActivity implements View.OnClickListener{
    TimePicker timePickerFrom, timePickerTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_set_hours);

        timePickerFrom = (TimePicker) findViewById(R.id.doctorTimePickerFrom);
        timePickerTo = (TimePicker) findViewById(R.id.doctorTimePickerTo);
    }

    @Override
    public void onClick(View v) {
        String hourFrom = String.valueOf(timePickerFrom.getCurrentHour());
        String minFrom = String.valueOf(timePickerFrom.getCurrentMinute());
        String hourTo = String.valueOf(timePickerTo.getCurrentHour());
        String minTo = String.valueOf(timePickerTo.getCurrentMinute());

        String hoursFrom = hourFrom + ":" + minFrom;
        String hoursTo = hourTo + ":" + minTo;

        String hours = hoursFrom + " - " + hoursTo;

        String type = "setHours";
        System.out.println(hours);
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, Login.user_id, hours);

        Toast.makeText(this, "Οι ώρες σας άλλαξαν με επιτυχία!", Toast.LENGTH_LONG).show();
    }
}