package com.example.r20np.seller.ui.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.r20np.seller.ui.activity.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by R20NP on 2016-07-27.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return this.activity;
    }



}
