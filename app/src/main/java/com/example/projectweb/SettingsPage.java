package com.example.projectweb;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsPage extends AppCompatActivity {

    private ImageView backarrow;
    private TextView usernameTextView;
    private TextView phoneNumberTextView;

    private ActivityResultLauncher<Intent> updateProfileLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        backarrow = findViewById(R.id.backarrow);
        usernameTextView = findViewById(R.id.usernameTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                Toast.makeText(SettingsPage.this, "Welcome back Dashboard!", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.editProfileButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsPage.this, UpdateProfile.class);
                updateProfileLauncher.launch(intent); // Use the new ActivityResultLauncher
            }
        });

        updateProfileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String updatedUsername = data.getStringExtra("updatedUsername");
                        String updatedPhoneNumber = data.getStringExtra("updatedPhoneNumber");

                        usernameTextView.setText(updatedUsername);
                        phoneNumberTextView.setText(updatedPhoneNumber);
                        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
