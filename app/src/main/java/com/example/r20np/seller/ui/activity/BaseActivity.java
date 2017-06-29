package com.example.r20np.seller.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.r20np.seller.AppComponent;
import com.example.r20np.seller.SellerApplication;
import com.example.r20np.seller.ui.component.ActivityComponent;
import com.example.r20np.seller.ui.component.HasComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements HasComponent<ActivityComponent> {

    protected ActivityComponent component;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);
        this.component = getInitializeCompoenet();

        onInject(this.component);


    }



    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public ActivityComponent getComponent() {
        return this.component;
    }

    protected AppComponent getAppComponent() {
        return SellerApplication.get(this).getAppComponent();
    }

    protected abstract ActivityComponent getInitializeCompoenet();
    protected abstract void onInject(@Nullable ActivityComponent component);
}
