package com.example.loginddesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                Intent intent = new Intent(getApplicationContext(), oldreservation.class);
                intent.putExtra("username", username);
                startActivity(intent);


            }
        });}
}