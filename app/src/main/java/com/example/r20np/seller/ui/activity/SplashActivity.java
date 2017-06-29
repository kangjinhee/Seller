package com.example.r20np.seller.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.r20np.seller.R;
import com.example.r20np.seller.ui.component.ActivityComponent;
import com.example.r20np.seller.ui.component.SplashActivityComponent;
import com.example.r20np.seller.ui.contract.SplashAcitivityContract;
import com.example.r20np.seller.ui.module.SplashActivityModule;
import com.example.r20np.seller.ui.presenter.SplashActivityPresenter;
import com.example.r20np.seller.util.SplashDialog;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashAcitivityContract.View{
    public static final int REQUEST_CHECK_NETWORK = 0x1;

    @Inject SplashActivityPresenter presenter;
    @Inject SplashDialog splashDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    protected ActivityComponent getInitializeCompoenet() {
        return getAppComponent()
                .plus(new SplashActivityModule(this));
    }

    @Override
    protected void onInject(@Nullable ActivityComponent component) {
        assert component != null;
        ((SplashActivityComponent)component).inject(this);
    }

    private void init() {
        presenter.networkCheck();
    }


    @Override
    public void showScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        },3000);
    }

    @Override
    public void showDialog() {
        splashDialog.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CHECK_NETWORK :
                presenter.networkCheck();
                break;
        }
    }
}
