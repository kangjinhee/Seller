package com.example.r20np.seller.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;

import com.example.r20np.seller.R;
import com.example.r20np.seller.ui.activity.SplashActivity;

/**
 * Created by R20NP on 2016-07-15.
 */
public class SplashDialog {
    private SplashActivity context;
   /* @Inject
    AnalyticsManager analyticsManager;*/


    public SplashDialog(SplashActivity context) {
        this.context =context;
    }

    public AlertDialog create(){
        LayoutInflater inflater = context.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_check_network, null);
        final AlertDialog.Builder alert_confirm = new AlertDialog.Builder(context);
        alert_confirm.setTitle("네트워크설정확인");
        alert_confirm.setView(dialogView);

        alert_confirm.setMessage("네트워크 연결상태를 확인해 주세요.").setCancelable(false)
                .setPositiveButton("WIFI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //analyticsManager.eventTracker("SplashDialog", "pressBtn", "WIFI");
                                context.startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), SplashActivity.REQUEST_CHECK_NETWORK);
                            }
                        }).setNegativeButton("LTE",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //analyticsManager.eventTracker("SplashDialog", "pressBtn", "LTE");
                        context.startActivityForResult(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS), SplashActivity.REQUEST_CHECK_NETWORK);

                    }
                }).setNeutralButton("종료",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //analyticsManager.eventTracker("SplashDialog", "pressBtn", "finish");
                       context.finish();
                    }
                });


        return  alert_confirm.create();
    }
}
