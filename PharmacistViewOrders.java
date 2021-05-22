  package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class PharmacistViewOrders extends AppCompatActivity {

    LinearLayout viewOrdersLayoutList;
    LinearLayout viewOrderedProductsLayoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_orders);

        viewOrdersLayoutList = findViewById(R.id.view_orders_layout_list);


        String data[][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getOrders";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type, Login.user_id).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++){
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("id"); // column name
                String orderid = data[i][0];
                data[i][1] = jsonObject.getString("fullname"); // column name

                View orderView = getLayoutInflater().inflate(R.layout.row_view_order, null, false);

                TextView etOrderDetails = (TextView) orderView.findViewById(R.id.order_from);
                ImageView imageAccept = (ImageView)  orderView.findViewById(R.id.image_accept_order);
                ImageView imageReject = (ImageView)  orderView.findViewById(R.id.image_reject_order);
                ImageView imageDropDownUp = (ImageView) orderView.findViewById(R.id.arrow_drop_down_up);
                final int[] updown = {0};

                String details = "Παραγγελία#"+data[i][0]+" από "+data[i][1];
                etOrderDetails.setText(details);
                viewOrdersLayoutList.addView(orderView);
                viewOrderedProductsLayoutList = findViewById(R.id.ordered_products_layout);

                imageAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateOrder(orderid, "A");
                        viewOrdersLayoutList.removeView(orderView);
                    }
                });
                imageReject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateOrder(orderid, "R");
                        viewOrdersLayoutList.removeView(orderView);
                    }
                });
                imageDropDownUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updown[0]++;
                        if (updown[0] % 2 == 1){
                            imageDropDownUp.setImageResource(R.drawable.ic_arrow_drop_up);
                            viewOrderedProducts(orderid);
                        } else {
                            viewOrderedProductsLayoutList.removeAllViews();
                            imageDropDownUp.setImageResource(R.drawable.ic_arrow_drop_down);
                        }
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

    private void viewOrderedProducts(String orderid) {
        String data[][], receivedData = "";
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String type = "getOrderedProducts";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        try {
            receivedData = connectionHelper.execute(type, orderid).get();
            jsonArray = new JSONArray(receivedData);
            data = new String[jsonArray.length()][];
            for (int i=0; i<jsonArray.length(); i++) {
                data[i] = new String[jsonArray.length()];
                jsonObject = jsonArray.getJSONObject(i);
                data[i][0] = jsonObject.getString("name"); // column name
                data[i][1] = jsonObject.getString("stock"); // column name
                data[i][2] = jsonObject.getString("price"); // column name

                View productView = getLayoutInflater().inflate(R.layout.row_view_ordered_product, null, false);

                TextView orderedProductName = (TextView) productView.findViewById(R.id.view_ordered_product_name);
                TextView orderedProductStock = (TextView) productView.findViewById(R.id.view_ordered_product_stock);
                TextView orderedProductPrice = (TextView) productView.findViewById(R.id.view_ordered_product_price);

                orderedProductName.setText(data[i][0]);
                orderedProductStock.setText(data[i][1]);
                orderedProductPrice.setText(data[i][2]);

                viewOrderedProductsLayoutList.addView(productView);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateOrder(String orderid, String status) {
        String type = "updateOrder";
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.execute(type, orderid, status);
    }

}