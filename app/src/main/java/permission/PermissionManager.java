package permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import constants.PermissionCodeGen;
import constants.PermissionRequestObject;

/**
 * Created by Raza on 12/23/16.
 * Edited by Muzammil on Feb 07, 2017
 */

public class PermissionManager implements RequestPermissionsResultInterface
{
    private Activity mActivity;
    private static Context mContext;
    private static PermissionManager mPermissionManager;
    private static volatile PermissionManagerInterface mManagerInterface;

    private static List<String> mListNormalPermissions;// = new ArrayList<>();
//            .("ACCESS_LOCATION_EXTRA_COMMANDS");
//            "ACCESS_NETWORK_STATE",
//            "ACCESS_NOTIFICATION_POLICY",
//            "ACCESS_WIFI_STATE",
//            "BLUETOOTH",
//            "BLUETOOTH_ADMIN",
//            "BROADCAST_STICKY",
//            "CHANGE_NETWORK_STATE",
//            "CHANGE_WIFI_MULTICAST_STATE",
//            "CHANGE_WIFI_STATE",
//            "DISABLE_KEYGUARD",
//            "EXPAND_STATUS_BAR",
//            "GET_PACKAGE_SIZE",
//            "INSTALL_SHORTCUT",
//            "INTERNET",
//            "KILL_BACKGROUND_PROCESSES",
//            "MODIFY_AUDIO_SETTINGS",
//            "NFC",
//            "READ_SYNC_SETTINGS",
//            "READ_SYNC_STATS",
//            "RECEIVE_BOOT_COMPLETED",
//            "REORDER_TASKS",
//            "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS",
//            "REQUEST_INSTALL_PACKAGES",
//            "SET_ALARM",
//            "SET_TIME_ZONE",
//            "SET_WALLPAPER",
//            "SET_WALLPAPER_HINTS",
//            "TRANSMIT_IR",
//            "UNINSTALL_SHORTCUT",
//            "USE_FINGERPRINT",
//            "VIBRATE",
//            "WAKE_LOCK",
//            "WRITE_SYNC_SETTINGS"};

    public PermissionManager()
    {
    }

