package com.example.r20np.seller.util;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r20np.seller.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import javax.inject.Inject;


/**
 * Created by R20NP on 2017-05-22.
 */

public class MyLocation implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, ResultCallback<LocationSettingsResult>{
    @Inject
    GoogleApiClient.Builder googleApiBuilder;
    @Inject
    LocationRequest locationRequest;

    private GoogleApiClient googleApiClient;
    private LocationSettingsRequest locationSettingsRequest;
    private Activity activity;
    private Location lastLocation;
    private boolean requestingLocationUpdates = false;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private ProgressDialog dialog;


    public static final String MYADDRESS = "myaddress";
    public static final String MYLAT = "mylat";
    public static final String MYLNG = "mylng";

    private static final int UPDATE_INTERVAL = 0; // 10 sec
    private static final int FATEST_INTERVAL = 0; // 5 sec
    private static final int DISPLACEMENT = 10; // 10 meters

    public static final int REQUEST_CHECK_SETTINGS = 0x1;

    LinearLayout linearLayout;
    ImageView imageView;
    TextView et;

    public MyLocation(Activity activity) {
        this.activity = activity;
        imageView = (ImageView) activity.findViewById(R.id.main_iv_gps_setting);
        linearLayout = (LinearLayout) activity.findViewById(R.id.mian_ll_location);
        et = (TextView) activity.findViewById(R.id.main_search_et);
        dialog = new ProgressDialog(activity);
        dialog.setMessage("현재 위치 정보를 가져오는 중 입니다.");

        if(checkPlayServices(activity)) {
            createLocationRequest();
            buildGoogleApiClient();
            buildLocationSettingsRequest();

        }
    }



    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FATEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //  mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildGoogleApiClient() {
        /*googleApiClient = googleApiBuilder.addConnectionCallbacks(this)
                                          .addOnConnectionFailedListener(this)
                                          .addApi(LocationServices.API).build();*/
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        locationSettingsRequest = builder.build();

    }

    public void checkLocationSettings() {
        Log.d("mylocation", "checkLocationSettings");
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        googleApiClient,
                        locationSettingsRequest
                );
        result.setResultCallback(this);


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkLocationSettings();
        Log.d("mylocation", "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("mylocation", "onConnectionFailed");
    }

    private void getLastLocation(){
        if(PermissionUtil.checkPermissions(activity,Manifest.permission.ACCESS_FINE_LOCATION)
                 ||PermissionUtil.checkPermissions(activity,Manifest.permission.ACCESS_COARSE_LOCATION)){
             lastLocation = LocationServices.FusedLocationApi
                     .getLastLocation(googleApiClient);
        }else {
            PermissionUtil.requestLocationPermissions(activity);
        }

    }

   /* private void getLastLocation(){
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        }else {
            lastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(googleApiClient);
        }
    }*/

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        Log.d("mylocation", "onResult()");
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                Log.d("mylocation", "All location settings are satisfied.");
                requestingLocationUpdates = true;
                getLastLocation();
                setImageState();
                startLocationUpdates();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                Log.d("mylocation", "Location settings are not satisfied. Show the user a dialog to" +
                        "upgrade location settings ");
                requestingLocationUpdates = false;
                setImageState();
                showDialog();

                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.d("mylocation", "Location settings are inadequate, and cannot be fixed here. Dialog " +
                        "not created.");

                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("mylocation", "onLocationChanged");
        lastLocation = location;
        updateLocationUI();
        stopLocationUpdates();
    }

    public void startLocationUpdates() {
        Log.d("mylocation", "startLocationUpdates");
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                        PermissionUtil.REQUEST_LOCATION);

        }else {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient,
                    locationRequest,
                    this
            ).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    requestingLocationUpdates = true;
                    dialog.show();
                }
            });


        }


    }


    public void stopLocationUpdates() {
        Log.d("mylocation", "stopLocationUpdates");
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient,
                this
        ).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                requestingLocationUpdates = false;
                dialog.dismiss();
                updateUI();
            }
        });
    }

    private void setImageState() {

        Log.d("mylocation", "setImageState");
        if (requestingLocationUpdates) {
            imageView.setImageResource(R.drawable.gpson);
        } else {
            imageView.setImageResource(R.drawable.gpsoff);
       /*     TooltipWindow tipWindow = MainActivity.tooltipWindow;
            if(!tipWindow.isTooltipShown())
                tipWindow.showToolTip(linearLayout);*/
        }

    }
    private void updateUI() {

        Log.d("mylocation", "updateUI");

        if (lastLocation != null) {

            double latitude = lastLocation.getLatitude();
            double longitude = lastLocation.getLongitude();

            Address address = Geocoding.geocding(activity, null, latitude, longitude);

           // Store.storeAddress(activity, address);


            et.setText(address.getAdminArea() + " " + address.getLocality() + " " + address.getThoroughfare());

            Log.d("mylocation", address.getAddressLine(0) + address.getLatitude() + address.getLongitude());


        }
    }

    public void connect() {
        Log.d("mylocation", "connect");
        if (googleApiClient != null) {
            googleApiClient.connect();

        }
    }

    public void disconnect() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    private void showDialog(){
        Log.d("mylocation", "showDialog");
        final AlertDialog.Builder alert_gps = new AlertDialog.Builder(activity);
        alert_gps.setTitle("알림");

        alert_gps.setMessage("휴대폰 설정에서, 위치정보 찾기 기능을 활성화 해주세요.").setCancelable(false)
                .setPositiveButton("설정",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveSetting();
                            }
                        }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // et.setText(Store.getMyLocation(activity));
                        dialog.cancel();

                    }
                });
        alert_gps.create();
        alert_gps.show();
    }

    private void moveSetting() {
        Log.d("mylocation", "moveSetting");
        Intent gpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(gpsIntent, REQUEST_CHECK_SETTINGS);

    }

    public boolean isConnected(){
        return googleApiClient.isConnected();
    }

    private void updateLocationUI() {
        Log.d("mylocation", "updateLocationUI");
        if (lastLocation != null) {


            Toast.makeText(activity, String.format(": %f",
                    lastLocation.getLatitude()) + String.format(": %f",
                    lastLocation.getLongitude()) + String.format(": %s",
                    lastLocation),Toast.LENGTH_LONG).show();

        }
    }
    public boolean checkPlayServices(Activity activity) {
        Log.d("mylocation", "checkPlayServices");
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else
                //finish();
            return false;
        }
        return true;
    }






    public boolean isRequestingLocationUpdates() {
        return requestingLocationUpdates;
    }
}
