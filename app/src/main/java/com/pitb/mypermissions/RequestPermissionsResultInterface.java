package com.pitb.mypermissions;

/**
 * Created by Raza on 12/23/16.
 */

public interface RequestPermissionsResultInterface
{
    void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults);
}
