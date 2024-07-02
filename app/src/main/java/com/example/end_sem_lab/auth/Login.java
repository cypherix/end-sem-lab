package com.example.end_sem_lab.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.end_sem_lab.Helpers.DBHelper;
import com.example.end_sem_lab.MainActivity;
import com.example.end_sem_lab.R;

public class Login extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;
    TextView signupLink;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginbtn);
        signupLink = findViewById(R.id.signuplink);

        loginButton.setOnClickListener(v -> loginUser());

        signupLink.setOnClickListener(v -> {
            // Navigate to Register activity or screen
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate email and password
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

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }

        // Authenticate user
        if (dbHelper.checkUser(email, password)) {
            // Successful login
            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(Login.this, MainActivity.class);
             startActivity(intent);
        } else {
            // Failed login
            Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}
