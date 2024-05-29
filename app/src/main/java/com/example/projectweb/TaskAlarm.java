package com.example.projectweb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TaskAlarm extends AppCompatActivity implements View.OnClickListener{

    private TimePicker tp;
    private Button btn_set;
    private EditText taskInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_alarm);

        btn_set = findViewById(R.id.set_alarm);
        tp = findViewById(R.id.time);
        taskInput = findViewById(R.id.task_input);

        btn_set.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                tp.getHour(),
                tp.getMinute(), 0);
        String task = taskInput.getText().toString();
        if (task.isEmpty()) {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            return;
        }
        Alarm_set(cal.getTimeInMillis(), task);
    }

    private void Alarm_set(long timeInMillis, String task) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        intent.putExtra("task", task);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Your Alarm is Set", Toast.LENGTH_LONG).show();
    }
}
