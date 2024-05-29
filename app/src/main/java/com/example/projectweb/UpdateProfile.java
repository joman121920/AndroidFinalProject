package com.example.projectweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateProfile extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText phoneNumberEditText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        usernameEditText = findViewById(R.id.username1);
        phoneNumberEditText = findViewById(R.id.password1);
        updateButton = findViewById(R.id.btnsignin1);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedUsername = usernameEditText.getText().toString();
                String updatedPhoneNumber = phoneNumberEditText.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedUsername", updatedUsername);
                resultIntent.putExtra("updatedPhoneNumber", updatedPhoneNumber);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
