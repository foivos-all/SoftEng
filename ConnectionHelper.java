package com.example.healthcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
                System.out.println(post_data);
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
        }else if (type.equals("pharmacistProfile")) {
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
        if (result.equals("A")){
            Intent intent = new Intent(context, PatientMenu.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (result.equals("G")){
            Intent intent = new Intent(context, test.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (result.equals("F")){
            Intent intent = new Intent(context, test.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }else if (result.equals("back_to_login")){
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
