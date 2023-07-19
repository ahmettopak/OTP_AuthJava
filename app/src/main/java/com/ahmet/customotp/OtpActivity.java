package com.ahmet.customotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;

public class OtpActivity extends AppCompatActivity  implements PostRequestTask.OnPostRequestListener{

    PinView otpText;
    Button authButton;
    private  String otp , recOtp;
    Intent intent = new Intent(this, MainActivity.class);
    LoginActivity loginActivity;

    OtpActivity(LoginActivity loginActivity){
        this.loginActivity = loginActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otpText = findViewById(R.id.otpInput);
        authButton = findViewById(R.id.otpButton);
        String serverUrl = "http://192.168.1.147:3000/sendotp";

        // İstek gönderilecek email adresi
        String email = "dev.ahmettopak@gmail.com";

        // PostRequestTask'i başlat
        PostRequestTask postRequestTask = new PostRequestTask(loginActivity.email, this);
        postRequestTask.execute(serverUrl);
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = otpText.getText().toString();
                if (otp == recOtp){
                    Toast.makeText(OtpActivity.this, "Hoşgeldiniz", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(OtpActivity.this, "Girdiğiniz OTP Kodu Hatalı!", Toast.LENGTH_SHORT).show();
                }
               }
        });
    }
    @Override
    public void onPostRequestCompleted(String result) {
        Log.d("TAG", "onPostRequestCompleted: " + result);
        recOtp = result;
    }
}