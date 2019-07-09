package com.indeema.library.androidutils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;

/**
 * Utility methods for working with different connectivity.
 */

public class ConnectivityUtils {

    /**
     * Check Internet Connection
     *
     * @return positive if connected
     */
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * @return positive if enable
     */
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //noinspection deprecation
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    /**
     * Open OS Airplane mode Setting
     */
    public static void openAirplaneModeSetting(Context context) {
        // :1  try to open os airplane setting
        // :2  on error open wifi setting
        try {
            Intent intentAirplaneMode = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            intentAirplaneMode.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentAirplaneMode);
        } catch (ActivityNotFoundException e) {
            Intent intentForNewOS = new Intent("android.settings.WIRELESS_SETTINGS");
            intentForNewOS.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentForNewOS);
        }
    }

    /**
     * Open OS GPS Setting
     */
    public static void openLocationSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * Open OS Bluetooth Setting
     */
    public static void openBluetoothSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * Check if power connector is currently connected to the device
     *
     * @return current connector status plug/unplug
     */
    public static boolean isPowerPlugIn(Context context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, filter);
        if (batteryStatus != null) {
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            return (chargePlug == BatteryManager.BATTERY_PLUGGED_USB || chargePlug == BatteryManager.BATTERY_PLUGGED_AC);

        } else {
            return false;
        }
    }

    /**
     * Check if wired headset is currently connected to the device
     *
     * @return current connector status plug/unplug
     */
    public static boolean isWiredHeadsetOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            return audioManager.isWiredHeadsetOn();
        } else {
            return false;
        }
    }

}
