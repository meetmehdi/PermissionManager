package permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Muzammil on 02/02/2017.
 */

public class GlobalPermissionManager extends Application implements RequestPermissionsResultInterface
{
    private Activity mActivity;
    private static Context mContext;
    private static PermissionManager mPermissionManager;
    private static volatile PermissionManagerInterface mManagerInterface;


    public GlobalPermissionManager()
    {
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
        return mPermissionManager;
    }

    public GlobalPermissionManager(Context context) {
        mContext = context;
    }

    public static boolean hasPermissions(Context context, String... permissions)
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPermitted(String permissionName)
    {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), permissionName);
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    public void askPermission(
            final Activity mActivity,
            final String permissionName,
            final PermissionManagerInterface managerInterface,
            final int requestCode)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            this.mActivity = mActivity;
            this.mManagerInterface = managerInterface;

            boolean isPermitted = isPermitted(permissionName);
            if(isPermitted == false)
            {
                // following condition is TRUE only when user previously DENIED permission
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permissionName))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage("Please consider giving required permissions.");
                    builder.setCancelable(false);
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                    {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                        {
                            onRequestPermissionsResult(requestCode, new String[]{ permissionName}, new int[] {-1});
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
            else managerInterface.onPermissionGranted("Permission Already Granted", requestCode);//Toast.makeText(getBaseContext(), "Permission (ExternalStrg) Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            mManagerInterface.onPermissionGranted("Permission Granted", requestCode);
        }
        else
        {
            mManagerInterface.onPermissionDenied("Permission Denied", requestCode);
        }
    }
}
