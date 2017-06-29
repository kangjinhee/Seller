package com.example.r20np.seller.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.example.r20np.seller.ui.activity.MainActivity;

import javax.inject.Inject;

/**
 * Created by R20NP on 2017-06-19.
 */

public class PermissionUtil {

    @Inject
    MainActivity mainActivity;
    private static String[] PERMISSION_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    public static final int REQUEST_LOCATION = 101;
    public PermissionUtil() {
    }

    public static boolean checkPermissions(Activity activity, String permission){
        int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
        if (permissionResult == PackageManager.PERMISSION_GRANTED) return true;
        else return false;
    }

    public static void requestPermissions(final @NonNull Activity activity, final @NonNull String[] permissions, final int requestCode){
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static boolean verifyPermission(int[] grantresults){
        if(grantresults.length < 1){
            return false;
        }
        for (int result:grantresults){
            if(result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestLocationPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, PERMISSION_LOCATION, REQUEST_LOCATION);
    }

    public static void showRequestAgainDialog(final MainActivity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("이 권한은 꼭 필요한 권한이므로, 설정에서 활성화부탁드립니다.");
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:"));
                    activity.startActivity(intent);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    activity.startActivity(intent);
                }

            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
