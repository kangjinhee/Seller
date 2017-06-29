package com.example.r20np.seller.ui.fragment.presenter;

import com.example.r20np.seller.ui.activity.AuthActivity;
import com.example.r20np.seller.ui.fragment.contract.RegisterFragmentContract;
import com.example.r20np.seller.util.ConnectionDetector;

import javax.inject.Inject;


public class RegisterFragmentPresenter implements RegisterFragmentContract.Presenter{
    private RegisterFragmentContract.View mView;
    @Inject
    ConnectionDetector connectionDetector;

    @Inject
    AuthActivity authActivity;

    @Inject
    public RegisterFragmentPresenter(RegisterFragmentContract.View mView) {
        this.mView = mView;
    }




}
