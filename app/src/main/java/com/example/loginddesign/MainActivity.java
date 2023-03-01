package com.example.loginddesign;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Routes routes=new Routes();
        SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ProgressDialog progressDialog = new ProgressDialog(this);
        Button loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText usernameEditText = (EditText) findViewById(R.id.editTextTextEmailAddress);
                EditText passwordEditText = (EditText) findViewById(R.id.editTextTextPassword);

                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();


                if (username.isEmpty() || password.isEmpty()) {
                    // If either field is empty, show an error message
                    Toast.makeText(getApplicationContext(), "Please enter a username and password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 5 || password.length() > 20) {
                    // If the password is too short or too long, show an error message
                    Toast.makeText(getApplicationContext(), "password must be between 5 and 20 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(username,password);

//                System.out.println("Hello World");


            }
            private void postDataUsingVolley(String UserName, String Password) {
                // url to post our data
//                String url = "http://192.168.174.160:3000/Auth/login";
                progressDialog.setMessage("Waiting");
                progressDialog.show();
                // creating a new variable for our request queue
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
// on below line we are calling a string
                // request method to post the data to our API
                // in this we are calling a post method.
                StringRequest request = new StringRequest(Request.Method.POST, routes.AUTH, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // inside on response method we are
                        // hiding our progress bar
                        // and setting data to edit text as empty
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
                                Intent intent = new Intent(getApplicationContext(), oldreservation.class);
                                intent.putExtra("username", UserName);
                                startActivity(intent);

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Youre Email Or Password Is Worng", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // below line we are creating a map for
                        // storing our values in key and value pair.
                        Map<String, String> params = new HashMap<String, String>();

                        // on below line we are passing our key
                        // and value pair to our parameters.
                        params.put("Username", UserName);
                        params.put("Password", Password);
                        // returning our params.
                        return params;
                    }
                };
                // below line is to make
                // a json object request.
                queue.add(request);
            }
        });
    }


}