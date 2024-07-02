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
import com.example.end_sem_lab.R;

public class Register extends AppCompatActivity {

    EditText usernameEditText, emailEditText, mobileEditText, passwordEditText;
    Button registerButton;
    TextView loginLink;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        mobileEditText = findViewById(R.id.number);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.registertbn);
        loginLink = findViewById(R.id.loginlink);

        registerButton.setOnClickListener(v -> registerUser());

        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
            finish();
        });
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Username is required");
            usernameEditText.requestFocus();
            return;
        }

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

        if (TextUtils.isEmpty(mobile)) {
            mobileEditText.setError("Mobile number is required");
            mobileEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }


        if (dbHelper.addUser(username, password, email, mobile)) {

            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            // Registration failed
            Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
