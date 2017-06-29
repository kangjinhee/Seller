package com.example.r20np.seller.ui.contract;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.Page;


/**
 * Created by R20NP on 2017-05-09.
 */

public interface MainActivityContract {
    interface View {
        void showAD(ArrayList<Page> pageViews);
       // void meCompleted(User user,String type)



    }

    interface Presenter {
        void getAD();
    }
}
