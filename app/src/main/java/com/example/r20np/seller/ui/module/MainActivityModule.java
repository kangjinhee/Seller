package com.example.r20np.seller.ui.module;

import android.support.v7.widget.LinearLayoutManager;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.activity.MainActivity;
import com.example.r20np.seller.ui.contract.MainActivityContract;
import com.example.r20np.seller.ui.presenter.MainActivityPresenter;
import com.example.r20np.seller.util.MyLocation;
import com.example.r20np.seller.util.PicassoLoader;
import com.squareup.picasso.Picasso;

import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import dagger.Module;
import dagger.Provides;

/**
 * Created by R20NP on 2017-05-09.
 */
@Module
public class MainActivityModule {
    private MainActivity mainActivity;
    private final MainActivityContract.View mView;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mView = mainActivity;

    }


    @Provides
    @ActivityScope
    MainActivityContract.View provideMainActivityContractView() {
        return mView;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    PicassoLoader providePicassoLoader(Picasso picasso) {
        return new PicassoLoader(picasso);
    }
    @Provides
    @ActivityScope
    IndicatorConfiguration provideIndicatorConfiguration(MainActivity mainActivity,PicassoLoader picassoLoader) {
        return new IndicatorConfiguration.Builder()
                .imageLoader(picassoLoader)
                .onPageClickListener(mainActivity)
                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
                .build();
    }




    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(MainActivity mainActivity) {
        return new LinearLayoutManager(mainActivity);
    }

//    @Provides
//    @ActivityScope
//    NavItemListAdapter provideNavItemListAdapter(){
//        return new NavItemListAdapter(mainActivity);
//    }


    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter(mView);
    }

    @Provides
    @ActivityScope
    MyLocation provideMyLocation(MainActivity mainActivity){
        return new MyLocation(mainActivity);
    }


}
