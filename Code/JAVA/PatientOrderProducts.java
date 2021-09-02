package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PatientOrderProducts extends AppCompatActivity implements View.OnClickListener{

    ArrayList<String> pharmacistsList = new ArrayList<>();
    String pharmacist_id;
    LinearLayout layoutList;
    LinearLayout orderedLayoutList;
    TextView orderedProductsTitle;
    Button buttonSendOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_order_products);

        AppCompatSpinner spinnerProducts = (AppCompatSpinner) findViewById(R.id.spinner_products);
        layoutList = findViewById(R.id.order_products_layout_list);
        orderedLayoutList = findViewById(R.id.ordered_products_layout_list);
        orderedProductsTitle = (TextView) findViewById(R.id.ordered_products_title);
        buttonSendOrder = findViewById(R.id.button_order_list);

        String data[][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getPharmacists";
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
                pharmacistsList.add(data[i][1]);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pharmacistsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProducts.setAdapter(arrayAdapter);
        spinnerProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                layoutList.removeAllViews();
                String pharmacistName = parent.getItemAtPosition(position).toString();
                getPharmacistID(pharmacistName);
                showProducts();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (orderedLayoutList.getChildCount() == 0){
            Toast.makeText(PatientOrderProducts.this, "Πρώτα εισάγετε προϊόντα!", Toast.LENGTH_LONG).show();
        } else{
            String type = "insertOrder";
            ConnectionHelper connectionHelper = new ConnectionHelper(this);
            connectionHelper.execute(type, Login.user_id, pharmacist_id);

            for(int i = 0; i<orderedLayoutList.getChildCount(); i++) {
                View orderedProductView = orderedLayoutList.getChildAt(i);

                TextView tvorderedProductName = (TextView) orderedProductView.findViewById(R.id.ordered_product_name);
                TextView tvorderedProductStock = (TextView) orderedProductView.findViewById(R.id.ordered_product_stock);
                TextView tvorderedProductPrice = (TextView) orderedProductView.findViewById(R.id.ordered_product_price);

                String orderedProductName = tvorderedProductName.getText().toString();
                String orderedProductStock = tvorderedProductStock.getText().toString();
                String orderedProductPrice = tvorderedProductPrice.getText().toString();

                type = "orderProducts";
                connectionHelper = new ConnectionHelper(this);
                connectionHelper.execute(type, pharmacist_id, orderedProductName, orderedProductStock, orderedProductPrice);
                Toast.makeText(PatientOrderProducts.this, "Η παραγγελία σας καταχωρήθηκε επιτυχώς!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showProducts() {
        String data[][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getProducts";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type, pharmacist_id).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++){
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("name"); // column name
                data[i][1] = jsonObject.getString("stock"); // column name
                data[i][2] = jsonObject.getString("price"); // column name

                addView(data[i][0], data[i][1], data[i][2]);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addView(String productName, String productStock, String productPrice) {
        View productView = getLayoutInflater().inflate(R.layout.row_order_product, null, false);

        TextView etProductName = (TextView) productView.findViewById(R.id.order_product_name);
        EditText etProductStock = (EditText) productView.findViewById(R.id.order_product_stock);
        TextView etProductPrice = (TextView) productView.findViewById(R.id.order_product_price);
        ImageView imageAdd = (ImageView)  productView.findViewById(R.id.image_add_product);

        etProductName.setText(productName);
        etProductStock.setText(productStock);
        etProductPrice.setText(productPrice);

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                View orderedProductView = getLayoutInflater().inflate(R.layout.row_ordered_product, null, false);
                TextView orderedProductName = (TextView) orderedProductView.findViewById(R.id.ordered_product_name);
                TextView orderedProductStock = (TextView) orderedProductView.findViewById(R.id.ordered_product_stock);
                TextView orderedProductPrice = (TextView) orderedProductView.findViewById(R.id.ordered_product_price);
                ImageView imageRemove = (ImageView)  orderedProductView.findViewById(R.id.image_remove_product);

                String stock = etProductStock.getText().toString();
                double price = Double.parseDouble(etProductPrice.getText().toString()) * Integer.parseInt(stock);
                price = (double)Math.round(price * 100d) / 100d;
                orderedProductName.setText(productName);
                orderedProductStock.setText(stock);
                orderedProductPrice.setText(String.valueOf(price));

                imageRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderedLayoutList.removeView(orderedProductView);

                        if (orderedLayoutList.getChildCount() == 0){
                            orderedProductsTitle.setVisibility(View.INVISIBLE);
                        }
                    }
                });

                orderedProductsTitle.setVisibility(View.VISIBLE);
                orderedLayoutList.addView(orderedProductView);
            }
        });

        layoutList.addView(productView);
    }

    private void getPharmacistID(String pharmacistName){
        String type = "getPharmacistID";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            String receivedData = connectionHelper.execute(type, pharmacistName).get();
            pharmacist_id = receivedData;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}