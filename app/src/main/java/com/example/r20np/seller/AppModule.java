package com.example.r20np.seller;

import android.app.Application;

import dagger.Module;

/**
 * Created by R20NP on 2017-05-08.
 */

@Module
public class AppModule {
    private Application application;
    public AppModule(Application application){this.application = application;}
}

