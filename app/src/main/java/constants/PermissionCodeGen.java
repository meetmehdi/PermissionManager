package constants;

/**
 * Created by Muzammil on 02/02/2017.
 */

public class PermissionCodeGen
{

// normal permissions

    static int ACCESS_LOCATION_EXTRA_COMMANDS = 1010;
    static int ACCESS_NETWORK_STATE = 1011;
    static int ACCESS_NOTIFICATION_POLICY = 1012;
    static int ACCESS_WIFI_STATE = 1013;
    static int BLUETOOTH = 1014;
    static int BLUETOOTH_ADMIN = 1015;
    static int BROADCAST_STICKY = 1016;
    static int CHANGE_NETWORK_STATE = 1017;
    static int CHANGE_WIFI_MULTICAST_STATE = 1018;
    static int CHANGE_WIFI_STATE = 1019;
    static int DISABLE_KEYGUARD = 1020;
    static int EXPAND_STATUS_BAR = 1021;
    static int GET_PACKAGE_SIZE = 1022;
    static int INSTALL_SHORTCUT = 1023;
    static int INTERNET = 1024;
    static int KILL_BACKGROUND_PROCESSES = 1025;
    static int MODIFY_AUDIO_SETTINGS = 1026;
    static int NFC = 1027;
    static int READ_SYNC_SETTINGS = 1028;
    static int READ_SYNC_STATS = 1029;
    static int RECEIVE_BOOT_COMPLETED = 1030;
    static int REORDER_TASKS = 1031;
    static int REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 1032;
    static int REQUEST_INSTALL_PACKAGES = 1033;
    static int SET_ALARM = 1034;
    static int SET_TIME_ZONE = 1035;
    static int SET_WALLPAPER = 1036;
    static int SET_WALLPAPER_HINTS = 1037;
    static int TRANSMIT_IR = 1038;
    static int UNINSTALL_SHORTCUT = 1039;
    static int USE_FINGERPRINT = 1040;
    static int VIBRATE = 1041;
    static int WAKE_LOCK = 1042;
    static int WRITE_SYNC_SETTINGS = 1043;

// dangerous permissions

    static int READ_CALENDAR = 1050;
    static int WRITE_CALENDAR = 1051;
    static int CAMERA = 1052;
    static int READ_CONTACTS = 1053;
    static int WRITE_CONTACTS = 1054;
    static int GET_ACCOUNTS = 1055;
    static int ACCESS_FINE_LOCATION = 1056;
    static int ACCESS_COARSE_LOCATION = 1057;
    static int RECORD_AUDIO = 1058;
    static int READ_PHONE_STATE = 1059;
    static int CALL_PHONE = 1060;
    static int READ_CALL_LOG = 1061;
    static int WRITE_CALL_LOG = 1062;
    static int ADD_VOICEMAIL = 1063;
    static int USE_SIP = 1064;
    static int PROCESS_OUTGOING_CALLS = 1065;
    static int BODY_SENSORS = 1066;
    static int SEND_SMS = 1067;
    static int RECEIVE_SMS = 1068;
    static int READ_SMS = 1069;
    static int RECEIVE_WAP_PUSH = 1070;
    static int RECEIVE_MMS = 1071;
    static int READ_EXTERNAL_STORAGE = 1072;
    static int WRITE_EXTERNAL_STORAGE = 1073;

