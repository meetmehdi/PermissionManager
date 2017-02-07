package constants;

/**
 * Created by Muzammil on 07/02/2017.
 */

public class PermissionRequestObject
{
    public int permissionCode;
    public String permissionName;

    public PermissionRequestObject(int code, String permissionName)
    {
        this.permissionCode = code;
        this.permissionName = permissionName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(int permissionCode) {
        this.permissionCode = permissionCode;
    }
}
