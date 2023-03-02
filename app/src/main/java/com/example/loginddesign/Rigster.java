package com.example.loginddesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Rigster extends AppCompatActivity {
    Routes routes=new Routes();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigster);
        EditText Username=(EditText) findViewById(R.id.User);
        ProgressDialog progressDialog = new ProgressDialog(this);
        EditText Password=(EditText) findViewById(R.id.Pass);
        Button RegButton=(Button) findViewById(R.id.RegButton);
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Why Its Named From Not Username??
                String From = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();
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
            }
            private void postDataUsingVolley(String From, String Password) {

                progressDialog.setMessage("Waiting");
                progressDialog.show();
                RequestQueue queue = Volley.newRequestQueue(Rigster.this);
// on below line we are calling a string
                // request method to post the data to our API
                // in this we are calling a post method.
                StringRequest request = new StringRequest(Request.Method.POST, routes.USERROUTE, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // on below line we are parsing the response
                            // to json object to extract data from it.
                            JSONObject respObj = new JSONObject(response);
                            if(respObj.getBoolean("Status")){
                            Toast.makeText(Rigster.this, "Please Sign In", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Rigster.this,MainActivity.class);
                                startActivity(i);

                            }else{
                                Toast.makeText(Rigster.this, "Your Email Or Password Is wrong", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Rigster.this, "Fail to get response " + error, Toast.LENGTH_SHORT).show();
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