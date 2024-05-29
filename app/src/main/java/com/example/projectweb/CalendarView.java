package com.example.projectweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CalendarView extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        customCalendarView = (CustomCalendarView) findViewById(R.id.custom_calendar_view);
    }
}