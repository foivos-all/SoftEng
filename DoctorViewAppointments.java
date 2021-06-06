package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DoctorViewAppointments extends AppCompatActivity {
    LinearLayout viewAppointmentsLayoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_appointments);

        viewAppointmentsLayoutList = findViewById(R.id.view_appointments_layout_list);

        String data[][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getAppointments";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type, Login.user_id).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++){
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("id"); // column name
                String appointmentid = data[i][0];
                data[i][1] = jsonObject.getString("fullname"); // column name
                data[i][2] = jsonObject.getString("date_hour"); // column name

                View appointmentView = getLayoutInflater().inflate(R.layout.row_view_appointment, null, false);

                TextView etAppointmentDetails = (TextView) appointmentView.findViewById(R.id.appointment_from);
                ImageView imageAccept = (ImageView)  appointmentView.findViewById(R.id.image_accept_appointment);
                ImageView imageReject = (ImageView)  appointmentView.findViewById(R.id.image_reject_appointment);
                TextView etAppointmentHours = (TextView) appointmentView.findViewById(R.id.view_appointment_date_hour);

                String details = "Ραντεβού#"+data[i][0]+" από "+data[i][1];
                etAppointmentDetails.setText(details);
                etAppointmentHours.setText(data[i][2]);
                viewAppointmentsLayoutList.addView(appointmentView);

                imageAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateAppointment(appointmentid, "A");
                        viewAppointmentsLayoutList.removeView(appointmentView);
                    }
                });
                imageReject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateAppointment(appointmentid, "R");
                        viewAppointmentsLayoutList.removeView(appointmentView);
                    }
                });
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateAppointment(String appointmentid, String status) {
        String type = "updateAppointment";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, appointmentid, status);
    }
}