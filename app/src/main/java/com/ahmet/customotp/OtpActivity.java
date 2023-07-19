package com.ahmet.customotp;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ahmet.customotp.PostRequestTask;
import com.chaos.view.PinView;


public class OtpActivity extends AppCompatActivity  implements PostRequestTask.OnPostRequestListener{

    PinView otpText;
    Button authButton;
    String otp , recOtp;
    String email = "";
    TextView resendOtp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otpText = findViewById(R.id.otpInput);
        authButton = findViewById(R.id.otpButton);
        resendOtp = findViewById(R.id.resendOtp);


        Intent intent = new Intent(this, MainActivity.class);

        // Intent'ten veriyi alma
        if (getIntent().hasExtra("email")) {
            email = getIntent().getStringExtra("email");
            Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        }

        String serverUrl = "https://otp-server-li85.onrender.com/sendotp";

        PostRequestTask postRequestTask = new PostRequestTask(email, this);
        postRequestTask.execute(serverUrl);

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = otpText.getText().toString();
                if (otp.equals(recOtp)){
                    Toast.makeText(OtpActivity.this, "Hoşgeldiniz", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(OtpActivity.this, "Girdiğiniz OTP Kodu Hatalı!" + otp, Toast.LENGTH_SHORT).show();
                }
            }
        });


        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    postRequestTask.execute(serverUrl);
                }
                catch (IllegalStateException e){
                    Log.d("OTP" , e + "");
                }

            }
        });
    }
    @Override
    public void onPostRequestCompleted(String result) {
        Log.d("TAG", "onPostRequestCompleted: " + result);

        result = result.replaceAll("[^0-9]", ""); // Sadece rakamları koru
        recOtp = result;
    }
}