package com.example.r20np.seller.ui.fragment.presenter;

import com.example.r20np.seller.ui.activity.AuthActivity;
import com.example.r20np.seller.ui.fragment.contract.LoginFragmentContract;
import com.example.r20np.seller.util.ConnectionDetector;

import javax.inject.Inject;


public class LoginFragmentPresenter implements LoginFragmentContract.Presenter{
    private LoginFragmentContract.View mView;
    @Inject
    ConnectionDetector connectionDetector;

    @Inject
    AuthActivity authActivity;


    public LoginFragmentPresenter(LoginFragmentContract.View mView) {
        this.mView = mView;
    }



}
