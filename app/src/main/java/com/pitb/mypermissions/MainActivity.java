package com.pitb.mypermissions;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static com.pitb.mypermissions.Enums.CALL_LOG;
import static com.pitb.mypermissions.Enums.CAMERA;
import static com.pitb.mypermissions.Enums.LOCATION;
import static com.pitb.mypermissions.Enums.READ_CONTACTS;
import static com.pitb.mypermissions.Enums.READ_SMS;

public class MainActivity extends AppCompatActivity {

    public PermissionManager sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                sec = new PermissionManager(MainActivity.this, getApplicationContext());

                PermissionManagerInterface managerInterface = new PermissionManagerInterface() {
                    @Override
                    public void onPermissionGranted(String message, int requestCode) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(String message, int requestCode) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void isAllGranted(boolean flag) {
                        if (!flag) {
                            Toast.makeText(MainActivity.this, "One of the permission is still denied", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "All permissions are granted", Toast.LENGTH_SHORT).show();
                            //Continue your app starting methods from here
                        }
                    }

                };
                sec.getManagerInterface(managerInterface);
            }else{
                //Write your methods here if yourphone is below api 23
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        try {
            super.onStart();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String[] list = new String[]{CAMERA, CALL_LOG, LOCATION, READ_SMS, READ_CONTACTS};
                sec.requestPermission(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        sec.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
