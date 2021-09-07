package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class PatientNearbyPharmacies extends AppCompatActivity implements OnMapReadyCallback {
    boolean isPermissionGranted;
    GoogleMap mGoogleMap;
    SupportMapFragment supportMapFragment;
    private static final String BASE_URL= "http://10.0.2.2/getNearbyPharmacies.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_nearby_pharmacies);

        checkMyPermission();

        if (isPermissionGranted){
            supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nearby_pharmacies_map);
            supportMapFragment.getMapAsync(this);
        }
    }

    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri .fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMyLocationEnabled(true);
        String data[][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "nearbyPharmacies";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++){
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("name"); // column name
                data[i][1] = jsonObject.getString("lat"); // column name
                data[i][2] = jsonObject.getString("lng"); // column name
                String name = data[i][0];
                LatLng latLng = new LatLng(Double.parseDouble(data[i][1]), Double.parseDouble(data[i][2]));
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(name);
                mGoogleMap.addMarker(options);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}