package com.example.r20np.seller.ui.module;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.activity.AuthActivity;
import com.example.r20np.seller.ui.contract.AuthActivityContract;
import com.example.r20np.seller.ui.presenter.AuthActivityPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class AuthActivityModule {
    private AuthActivity authActivity;
    private final AuthActivityContract.View mView;


    public AuthActivityModule(AuthActivity authActivity) {
        this.authActivity = authActivity;
        this.mView = authActivity;

    }

    @Provides
    @ActivityScope
    AuthActivityContract.View provideAuthActivityContractView() {
        return mView;

    }

    @Provides
    @ActivityScope
    AuthActivity provideAuthActivity() {
        return authActivity;
    }

    @Provides
    @ActivityScope
    AuthActivityPresenter provideAuthActivityPresenter(){
        return new AuthActivityPresenter(mView);
    }







}
