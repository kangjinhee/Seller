package com.example.r20np.seller.ui.contract;


public interface SplashAcitivityContract {
    interface View {
        void showScreen();
        void showDialog();

    }

    interface Presenter {
        void networkCheck();
    }
}
