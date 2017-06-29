package com.example.r20np.seller.ui.component;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.module.ActivityModule;

import dagger.Component;

/**
 * Created by R20NP on 2016-07-27.
 */

@ActivityScope
@Component(
        modules = ActivityModule.class
)
public interface ActivityComponent {
}
