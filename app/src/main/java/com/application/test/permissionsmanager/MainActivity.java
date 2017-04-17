package com.application.test.permissionsmanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.pitb.mypermissions.R;

import java.util.List;

import constants.PermissionRequestObject;
import permission.GlobalPermissionManager;
import permission.PermissionManager;
import permission.PermissionManagerInterface;
import permission.RequestPermissionsResultInterface;

public class MainActivity extends AppCompatActivity
{

    public static int INTERNET_PERMISSION_CODE = 1010;
    public static int CALL_PHONE_PERMISSION_CODE = 1011;
    public static int PHONE_STATE_PERMISSION_CODE = 1012;
    public static int NETWORK_STATE_PERMISSION_CODE = 1013;
    public static int FINE_LOCATION_PERMISSION_CODE = 1014;
    public static int COARSE_LOCATION_PERMISSION_CODE = 1015;
    public static int EXTERNAL_STORAGE_PERMISSION_CODE = 1016;

//    RequestPermissionsResultInterface mRequestPermissionsResultInterface;

//    PermissionManagerInterface mPermissionManagerInterface = null;
//    PermissionManager mPermissionManager = null;

    GlobalPermissionManager globalPermissionManager;
    PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManagerInterface permissionManagerInterface = new PermissionManagerInterface()
        {
            @Override
            public void onPermissionGranted(String message, int requestCode)
            {
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPermissionDenied(String message, int requestCode)
            {
//                Toast.makeText(MainActivity.this, "PermissionManagerInterface::OnPermissionDenied()", Toast.LENGTH_SHORT).show();
                android.os.Process.killProcess(Process.myPid());
                System.exit(1);
            }
        };

        permissionManager = PermissionManager.getInstance(this);

        // enumerate and return all the required permissions
        List<PermissionRequestObject> listPermission = permissionManager.enumeratePermissions(getPackageManager(), this.getPackageName());

        // grant them one-by-one
        for(int index=0; index<listPermission.size(); index++)
        {
            permissionManager.askPermission(
                    MainActivity.this,
                    listPermission.get(index).getPermissionName(),
                    permissionManagerInterface,
                    listPermission.get(index).getPermissionCode());
        }
    }

    protected void OnClick_SecondActivity(View view)
    {

    }

    protected void OnClick_ReadExternalStorage(View view)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuAbout:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
//        globalPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
