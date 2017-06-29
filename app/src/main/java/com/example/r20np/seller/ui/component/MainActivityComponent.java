package com.example.r20np.seller.ui.component;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.activity.MainActivity;
import com.example.r20np.seller.ui.module.MainActivityModule;

import dagger.Subcomponent;

/**
 * Created by R20NP on 2017-05-09.
 */
@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainActivityComponent extends ActivityComponent {
    MainActivity inject(MainActivity mainActivity);
}
