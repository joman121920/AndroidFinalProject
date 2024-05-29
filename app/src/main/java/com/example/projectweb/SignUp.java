package com.example.projectweb;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        SQLiteDatabase db = DB.getDatabase();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = repassword.getText().toString().trim();

                if(user.isEmpty()) {
                    username.setError("Please enter username");
                    return;
                }

                if(pass.isEmpty()) {
                    password.setError("Please enter password");
                    return;
                }

                if(repass.isEmpty()) {
                    repassword.setError("Please confirm password");
                    return;
                }

                if(!pass.equals(repass)) {
                    repassword.setError("Password not matching");
                    return;
                }

                Boolean checkuser = DB.checkusername(user);
                if(checkuser == false) {
                    Boolean insert = DB.insertData(user, pass);
                    if(insert == true) {
                        Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUp.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUp.this, "User already exists! Please sign in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView syncProImage = findViewById(R.id.Airplane);
        ObjectAnimator animator = ObjectAnimator.ofFloat(syncProImage, "translationX", 0f, 300f);
        animator.setDuration(2000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }
}