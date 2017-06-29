package com.example.r20np.seller;

import com.example.r20np.seller.ui.component.AuthActivityComponent;
import com.example.r20np.seller.ui.component.MainActivityComponent;
import com.example.r20np.seller.ui.component.SplashActivityComponent;
import com.example.r20np.seller.ui.module.AuthActivityModule;
import com.example.r20np.seller.ui.module.MainActivityModule;
import com.example.r20np.seller.ui.module.SplashActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by R20NP on 2017-05-08.
 */
@Singleton
@Component(modules = {AppModule.class,
                      GoogleModule.class})
public interface AppComponent {
    SplashActivityComponent plus(SplashActivityModule splashActivityModule);
    MainActivityComponent plus(MainActivityModule mainActivityModule);
    AuthActivityComponent plus(AuthActivityModule authActivityModule);

}

