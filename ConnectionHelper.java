package com.example.healthcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.lang.UProperty;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class ConnectionHelper extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    String session_id = "";
    ConnectionHelper (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if(type.equals("register")){
            String register_url = "http://10.0.2.2/register.php";
            try {
                String email = params[1];
                String pass = params[2];
                String property = params[3];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        +URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass, "UTF-8")+"&"
                        +URLEncoder.encode("property","UTF-8")+"="+URLEncoder.encode(property, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("login")){
            String login_url = "http://10.0.2.2/login.php";
            try {
                String email = params[1];
                String pass = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        +URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("patientProfile")) {
            String patient_url = "http://10.0.2.2/patientProfile.php";
            try {
                String fullname = params[1];
                String age = params[2];
                String gender = params[3];
                String address = params[4];
                String phone = params[5];
                String info = params[6];
                URL url = new URL(patient_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8") + "&"
                        + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&"
                        + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&"
                        + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                        + URLEncoder.encode("info", "UTF-8") + "=" + URLEncoder.encode(info, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("doctorProfile")) {
            String doctor_url = "http://10.0.2.2/doctorProfile.php";
            try {
                String fullname = params[1];
                String specialty = params[2];
                String address = params[3];
                String phone = params[4];
                URL url = new URL(doctor_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8") + "&"
                        + URLEncoder.encode("specialty", "UTF-8") + "=" + URLEncoder.encode(specialty, "UTF-8") + "&"
                        + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                System.out.println(post_data);
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("pharmacistProfile")) {
            String doctor_url = "http://10.0.2.2/pharmacistProfile.php";
            try {
                String fullname = params[1];
                String address = params[2];
                String phone = params[3];
                URL url = new URL(doctor_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8") + "&"
                        + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                System.out.println(post_data);
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("nearbyHospitals")) {
            String nearby_hospitals_url = "http://10.0.2.2/getNearbyHospitals.php";
            try {
                String data[][];
                URL url = new URL(nearby_hospitals_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                data = new String[jsonArray.length()][];
                for (int i=0; i<jsonArray.length(); i++){
                    data[i] = new String[jsonArray.length()];
                    jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getString("name"); // column name
                    data[i][1] = jsonObject.getString("lat"); // column name
                    data[i][2] = jsonObject.getString("lng"); // column name
                }

                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("nearbyPharmacies")) {
            String nearby_pharmacies_url = "http://10.0.2.2/getNearbyPharmacies.php";
            try {
                String data[][];
                URL url = new URL(nearby_pharmacies_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                data = new String[jsonArray.length()][];
                for (int i=0; i<jsonArray.length(); i++){
                    data[i] = new String[jsonArray.length()];
                    jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getString("name"); // column name
                    data[i][1] = jsonObject.getString("lat"); // column name
                    data[i][2] = jsonObject.getString("lng"); // column name
                }

                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("addProducts")) {
            String doctor_url = "http://10.0.2.2/addProducts.php";
            try {
                String productname = params[1];
                String stock = params[2];
                String price = params[3];
                URL url = new URL(doctor_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("productname", "UTF-8") + "=" + URLEncoder.encode(productname, "UTF-8") + "&"
                        + URLEncoder.encode("stock", "UTF-8") + "=" + URLEncoder.encode(stock, "UTF-8") + "&"
                        + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&"
                        + URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(Login.user_id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getPharmacists")) {
            String nearby_pharmacies_url = "http://10.0.2.2/getPharmacists.php";
            try {
                String data[][];
                URL url = new URL(nearby_pharmacies_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                data = new String[jsonArray.length()][];
                for (int i=0; i<jsonArray.length(); i++){
                    data[i] = new String[jsonArray.length()];
                    jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getString("user_id"); // column name
                    data[i][1] = jsonObject.getString("fullname"); // column name
                }

                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getPharmacistID")){
            String login_url = "http://10.0.2.2/getPharmacistID.php";
            try {
                String fullname = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fullname, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getProducts")) {
            String nearby_hospitals_url = "http://10.0.2.2/getProducts.php";
            try {
                String data[][];
                String pharmacist_id = params[1];
                URL url = new URL(nearby_hospitals_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("pharmacist_id", "UTF-8") + "=" + URLEncoder.encode(pharmacist_id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                data = new String[jsonArray.length()][];
                for (int i=0; i<jsonArray.length(); i++){
                    data[i] = new String[jsonArray.length()];
                    jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getString("name"); // column name
                    data[i][1] = jsonObject.getString("stock"); // column name
                    data[i][2] = jsonObject.getString("price"); // column name
                }

                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("insertOrder")) {
            String order_url = "http://10.0.2.2/insertOrder.php";
            try {
                String user_id = params[1];
                String pharmacist_id = params[2];
                URL url = new URL(order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&"
                        + URLEncoder.encode("pharmacist_id", "UTF-8") + "=" + URLEncoder.encode(pharmacist_id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                System.out.println(result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("orderProducts")) {
            String products_url = "http://10.0.2.2/insertOrderedProducts.php";
            try {
                String pharmacist_id = params[1];
                String name = params[2];
                String stock = params[3];
                String price = params[4];
                URL url = new URL(products_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("pharmacist_id", "UTF-8") + "=" + URLEncoder.encode(pharmacist_id, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("stock", "UTF-8") + "=" + URLEncoder.encode(stock, "UTF-8") + "&"
                        + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getOrders")) {
            String get_orders_url = "http://10.0.2.2/getOrders.php";
            try {
                String data[][];
                String pharmacist_id = params[1];
                URL url = new URL(get_orders_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("pharmacist_id", "UTF-8") + "=" + URLEncoder.encode(pharmacist_id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                data = new String[jsonArray.length()][];
                for (int i=0; i<jsonArray.length(); i++){
                    data[i] = new String[jsonArray.length()];
                    jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getString("id"); // column name
                    data[i][1] = jsonObject.getString("fullname"); // column name
                }

                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("updateOrder")) {
            String order_url = "http://10.0.2.2/updateOrder.php";
            try {
                String order_id = params[1];
                String status = params[2];
                URL url = new URL(order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("order_id", "UTF-8") + "=" + URLEncoder.encode(order_id, "UTF-8") + "&"
                        + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getOrderedProducts")) {
            String get_orders_url = "http://10.0.2.2/getOrderedProducts.php";
            try {
                String data[][];
                String order_id = params[1];
                URL url = new URL(get_orders_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("order_id", "UTF-8") + "=" + URLEncoder.encode(order_id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                data = new String[jsonArray.length()][];
                for (int i=0; i<jsonArray.length(); i++){
                    data[i] = new String[jsonArray.length()];
                    jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getString("name"); // column name
                    data[i][1] = jsonObject.getString("stock"); // column name
                    data[i][2] = jsonObject.getString("price"); // column name
                }

                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        //alertDialog.show();
        
        char property = result.charAt(result.length() - 1);
        if (property == 'A'){
            Intent intent = new Intent(context, PatientMenu.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (property == 'G'){
            Intent intent = new Intent(context, test.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (property == 'F'){
            Intent intent = new Intent(context, PharmacistMenu.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (result.equals("back_to_login")){
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (result.equals("Products Added!")){
            System.out.println("Products Added!");
        } else
            System.out.println(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