    private static void prepareListNormalPermissions()
    {
        mListNormalPermissions = new ArrayList<>();

        mListNormalPermissions.add("ACCESS_LOCATION_EXTRA_COMMANDS");
        mListNormalPermissions.add("ACCESS_NETWORK_STATE");
        mListNormalPermissions.add("ACCESS_NOTIFICATION_POLICY");
        mListNormalPermissions.add("ACCESS_WIFI_STATE");
        mListNormalPermissions.add("BLUETOOTH");
        mListNormalPermissions.add("BLUETOOTH_ADMIN");
        mListNormalPermissions.add("BROADCAST_STICKY");
        mListNormalPermissions.add("CHANGE_NETWORK_STATE");
        mListNormalPermissions.add("CHANGE_WIFI_MULTICAST_STATE");
        mListNormalPermissions.add("CHANGE_WIFI_STATE");
        mListNormalPermissions.add("DISABLE_KEYGUARD");
        mListNormalPermissions.add("EXPAND_STATUS_BAR");
        mListNormalPermissions.add("GET_PACKAGE_SIZE");
        mListNormalPermissions.add("INSTALL_SHORTCUT");
        mListNormalPermissions.add("INTERNET");
        mListNormalPermissions.add("KILL_BACKGROUND_PROCESSES");
        mListNormalPermissions.add("MODIFY_AUDIO_SETTINGS");
        mListNormalPermissions.add("NFC");
        mListNormalPermissions.add("READ_SYNC_SETTINGS");
        mListNormalPermissions.add("READ_SYNC_STATS");
        mListNormalPermissions.add("RECEIVE_BOOT_COMPLETED");
        mListNormalPermissions.add("REORDER_TASKS");
        mListNormalPermissions.add("REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        mListNormalPermissions.add("REQUEST_INSTALL_PACKAGES");
        mListNormalPermissions.add("SET_ALARM");
        mListNormalPermissions.add("SET_TIME_ZONE");
        mListNormalPermissions.add("SET_WALLPAPER");
        mListNormalPermissions.add("SET_WALLPAPER_HINTS");
        mListNormalPermissions.add("TRANSMIT_IR");
        mListNormalPermissions.add("UNINSTALL_SHORTCUT");
        mListNormalPermissions.add("USE_FINGERPRINT");
        mListNormalPermissions.add("VIBRATE");
        mListNormalPermissions.add("WAKE_LOCK");
        mListNormalPermissions.add("WRITE_SYNC_SETTINGS");
    }

    public static PermissionManager getInstance(Context context)
    {
        if (mPermissionManager == null)
        {
            synchronized (PermissionManager.class)
            {
                if (mPermissionManager == null)
                {
                    mPermissionManager = new PermissionManager(context);
                }
            }
        }

        if(mListNormalPermissions == null || mListNormalPermissions.size() != 34)
            prepareListNormalPermissions();

        return mPermissionManager;
    }

    public PermissionManager(Context context) {
        mContext = context;
    }

    private boolean isPermitted(String permissionName)
    {
        int result = ContextCompat.checkSelfPermission(mActivity.getApplicationContext(), permissionName);
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    public List<PermissionRequestObject> enumeratePermissions(PackageManager packageManager, String packageName)
    {
        List<PermissionRequestObject> listPermission = new ArrayList<>();

        try
        {
            PackageInfo info = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

            if (info.requestedPermissions != null)
            {
                for (String permission : info.requestedPermissions)
                {
                    String packageStrippedPermissionName =  permission.substring(permission.lastIndexOf(".")+1);
                    int permissionCode = PermissionCodeGen.getPermissionCode(packageStrippedPermissionName);

                    PermissionRequestObject pro = new PermissionRequestObject(permissionCode, permission);
                    listPermission.add(pro);
                }
            }
        }
        catch (Exception ex)
        {

        }

        return listPermission;
    }

    public void askPermission(
            final Activity mActivity,
            final String permissionName,
            final PermissionManagerInterface managerInterface,
            final int requestCode)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            // check whether normal permission.... if so, return
            final String packageStrippedPermissionName =  permissionName.substring(permissionName.lastIndexOf(".")+1);
            if(mListNormalPermissions.contains(packageStrippedPermissionName))
                return;

            this.mActivity = mActivity;
            this.mManagerInterface = managerInterface;

            boolean isPermitted = isPermitted(permissionName);
            if(isPermitted == false)
            {
                // following condition is TRUE only when user previously DENIED permission
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permissionName))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage("Essential permission required: " + packageStrippedPermissionName);
                    builder.setCancelable(false);
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                    {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                        {
                            onRequestPermissionsResult(requestCode,
                                    new String[]{ permissionName},
                                    new int[] {-1});
                        }
                    });
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(
                                    mActivity,
                                    new String[]{permissionName},
                                    requestCode);
                        }
                    });

                    final AlertDialog alert = builder.create();
                    alert.show();
                }
                else
                {
                    ActivityCompat.requestPermissions(
                            mActivity,
                            new String[]{permissionName},
                            requestCode);
                }
            }
            else managerInterface.onPermissionGranted(packageStrippedPermissionName + ": Already Granted", requestCode);//Toast.makeText(getBaseContext(), "Permission (ExternalStrg) Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        String packageStrippedPermissionName =  permissions[0].substring(permissions[0].lastIndexOf(".")+1);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            mManagerInterface.onPermissionGranted("\'" + packageStrippedPermissionName + "\': permission granted", requestCode);
        }
        else
        {
            mManagerInterface.onPermissionDenied("\'" + packageStrippedPermissionName + "': permission denied", requestCode);
        }
    }
}
