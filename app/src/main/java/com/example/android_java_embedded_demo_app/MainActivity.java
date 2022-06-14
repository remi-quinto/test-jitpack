package com.example.android_java_embedded_demo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    public String roomUrlString = "https://salesdemo.whereby.com/04cb2d34-6a34-4bd1-b1da-e1f89716b56c"; // Replace by your own
    public String roomUrlString = "https://team.whereby.com/remi-quinto";
//    private String roomParameters = "?needancestor&skipMediaPermissionPrompt";

//    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, SdkActivity.class);
//        intent.putExtra("roomUrl", roomUrlString);
//        startActivity(intent);

    }
}