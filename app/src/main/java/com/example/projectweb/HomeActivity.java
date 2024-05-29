package com.example.projectweb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    // Declare CardView variables
    CardView calenderView, splashScreen, createTask, settings, alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the ImageView and start the animation
        ImageView syncProImage = findViewById(R.id.SyncProImage);
        ObjectAnimator animator = ObjectAnimator.ofFloat(syncProImage, "translationX", 0f, 50f);
        animator.setDuration(2000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

        // Find the CardViews
        calenderView = findViewById(R.id.calendars);
        splashScreen = findViewById(R.id.HomeTown);
        createTask = findViewById(R.id.Addtask);
        settings = findViewById(R.id.Settings);
        alarms = findViewById(R.id.alarm);
       /* animateRotation(calenderView);
        animateRotation(splashScreen);
        animateRotation(createTask);
        animateRotation(settings);*/

        // Set onClickListeners for the CardViews
        splashScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRotation(view);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Welcome Back to Home!", Toast.LENGTH_SHORT).show();
            }
        });

        calenderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRotation(view);
                Intent intent = new Intent(getApplicationContext(), CalendarView.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Welcome to CalendarView!", Toast.LENGTH_SHORT).show();
            }
        });

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRotation(view);
                Intent intent = new Intent(getApplicationContext(), AddTask.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Welcome to Create Task!", Toast.LENGTH_SHORT).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRotation(view);
                Intent intent = new Intent(getApplicationContext(), SettingsPage.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Welcome to Settings!", Toast.LENGTH_SHORT).show();
            }
        });

        alarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRotation(view);
                Intent intent = new Intent(getApplicationContext(), TaskAlarm.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Welcome to Alarm/Task Alarm!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animateRotation(View view) {
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        rotationAnimator.setDuration(1000); // Rotation duration in milliseconds
        rotationAnimator.start();
    }
}
