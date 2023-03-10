package com.example.loginddesign;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Routes routes=new Routes();
        SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ProgressDialog progressDialog = new ProgressDialog(this);
        Button loginButton = (Button) findViewById(R.id.RegButton);
        // I know Its Error
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView=(TextView) findViewById(R.id.Reg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Rigster.class);
                startActivity(i);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText FromEditText = (EditText) findViewById(R.id.User);
                EditText passwordEditText = (EditText) findViewById(R.id.Pass);

                String From = FromEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
//
//                Intent i = new Intent(MainActivity.this,MainActivity2.class);
//                startActivity(i);
                if (From.isEmpty() && password.isEmpty()) {
                    // If either field is empty, show an error message
                    Toast.makeText(getApplicationContext(), "Please enter a From and password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 5 || password.length() > 20) {
                    // If the password is too short or too long, show an error message
                    Toast.makeText(getApplicationContext(), "password must be between 5 and 20 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(From,password);

//                System.out.println("Hello World");


            }
            private void postDataUsingVolley(String From, String Password) {

                progressDialog.setMessage("Waiting");
                progressDialog.show();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
// on below line we are calling a string
                // request method to post the data to our API
                // in this we are calling a post method.
                StringRequest request = new StringRequest(Request.Method.POST, routes.AUTH, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // inside on response method we are
                        // hiding our progress bar
                    // Used SharedPref to Save my Token for the Next Requests
// on below line we are displaying a success toast message.

                        try {
                            // on below line we are parsing the response
                            // to json object to extract data from it.
                            JSONObject respObj = new JSONObject(response);
                            System.out.println(respObj.getBoolean("Status"));
                            if(respObj.getBoolean("Status")){
                                editor.putString("Token",respObj.getString("accessToken"));
                                editor.commit();
                                Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                                startActivity(i);

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Your Email Or Password Is wrong", Toast.LENGTH_SHORT).show();
                            }

progressDialog.dismiss();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error "+error);
                        Toast.makeText(MainActivity.this, "Fail to get response " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // below line we are creating a map for
                        // storing our values in key and value pair.
                        Map<String, String> Body = new HashMap<String, String>();

                        // on below line we are passing our key
                        // and value pair to our parameters.
                        Body.put("Username", From);
                        Body.put("Password", Password);
                        // returning our params.
                        return Body;
                    }
                };
                // below line is to make
                // a JSON object request.
                queue.add(request);
            }
        });
    }


}