    public static int getPermissionCode(String permissionName)
    {
        switch (permissionName)
        {
            case "ACCESS_LOCATION_EXTRA_COMMANDS":
                return ACCESS_LOCATION_EXTRA_COMMANDS;
            case "ACCESS_NETWORK_STATE":
                return ACCESS_NETWORK_STATE;
            case "ACCESS_NOTIFICATION_POLICY":
                return ACCESS_NOTIFICATION_POLICY;
            case "ACCESS_WIFI_STATE":
                return ACCESS_WIFI_STATE;
            case "BLUETOOTH":
                return BLUETOOTH;
            case "BLUETOOTH_ADMIN":
                return BLUETOOTH_ADMIN;
            case "BROADCAST_STICKY":
                return BROADCAST_STICKY;
            case "CHANGE_NETWORK_STATE":
                return CHANGE_NETWORK_STATE;
            case "CHANGE_WIFI_MULTICAST_STATE":
                return CHANGE_WIFI_MULTICAST_STATE;
            case "CHANGE_WIFI_STATE":
                return CHANGE_WIFI_STATE;
            case "DISABLE_KEYGUARD":
                return DISABLE_KEYGUARD;
            case "EXPAND_STATUS_BAR":
                    return EXPAND_STATUS_BAR;
            case "GET_PACKAGE_SIZE":
                return GET_PACKAGE_SIZE;
            case "INSTALL_SHORTCUT":
                return INSTALL_SHORTCUT;
            case "INTERNET":
                return INTERNET;
            case "KILL_BACKGROUND_PROCESSES":
                return KILL_BACKGROUND_PROCESSES;
            case "MODIFY_AUDIO_SETTINGS":
                return MODIFY_AUDIO_SETTINGS;
            case "NFC":
                return NFC;
            case "READ_SYNC_SETTINGS":
                return READ_SYNC_SETTINGS;
            case "READ_SYNC_STATS":
                return READ_SYNC_STATS;
            case "RECEIVE_BOOT_COMPLETED":
                return RECEIVE_BOOT_COMPLETED;
            case "REORDER_TASKS":
                return REORDER_TASKS;
            case "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS":
                return REQUEST_IGNORE_BATTERY_OPTIMIZATIONS;
            case "REQUEST_INSTALL_PACKAGES":
                return REQUEST_INSTALL_PACKAGES;
            case "SET_ALARM":
                return SET_ALARM;
            case "SET_TIME_ZONE":
                return SET_TIME_ZONE;
            case "SET_WALLPAPER":
                return SET_WALLPAPER;
            case "SET_WALLPAPER_HINTS":
                return SET_WALLPAPER_HINTS;
            case "TRANSMIT_IR":
                return TRANSMIT_IR;
            case "UNINSTALL_SHORTCUT":
                return UNINSTALL_SHORTCUT;
            case "USE_FINGERPRINT":
                return USE_FINGERPRINT;
            case "VIBRATE":
                return VIBRATE;
            case "WAKE_LOCK":
                return WAKE_LOCK;
            case "WRITE_SYNC_SETTINGS":
                return WRITE_SYNC_SETTINGS;
            case "READ_CALENDAR":
                return READ_CALENDAR;
            case "WRITE_CALENDAR":
                return WRITE_CALENDAR;
            case "CAMERA":
                return CAMERA;
            case "READ_CONTACTS":
                return READ_CONTACTS;
            case "WRITE_CONTACTS":
                return GET_ACCOUNTS;
            case "GET_ACCOUNTS":
                return GET_ACCOUNTS;
            case "ACCESS_FINE_LOCATION":
                return ACCESS_FINE_LOCATION;
            case "ACCESS_COARSE_LOCATION":
                return ACCESS_COARSE_LOCATION;
            case "RECORD_AUDIO":
                return RECORD_AUDIO;
            case "READ_PHONE_STATE":
                return READ_PHONE_STATE;
            case "CALL_PHONE":
                return CALL_PHONE;
            case "READ_CALL_LOG":
                return READ_CALL_LOG;
            case "WRITE_CALL_LOG":
                return WRITE_CALL_LOG;
            case "ADD_VOICEMAIL":
                return ADD_VOICEMAIL;
            case "USE_SIP":
                return USE_SIP;
            case "PROCESS_OUTGOING_CALLS":
                return PROCESS_OUTGOING_CALLS;
            case "BODY_SENSORS":
                return BODY_SENSORS;
            case "SEND_SMS":
                  return SEND_SMS;
            case "RECEIVE_SMS":
                return RECEIVE_SMS;
            case "READ_SMS":
                return READ_SMS;
            case "RECEIVE_WAP_PUSH":
                return RECEIVE_WAP_PUSH;
            case "RECEIVE_MMS":
                return RECEIVE_MMS;
            case "READ_EXTERNAL_STORAGE":
                return READ_EXTERNAL_STORAGE;
            case "WRITE_EXTERNAL_STORAGE":
                return WRITE_EXTERNAL_STORAGE;
        }

        return -1;
    }
}
