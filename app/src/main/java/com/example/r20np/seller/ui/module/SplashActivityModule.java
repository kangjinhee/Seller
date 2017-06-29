package com.example.r20np.seller.ui.module;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.activity.SplashActivity;
import com.example.r20np.seller.ui.contract.SplashAcitivityContract;
import com.example.r20np.seller.ui.presenter.SplashActivityPresenter;
import com.example.r20np.seller.util.ConnectionDetector;
import com.example.r20np.seller.util.SplashDialog;

import dagger.Module;
import dagger.Provides;


@Module
public class SplashActivityModule {
    private SplashActivity splashActivity;
    private final SplashAcitivityContract.View mView;

    public SplashActivityModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
        this.mView = splashActivity;

    }
/*    @Provides
    @ActivityScope
    SplashAcitivityContract.View provideSplashActivityContractView() {
        return mView;
    }*/

    @Provides
    @ActivityScope
    SplashActivity provideSplashActivity() {
        return splashActivity;
    }

    @Provides
    @ActivityScope
    SplashDialog provideSplashDialog(){
        return new SplashDialog(splashActivity);
    }

    @Provides
    @ActivityScope
    SplashActivityPresenter provideSplashActivityPresenter(ConnectionDetector connectionDetector) {
        return new SplashActivityPresenter(splashActivity, connectionDetector);
    }
}
