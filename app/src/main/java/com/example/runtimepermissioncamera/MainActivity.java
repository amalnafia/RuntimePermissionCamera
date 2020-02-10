package com.example.runtimepermissioncamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Camera Permission Request Code
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button cameraPermission = findViewById(R.id.buttonRequestCameraPermission);
        cameraPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check Camera Permission already granted or not
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this, "Camera permission is already granted", Toast.LENGTH_SHORT).show();
                    openCamera();
                } else {
                    // Request Camera Permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    // When we request permission user will either allow or deny the permission which can be checked inside onRequestPermissionsResult method
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                // Check Camera permission is granted or not
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                    Toast.makeText(MainActivity.this, "Camera  permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Camera  permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void openCamera()
    {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
}