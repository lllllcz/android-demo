package com.example.appdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toCalButton = findViewById(R.id.to_cal_button);
        Button loginButton = findViewById(R.id.to_login_button);
        Button timeButton = findViewById(R.id.time_calculator_button);
        toCalButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.to_cal_button:
                intent.setClass(MainActivity.this, CalculatorActivity.class);
                break;
            case R.id.to_login_button:
                intent.setClass(MainActivity.this, LoginActivity.class);
                break;
            case R.id.time_calculator_button:
                intent.setClass(MainActivity.this, TimeCalculatorActivity.class);
                break;
        }
        startActivity(intent);
    }
}