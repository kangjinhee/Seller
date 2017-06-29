package com.example.r20np.seller;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by R20NP on 2016-09-01.
 */
@Module
public class GoogleModule {
/*
    @Provides
    @Singleton
    GoogleSignInOptions provideGoogleSignInOptions(){
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

    }

    @Provides
    @Singleton
    GoogleApiClient provideGoogleApiClient(Application application, GoogleSignInOptions gso){
        return new GoogleApiClient.Builder(application)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }*/

    @Provides
    @Singleton
    GoogleApiClient.Builder provideGoogleApiClientBuilder(Application application){
        return new GoogleApiClient.Builder(application);
    }
    private static final int UPDATE_INTERVAL = 10*1000; // 10 sec
    private static final int FATEST_INTERVAL = 5*1000; // 5 sec
    private static final int DISPLACEMENT = 10; // 10 meters
    @Provides
    @Singleton
    LocationRequest provideLocationRequest(){
        return new LocationRequest().setInterval(UPDATE_INTERVAL)
                                    .setFastestInterval(FATEST_INTERVAL)
                                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                    .setSmallestDisplacement(DISPLACEMENT);
    }
}
