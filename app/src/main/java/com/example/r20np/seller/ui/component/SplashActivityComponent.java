package com.example.r20np.seller.ui.component;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.activity.SplashActivity;
import com.example.r20np.seller.ui.module.SplashActivityModule;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(
        modules = SplashActivityModule.class
)
public interface SplashActivityComponent extends ActivityComponent{
    SplashActivity inject(SplashActivity splashActivity);

}
