package com.example.loginddesign;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    List<ResrvationModel> postList=new ArrayList<>();
    Routes routes=new Routes();
    ReservationAdapter adapter;
RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recyclerview);
        Button button=findViewById(R.id.Navigate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//:TODO What am i Doing here ?
//Im Just Calling Method That Bring My Data
        GetData();
    }

    private void GetData() {
        SharedPreferences sh = getSharedPreferences("Data", MODE_PRIVATE);
        String s1 = sh.getString("Token", "");
        System.out.println("TOKEN11"+s1);

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Waiting For Data...");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, routes.RESERVATIONROUTE+"/"+s1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<=response.length();i++){
                    try {
                        //:TODO Plz Plz Plz Plz Plz Do Not Touch This This Plz Plz Plz Plz Plz
                        JSONObject jsonObject =response.getJSONObject(i);
                        postList.add(new ResrvationModel(
                                jsonObject.getInt("id"),
                                jsonObject.getString("FlightName"),
                                jsonObject.getInt("Cost"),
                                jsonObject.getString("From"),
                                //:TODO I know The Name Must Be ToCountry But The Database is Created So its what its
                                jsonObject.getString("ToCountrt")
                        )
                        )
                        ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter=new ReservationAdapter(getApplicationContext(),postList);
                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity2.this, "Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                // if you get This Error Please Notify Me
                Toast.makeText(MainActivity2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}