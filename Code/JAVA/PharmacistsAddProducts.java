package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PharmacistsAddProducts extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSumbitList;
    ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacists_add_products);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add_product);
        buttonSumbitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSumbitList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add_product:
                addView();
                break;

            case R.id.button_submit_list:
                if(checkIfValidAndRead()){
                    for (int i = 0; i < productArrayList.size(); i++){
                        String type = "addProducts";
                        ConnectionHelper connectionHelper = new ConnectionHelper(this);
                        connectionHelper.execute(type, productArrayList.get(i).getProductName(), productArrayList.get(i).getProductStock(), productArrayList.get(i).getProductPrice());
                    }

                    Toast.makeText(this, "Τα προϊόντα σας εισήχθηκαν με επιτυχία!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean checkIfValidAndRead() {
        productArrayList.clear();
        boolean result = true;

        for(int i = 0; i<layoutList.getChildCount(); i++){
            View productView = layoutList.getChildAt(i);

            EditText etProductName = (EditText) productView.findViewById(R.id.product_name);
            EditText etProductStock = (EditText) productView.findViewById(R.id.product_stock);
            EditText etProductPrice = (EditText) productView.findViewById(R.id.product_price);

            Product product = new Product();

            if (!etProductName.getText().toString().equals("")){
                product.setProductName(etProductName.getText().toString());
            } else {
                result = false;
                break;
            }
            if (!etProductStock.getText().toString().equals("")){
                product.setProductStock(etProductStock.getText().toString());
            } else {
                result = false;
                break;
            }
            if (!etProductPrice.getText().toString().equals("")){
                product.setProductPrice(etProductPrice.getText().toString());
            } else {
                result = false;
                break;
            }

            productArrayList.add(product);
        }

        if(productArrayList.size() == 0){
            result = false;
            Toast.makeText(this, "Πρώτα εισάγετε τα στοιχεία των προϊόντων", Toast.LENGTH_LONG).show();
        }else if(!result){
            Toast.makeText(this, "Εισάγετε όλες τις πληροφορίες σωστά", Toast.LENGTH_LONG).show();
        }

        return result;
    }

    private void addView() {
        View productView = getLayoutInflater().inflate(R.layout.row_add_product, null, false);

        EditText etProductName = (EditText) productView.findViewById(R.id.product_name);
        EditText etProductStock = (EditText) productView.findViewById(R.id.product_stock);
        EditText etProductPrice = (EditText) productView.findViewById(R.id.product_price);
        ImageView imageClose = (ImageView)  productView.findViewById(R.id.image_remove_product);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                removeView(productView);
            }
        });

        layoutList.addView(productView);
    }

    private void removeView(View view) {
        layoutList.removeView(view);
    }
}