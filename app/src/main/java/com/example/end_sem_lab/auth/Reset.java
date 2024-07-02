package com.example.end_sem_lab.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.end_sem_lab.Helpers.DBHelper;
import com.example.end_sem_lab.R;

public class Reset extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button resetButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        dbHelper = new DBHelper(this);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        resetButton = findViewById(R.id.resettbn);

        resetButton.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();
        String newPassword = passwordEditText.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            passwordEditText.setError("New Password is required");
            passwordEditText.requestFocus();
            return;
        }

        // Reset password
        if (dbHelper.resetPassword(email, newPassword)) {
            Toast.makeText(Reset.this, "Password Reset Successful", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(Reset.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            // Password reset failed
            Toast.makeText(Reset.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
