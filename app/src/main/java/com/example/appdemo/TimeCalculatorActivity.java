package com.example.appdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeCalculatorActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TimePicker startTimePicker;
    private TimePicker endTimePicker;
    private TextView result;
    private TextView startTextView;
    private TextView endTextView;

    enum MODE{TP_TP, TP_DUR, DUR_TP;}
    private MODE mode = MODE.TP_TP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_calculator);

        Button calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(this);

        RadioGroup calculateMode = findViewById(R.id.calculate_mode);
        calculateMode.setOnCheckedChangeListener(this);

        startTimePicker = findViewById(R.id.start_timePicker);
        endTimePicker = findViewById(R.id.end_timePicker);
        startTimePicker.setIs24HourView(true);
        endTimePicker.setIs24HourView(true);

        result = findViewById(R.id.time_result);
        startTextView = findViewById(R.id.start_textView);
        endTextView = findViewById(R.id.end_textView);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calculate:
                int startHour = startTimePicker.getHour();
                int startMinute = startTimePicker.getMinute();

                int endHour = endTimePicker.getHour();
                int endMinute = endTimePicker.getMinute();

                String resultString = calculateTime(startHour, startMinute, endHour, endMinute);
                result.setText(resultString);

                break;
        }
    }

    private String calculateTime(int startHour, int startMinute, int endHour, int endMinute) {

        int start = startHour * 60 + startMinute;
        int end = endHour * 60 + endMinute;
        int time;

        switch (mode) {
            case TP_TP:
                time = end - start;
                if (start > end) {
                    return "起始时间晚于终止时间";
                } else if (start == end) {
                    return "0";
                } else {
                    return String.format("时长：%d小时%d分钟", time / 60, time % 60);
                }
            case TP_DUR:
                time = start + end;
                return formatTime(time);
            case DUR_TP:
                time = end - start;
                return formatTime(time);
            default:
                return "Error!";
        }

    }

    private String formatTime(int time) {

        String res = "时间点  ";
        if (time > 60*24) {
            res += "第二天 ";
            time = time - 60*24;
        } else if (time < 0) {
            res += "前一天 ";
            time += 60*24;
        }

        String hour = String.valueOf(time / 60);
        if (hour.length() == 1) {
            res += "0";
        }
        res += (hour + ":");

        String minute = String.valueOf(time % 60);
        if (minute.length() == 1) {
            res += "0";
        }
        res += minute;

        return res;

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.tp_dur:
                startTextView.setText(R.string.start_time);
                endTextView.setText(R.string.add_time);
                mode = MODE.TP_DUR;
                break;
            case R.id.dur_tp:
                startTextView.setText(R.string.sub_time);
                endTextView.setText(R.string.end_time);
                mode = MODE.DUR_TP;
                break;
            default:
                break;
        }
    }
}