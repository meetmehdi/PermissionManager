
Introduction
===============

This is a basic PermissionManager which lets you seperate permission handling from main business logic.

This is done in just two steps.

1- Enumerate all the required permissions specified in AndroidManifest. 
2- Set permission one-by-one.


How To
=============

Assuming you want to ask for permissions in MainActivity::OnCreate()


PermissionManagerInterface managerInterface = new PermissionManagerInterface()
{
   // override methods
};

PermissionManager.getInstance(this);

// enumerate and return all permissions
List<PermissionRequestObject> list = PermissionManager.enumeratePermissions(...);

// iterate and set permission
for (int index=0;....)
{
    PermissionManager.askPermission(
    this,
    list.get(index).getPermissionName(),
    managerInterface,
    list.get(index).getPermissionCode())
}
