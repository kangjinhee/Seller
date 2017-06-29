package com.example.r20np.seller.ui.presenter;

import com.example.r20np.seller.ui.contract.SplashAcitivityContract;
import com.example.r20np.seller.util.ConnectionDetector;


public class SplashActivityPresenter implements SplashAcitivityContract.Presenter {
    private SplashAcitivityContract.View mView;

    private ConnectionDetector connectionDetector;

    public SplashActivityPresenter(SplashAcitivityContract.View mView, ConnectionDetector connectionDetector) {
        this.mView = mView;
        this.connectionDetector=connectionDetector;
    }


    @Override
    public void networkCheck() {
        if (connectionDetector.isConnectingToInternet()){
            mView.showScreen();
        }else {
            mView.showDialog();
        }


    }
}
