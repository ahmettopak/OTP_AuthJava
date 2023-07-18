package com.ahmet.customotp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PostRequestTask.OnPostRequestListener {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //resultTextView = findViewById(R.id.resultTextView);

        // Örnek bir sunucu URL'si
        String serverUrl = "http://192.168.1.147:3000/sendotp";

        // İstek gönderilecek email adresi
        String email = "dev.ahmettopak@gmail.com";

        // PostRequestTask'i başlat
        PostRequestTask postRequestTask = new PostRequestTask(email, this);
        postRequestTask.execute(serverUrl);
    }

    @Override
    public void onPostRequestCompleted(String result) {
        // Sunucudan gelen sonucu ekrana bas
        //resultTextView.setText(result);
        Log.d("TAG", "onPostRequestCompleted: " + result);
    }
}