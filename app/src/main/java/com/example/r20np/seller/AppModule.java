package com.example.r20np.seller;

import android.app.Application;

import com.example.r20np.seller.util.ConnectionDetector;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by R20NP on 2017-05-08.
 */

@Module
public class AppModule {
    private Application application;
    public AppModule(Application application){this.application = application;}

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    ConnectionDetector provideConnectionDetector(){
        return new ConnectionDetector(application);
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application application){
        return new Picasso.Builder(application).build();
    }



}

