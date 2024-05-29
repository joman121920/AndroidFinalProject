package com.example.projectweb;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button btngetStarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btngetStarted = (Button) findViewById(R.id.getStarted);

        btngetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Login in Here!", Toast.LENGTH_SHORT).show();
            }
        });

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(btngetStarted, "scaleX", 0.95f, 1.0f);
        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleXAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        scaleXAnimator.setDuration(1000);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(btngetStarted, "scaleY", 0.95f, 1.0f);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        scaleYAnimator.setDuration(1000);

        scaleXAnimator.start();
        scaleYAnimator.start();
    }
}