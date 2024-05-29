package com.example.projectweb;

import static com.example.projectweb.AddNewTask.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;

/** @noinspection ALL*/
public class LoginActivity extends AppCompatActivity {

    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;

    public SignInClient oneTapClient;
    public BeginSignInRequest signInRequest;
    TextInputLayout usernameLayout, passwordLayout;
    Button btnlogin, btnsignup, btngoogle;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btngoogle = findViewById(R.id.btngoogle);
        btnsignup = findViewById(R.id.btnsignup);
        btnlogin = findViewById(R.id.btnsignin1);
        usernameLayout = findViewById(R.id.username_layout);
        passwordLayout = findViewById(R.id.password_layout);

        DB = new DBHelper(this);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();

        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            try {
                                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                String idToken = credential.getGoogleIdToken();
                                String username = credential.getId();
                                String password = credential.getPassword();
                                if (idToken !=  null) {
                                    String email = credential.getId();
                                    Toast.makeText(LoginActivity.this, "Email: " + email, Toast.LENGTH_SHORT).show();
                                } else if (password != null) {
                                    Log.d(TAG, "Got password.");
                                }
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneTapClient.beginSignIn(signInRequest)
                        .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
                            @Override
                            public void onSuccess(BeginSignInResult result) {
                                IntentSenderRequest intentSenderRequest =
                                        new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                activityResultLauncher.launch(intentSenderRequest);
                            }
                        })
                        .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // No saved credentials found. Launch the One Tap sign-up flow, or
                                // do nothing and continue presenting the signed-out UI.
                                Log.d("TAG", e.getLocalizedMessage());
                            }
                        });
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = usernameLayout.getEditText().getText().toString().trim();
                String pass = passwordLayout.getEditText().getText().toString().trim();

                if(user.isEmpty()) {
                    usernameLayout.setError("Please enter username");
                    return;
                }

                if(pass.isEmpty()) {
                    passwordLayout.setError("Please enter password");
                    return;
                }

                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if(checkuserpass) {
                    Toast.makeText(LoginActivity.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("USERNAME", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent1);
                Toast.makeText(LoginActivity.this, "Welcome to SignUp!", Toast.LENGTH_SHORT).show();
            }
        });

        View welcome = findViewById(R.id.welcome);
        View loginHeader = findViewById(R.id.login_header);
        View btnSignIn = findViewById(R.id.btnsignin1);
        View btnSignUp = findViewById(R.id.btnsignup);
        View btnGoogle = findViewById(R.id.btngoogle);

        animateView(welcome, 0);
        animateView(loginHeader, 100);
        animateView(usernameLayout, 200);
        animateView(passwordLayout, 300);
        animateView(btnSignIn, 400);
        animateView(btnSignUp, 500);
        animateView(btnGoogle, 600);

        ImageView syncProImage = findViewById(R.id.Airplane);
        ObjectAnimator animator = ObjectAnimator.ofFloat(syncProImage, "translationX", 0f, 300f);
        animator.setDuration(2000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }

    private void animateView(View view, long delay) {
        view.animate()
                .alpha(1f)
                .translationY(0)
                .setStartDelay(delay)
                .setDuration(600)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
}
