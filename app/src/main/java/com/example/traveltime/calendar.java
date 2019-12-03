package com.example.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class calendar extends AppCompatActivity {

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calendar_view);
    }
}
