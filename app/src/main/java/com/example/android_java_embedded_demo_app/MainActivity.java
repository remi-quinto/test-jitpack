package com.example.android_java_embedded_demo_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mylibrary.SdkActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    public String roomUrlString = "https://salesdemo.whereby.com/04cb2d34-6a34-4bd1-b1da-e1f89716b56c"; // Replace by your own
    public String roomUrlString = "https://team.whereby.com/remi-quinto";
//    private String roomParameters = "?needancestor&skipMediaPermissionPrompt";

//    private WebView webView;

    public static void testStaticMethod() {
        ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SdkActivity.class);
        intent.putExtra("roomUrl", roomUrlString);
        startActivity(intent);

    }
}