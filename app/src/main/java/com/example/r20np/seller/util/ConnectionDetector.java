package com.example.r20np.seller.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;


public class ConnectionDetector {
    private Context context;

    public ConnectionDetector(Context context) {
        this.context = context;
    }

    public boolean isConnectingToInternet() {

        boolean connect=false;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Network[] networks = connectivityManager.getAllNetworks();
                    NetworkInfo networkInfo;
                    for (Network mNetwork : networks) {
                        networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                        if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                            connect = isConnectedToInternet(context);
                        }
                    }
            }else {
                    //noinspection deprecation
                    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                            if (info != null) {
                                for (NetworkInfo networkInfo : info) {
                                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                                        connect=isConnectedToInternet(context);
                                    }
                               }
                           }
                  }
        }
        return connect;
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return !(networkInfo == null || !networkInfo.isConnected());
    }



}

