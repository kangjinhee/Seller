package com.example.r20np.seller.ui.presenter;

import com.example.r20np.seller.ui.contract.AuthActivityContract;


public class AuthActivityPresenter implements AuthActivityContract.Presenter{
    private AuthActivityContract.View mView;



    public AuthActivityPresenter(AuthActivityContract.View mView) {
        this.mView = mView;
    }



}
