package com.example.r20np.seller.ui.fragment.module;

import com.example.r20np.seller.ui.fragment.FragmentScope;
import com.example.r20np.seller.ui.fragment.LoginFragment;
import com.example.r20np.seller.ui.fragment.contract.LoginFragmentContract;
import com.example.r20np.seller.ui.fragment.presenter.LoginFragmentPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginFragmentModule {
    private LoginFragment loginFragment;
    private final LoginFragmentContract.View mView;

    public LoginFragmentModule(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        this.mView = loginFragment;

    }

    @Provides
    @FragmentScope
    LoginFragmentContract.View provideLoginFragmentContractView() {
        return mView;
    }

    @Provides
    @FragmentScope
    LoginFragment provideLoginFragment() {
        return loginFragment;
    }

    @Provides
    @FragmentScope
    LoginFragmentPresenter provideLoginFragmentPresenter(){
        return new LoginFragmentPresenter(loginFragment);
    }

 /*   @Provides
    @FragmentScope
    GoogleSignInOptions provideGoogleSignInOptions(){
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    @Provides
    @FragmentScope
    GoogleApiClient provideGoogleApiClient(GoogleSignInOptions gso,AuthActivity authActivity){
        return new GoogleApiClient.Builder(authActivity)
                .enableAutoManage(this *//* FragmentActivity *//*, this *//* OnConnectionFailedListener *//*)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Provides
    @FragmentScope
    GoogleApiClient.OnConnectionFailedListener provideOnConnectionFailedListener(){
        return new GoogleApiClient.Builder(authActivity)
                .enableAutoManage(this *//* FragmentActivity *//*, this *//* OnConnectionFailedListener *//*)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }*/

}
