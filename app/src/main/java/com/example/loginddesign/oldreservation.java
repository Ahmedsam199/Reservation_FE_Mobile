package com.example.loginddesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class oldreservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sh = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String s1 = sh.getString("Token", "");
        System.out.println("Token "+s1);
        setContentView(R.layout.activity_oldreservation);
    }
}