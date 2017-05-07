package com.example.r20np.seller;

import android.app.Application;
import android.content.Context;

/**
 * Created by R20NP on 2017-05-07.
 */
public class SellerApplication extends Application  {

    private AppComponent appComponent;

    public static  SellerApplication get(Context context){
       return (SellerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
