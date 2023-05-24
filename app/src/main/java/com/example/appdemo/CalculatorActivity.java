package com.example.appdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView result_view;

    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        result_view = findViewById(R.id.result_view);

        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.clear_button).setOnClickListener(this);
        findViewById(R.id.divide_button).setOnClickListener(this);
        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.subtract_button).setOnClickListener(this);
        findViewById(R.id.multiply_button).setOnClickListener(this);
        findViewById(R.id.number_0_button).setOnClickListener(this);
        findViewById(R.id.number_1_button).setOnClickListener(this);
        findViewById(R.id.number_2_button).setOnClickListener(this);
        findViewById(R.id.number_3_button).setOnClickListener(this);
        findViewById(R.id.number_4_button).setOnClickListener(this);
        findViewById(R.id.number_5_button).setOnClickListener(this);
        findViewById(R.id.number_6_button).setOnClickListener(this);
        findViewById(R.id.number_7_button).setOnClickListener(this);
        findViewById(R.id.number_8_button).setOnClickListener(this);
        findViewById(R.id.number_9_button).setOnClickListener(this);
        findViewById(R.id.point_button).setOnClickListener(this);
        findViewById(R.id.equal_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.empty_button1 || view.getId() == R.id.empty_button2) {
            return;
        }
        String inputText = ((TextView) view).getText().toString();

        switch (view.getId()){
            case R.id.clear_button:
                showText = firstNum = operator = secondNum = "";
                result_view.setText("");
                break;
            case R.id.cancel_button:
                break;
            case R.id.add_button:
            case R.id.subtract_button:
            case R.id.multiply_button:
            case R.id.divide_button:
                if (!result.equals("")) {
                    showText = firstNum = result;
                    result = "";
                }
                operator = inputText;
                showText = showText + " " + inputText + " ";
                result_view.setText(showText);
                break;
            case R.id.equal_button:
                double calculate_result = calculate();
                result = String.valueOf(calculate_result);
                showText = showText + " = " + result;
                result_view.setText(showText);
                firstNum = operator = secondNum = "";
                break;
            default:
                if (!result.equals("")) {
                    showText = result = "";
                }

                if (operator.equals("")) {
                    // 无运算符，拼接第一个操作数
                    firstNum += inputText;
                }
                else {
                    // 有运算符，拼接第二个操作数
                    secondNum += inputText;
                }

                if (showText.equals("0") && !inputText.equals(".")) {
                    showText = inputText;
                }
                else {
                    showText += inputText;
                }
                result_view.setText(showText);
        }

    }

    // 计算结果
    private double calculate() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
            default:
                return 0;
        }
    }


}