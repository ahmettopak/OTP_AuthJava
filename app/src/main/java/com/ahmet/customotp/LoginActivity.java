package com.ahmet.customotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView registerText;
    EditText emailText, passwordText;
    public String email= "dev.ahmettopak@gmail.com" , password="123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);
        emailText = findViewById(R.id.emailEditText);
        passwordText = findViewById(R.id.passwordEditText);
        Intent intent = new Intent(this, OtpActivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    email = String.valueOf(emailText.getText());
                    password = String.valueOf(passwordText.getText());

                    if (!email.equals("") && !password.equals("")){
                        intent.putExtra("email", email);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Email veya şifre boş olamaz! ", Toast.LENGTH_SHORT).show();
                    }


            }
        });

    }
}