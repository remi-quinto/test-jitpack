package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//import com.example.android_java_embedded_demo_app.CustomWebChromeClient;

import java.util.ArrayList;
import java.util.List;

public class SdkActivity extends AppCompatActivity {

//    public String roomUrlString = "https://salesdemo.whereby.com/04cb2d34-6a34-4bd1-b1da-e1f89716b56c"; // Replace by your own
////    https://team.whereby.com/remi-quinto?needancestor
//    private String roomParameters = "?needancestor&skipMediaPermissionPrompt";

    public String roomUrlString = "" ;//https://salesdemo.whereby.com/04cb2d34-6a34-4bd1-b1da-e1f89716b56c"; // Replace by your own
    //    https://team.whereby.com/remi-quinto?needancestor
    private String roomParameters = "?needancestor&skipMediaPermissionPrompt";

    private static final int PERMISSION_REQUEST_CODE = 1234;
    private String[] requiredDangerousPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.RECORD_AUDIO
    };

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdk_main);

        roomUrlString = getIntent().getStringExtra("roomUrl");

        this.webView = findViewById(R.id.webView);
        WebViewUtils.configureWebView(this.webView);
        this.webView.setWebChromeClient(new CustomWebChromeClient(this));
        this.webView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.webView.getUrl() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.isPendingPermissions()) {
                // This explicitly requests the camera and audio permissions.
                // It's fine for a demo app but should probably be called earlier in the flow,
                // on a user interaction instead of onResume.
                this.requestCameraAndAudioPermissions();
            } else {
                this.loadEmbeddedRoomUrl();
            }
        }
    }

    private void loadEmbeddedRoomUrl() {
        this.webView.loadUrl(roomUrlString + roomParameters);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (this.grantResultsContainsDenials(grantResults)) {
                    // Show some permissions required dialog.
                } else {
                    // All necessary permissions granted, continue loading.
                    this.loadEmbeddedRoomUrl();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraAndAudioPermissions() {
        this.requestPermissions(this.getPendingPermissions(), PERMISSION_REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String[] getPendingPermissions() {
        List<String> pendingPermissions = new ArrayList<>();
        for (String permission : this.requiredDangerousPermissions) {
            if (this.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                pendingPermissions.add(permission);
            }
        }
        return pendingPermissions.toArray(new String[pendingPermissions.size()]);
    }

    private boolean isPendingPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        return this.getPendingPermissions().length > 0;
    }

    private boolean grantResultsContainsDenials(int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                return true;
            }
        }
        return false;
    }
}