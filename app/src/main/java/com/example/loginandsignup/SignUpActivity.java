package com.example.loginandsignup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button signupBtn;

    TextView login;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);

        emailInput = findViewById(R.id.editEmail);
        passwordInput = findViewById(R.id.editPassword);
        signupBtn = findViewById(R.id.buttonSignup);
        login=findViewById(R.id.textLogin);

        login.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                });

        signupBtn.setOnClickListener(v -> signupUser());
    }

    private void signupUser() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {

            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a valid email", Toast.LENGTH_LONG).show();
        } else if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
        } else {
            sharedPreferences.edit()
                    .putString("email", email)
                    .putString("password", password)
                    .apply();

            Toast.makeText(this, "Signup successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }

}