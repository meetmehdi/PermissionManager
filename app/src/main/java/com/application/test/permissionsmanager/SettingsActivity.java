package com.application.test.permissionsmanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.pitb.mypermissions.R;

import permission.PermissionManager;

import static com.application.test.permissionsmanager.MainActivity.CALL_PHONE_PERMISSION_CODE;
import static com.application.test.permissionsmanager.MainActivity.COARSE_LOCATION_PERMISSION_CODE;
import static com.application.test.permissionsmanager.MainActivity.EXTERNAL_STORAGE_PERMISSION_CODE;
import static com.application.test.permissionsmanager.MainActivity.FINE_LOCATION_PERMISSION_CODE;
import static com.application.test.permissionsmanager.MainActivity.INTERNET_PERMISSION_CODE;
import static com.application.test.permissionsmanager.MainActivity.NETWORK_STATE_PERMISSION_CODE;
import static com.application.test.permissionsmanager.MainActivity.PHONE_STATE_PERMISSION_CODE;

public class SettingsActivity extends AppCompatActivity
{

    Switch switchInternet;
    Switch switchCallPhone;
    Switch switchReadPhoneState;
    Switch switchAccessNetworkState;
    Switch switchAccessFineLocation;
    Switch switchCoarseLocation;
    Switch switchReadExternalStorage;

    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SetupSwitchButtons();
    }

    private boolean isInternetAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private boolean isCallPhoneAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private boolean isPhoneStateAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private boolean isNetworkStateAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private boolean isFineLocationAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private boolean isCoarseLocationAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private boolean isReadStorageAllowed()
    {
        int result = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        return ((result == PackageManager.PERMISSION_GRANTED));
    }

    private void SetupSwitchButtons()
    {
        switchInternet = (Switch)findViewById(R.id.switchInternet);
        switchCallPhone = (Switch)findViewById(R.id.switchCallPhone);
        switchReadPhoneState = (Switch)findViewById(R.id.switchReadPhoneState);
        switchAccessNetworkState = (Switch)findViewById(R.id.switchAccessNetworkState);
        switchAccessFineLocation = (Switch)findViewById(R.id.switchAccessFineLocation);
        switchCoarseLocation = (Switch)findViewById(R.id.switchCoarseLocation);
        switchReadExternalStorage = (Switch)findViewById(R.id.switchReadExternalStorage);

        switchInternet.setChecked(isInternetAllowed());
        switchInternet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestInternet()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        SettingsActivity.this,
                        Manifest.permission.INTERNET
                ))
                {
                    // If previously denied, show message why this should be allowed
                }

                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.INTERNET},
                        INTERNET_PERMISSION_CODE
                );
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)// Toast.makeText(getBaseContext(), "Internet ON", Toast.LENGTH_SHORT).show();
                {
                    requestInternet();
                }
                else //Toast.makeText(getBaseContext(), "Internet OFF", Toast.LENGTH_SHORT).show();
                {
                    if(isInternetAllowed())
                    {
                        switchInternet.setChecked(true);
                        Toast.makeText(SettingsActivity.this, "Please revoke through \'Settings\'", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switchCallPhone.setChecked(isCallPhoneAllowed());
        switchCallPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestCallPhone()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        SettingsActivity.this,
                        Manifest.permission.CALL_PHONE))
                {
                    // If denied previously, show message why this should be allowed
                }

                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        CALL_PHONE_PERMISSION_CODE);
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)// Toast.makeText(getBaseContext(), "CallPhone ON", Toast.LENGTH_SHORT).show();
                {
                    requestCallPhone();
                }
                else //Toast.makeText(getBaseContext(), "CallPhone OFF", Toast.LENGTH_SHORT).show();
                {
                    if(isCallPhoneAllowed())
                    {
                        switchCallPhone.setChecked(true);
                        Toast.makeText(SettingsActivity.this, "Please revoke through \'Settings\'", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switchReadPhoneState.setChecked(isPhoneStateAllowed());
        switchReadPhoneState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestReadPhoneState()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        SettingsActivity.this,
                        Manifest.permission.READ_PHONE_STATE
                ))
                {
                    // If previously denied, show message why this permission is neccessary
                }

                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        PHONE_STATE_PERMISSION_CODE);
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) //Toast.makeText(getBaseContext(), "ReadPhoneState ON", Toast.LENGTH_SHORT).show();
                {
                    requestReadPhoneState();
                }
                else //Toast.makeText(getBaseContext(), "ReadPhoneState OFF", Toast.LENGTH_SHORT).show();
                {
                    boolean bIsReadPhoneStateAllowed = isPhoneStateAllowed();
                    if(bIsReadPhoneStateAllowed)
                    {
                        switchReadPhoneState.setChecked(true);
                        Toast.makeText(SettingsActivity.this, "Please revoke through \'Settings\'", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switchAccessNetworkState.setChecked(isNetworkStateAllowed());
        switchAccessNetworkState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestNetworkState()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        SettingsActivity.this,
                        Manifest.permission.ACCESS_NETWORK_STATE))
                {
                    // If previously denied, show message why it is good idea to allow this permission
                }

                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                        NETWORK_STATE_PERMISSION_CODE
                );
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) //Toast.makeText(getBaseContext(), "AccessNetworkState ON", Toast.LENGTH_SHORT).show();
                {
                    requestNetworkState();
                }
                else //Toast.makeText(getBaseContext(), "AccessNetworkState OFF", Toast.LENGTH_SHORT).show();
                {
                    boolean bIsNetworkAllowed = isNetworkStateAllowed();
                    if(bIsNetworkAllowed)
                    {
                        switchAccessNetworkState.setChecked(true);
                        Toast.makeText(SettingsActivity.this, "Please revoke through \'Settings\'", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switchAccessFineLocation.setChecked(isFineLocationAllowed());
        switchAccessFineLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestFineLocation()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(SettingsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION))
                {
                    //If the user has denied the permission previously your code will come to this block
                    //Here you can explain why you need this permission
                    //Explain here why you need this permission
                }

                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        FINE_LOCATION_PERMISSION_CODE);
            }


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)// Toast.makeText(getBaseContext(), "AccessFineLocation ON", Toast.LENGTH_SHORT).show();
                {
                    requestFineLocation();
                }
                else //Toast.makeText(getBaseContext(), "AccessFineLocation OFF", Toast.LENGTH_SHORT).show();
                {
                    boolean bIsFineLocationAllowed = isFineLocationAllowed();
                    if(bIsFineLocationAllowed)
                    {
                        switchAccessFineLocation.setChecked(true);
                        Toast.makeText(getBaseContext(), "Please revoke though \'Settings\'", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switchCoarseLocation.setChecked(isCoarseLocationAllowed());
        switchCoarseLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestCoarseLocationPosition()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(SettingsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION))
                {
                    //If the user has denied the permission previously your code will come to this block
                    //Here you can explain why you need this permission
                    //Explain here why you need this permission
                }

                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        COARSE_LOCATION_PERMISSION_CODE);
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) //Toast.makeText(getBaseContext(), "CoarseLocation ON", Toast.LENGTH_SHORT).show();
                {
                    requestCoarseLocationPosition();
                }
                else //Toast.makeText(getBaseContext(), "CoarseLocation OFF", Toast.LENGTH_SHORT).show();
                {
                    boolean bIsCoarseLocAllowed = isCoarseLocationAllowed();
                    if(bIsCoarseLocAllowed) {
                        switchCoarseLocation.setChecked(true);
                        Toast.makeText(SettingsActivity.this, "Please revoke through \'Settings\'", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        switchReadExternalStorage.setChecked(isReadStorageAllowed());
        switchReadExternalStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            private void requestStoragePermission()
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(SettingsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
                {
                    //If the user has denied the permission previously your code will come to this block
                    //Here you can explain why you need this permission
                    //Explain here why you need this permission
                }

                // finally ask for the permission
                ActivityCompat.requestPermissions(
                        SettingsActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_PERMISSION_CODE);
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)// Toast.makeText(getBaseContext(), "ReadExternalStorage ON", Toast.LENGTH_SHORT).show();
                {
                    requestStoragePermission();
                }
                else
                {
                    boolean bIsReadExternalStorageAllowed = isReadStorageAllowed();
                    if(bIsReadExternalStorageAllowed) {
                        switchReadExternalStorage.setChecked(true);
                        Toast.makeText(SettingsActivity.this, "Please revoke through \'Settings\'", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == INTERNET_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchInternet.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == CALL_PHONE_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchCallPhone.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == PHONE_STATE_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchReadPhoneState.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == NETWORK_STATE_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchAccessNetworkState.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == FINE_LOCATION_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchAccessFineLocation.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == COARSE_LOCATION_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchReadExternalStorage.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == EXTERNAL_STORAGE_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted - " + permissions[0], Toast.LENGTH_LONG).show();
            }
            else
            {
                switchReadExternalStorage.setChecked(false);
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
