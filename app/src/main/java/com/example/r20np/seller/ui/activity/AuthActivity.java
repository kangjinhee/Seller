package com.example.r20np.seller.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.r20np.seller.R;
import com.example.r20np.seller.SellerApplication;
import com.example.r20np.seller.ui.component.ActivityComponent;
import com.example.r20np.seller.ui.component.AuthActivityComponent;
import com.example.r20np.seller.ui.contract.AuthActivityContract;
import com.example.r20np.seller.ui.fragment.LoginFragment;
import com.example.r20np.seller.ui.fragment.RegisterFragment;
import com.example.r20np.seller.ui.module.AuthActivityModule;
import com.example.r20np.seller.ui.presenter.AuthActivityPresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class AuthActivity extends BaseActivity implements AuthActivityContract.View {
    public static final String TYPE ="type";
    public static final int LOGIN_FRAGMENT =0;
    public static final int REGISTER_FRAGMENT_BACKSTACK=3;
    public static final int REGISTER_FRAGMENT =1;
    public static final int USER_DETAIL_FRAGMENT =2;
    @Inject
    AuthActivityPresenter presenter;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBar actionBar;

    public static void startWithFragmentType(int type, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, AuthActivity.class);
        intent.putExtra(TYPE, type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_auth);
        super.onCreate(savedInstanceState);
        setupToolbar();

        /*init();
*/
        updateFragment(getIntent().getIntExtra(TYPE, LOGIN_FRAGMENT));
    }


    public void setupToolbar(){
        setSupportActionBar(toolbar);
        actionBar =getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }




    public void updateFragment(int type){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if (type==LOGIN_FRAGMENT){
            actionBar.setTitle(R.string.loginFragmentTitle);
            fragmentTransaction
                    .replace(R.id.auth_container, LoginFragment.newInstance(null, null));
        }else if(type==REGISTER_FRAGMENT||type==REGISTER_FRAGMENT_BACKSTACK){
            actionBar.setTitle(R.string.registerFragmentTitle);
            fragmentTransaction
                    .replace(R.id.auth_container, RegisterFragment.newInstance(null, null));
            if (type==REGISTER_FRAGMENT_BACKSTACK){
                fragmentTransaction.addToBackStack(null);
            }
        }/*else if (type==USER_DETAIL_FRAGMENT){
            actionBar.setTitle(R.string.userDetailFragmentTitle);
            fragmentTransaction.replace(R.id.auth_container, UserDetailFragment.newInstance(null, null));
        }*/
        fragmentTransaction.commit();
    }

    @Override
    protected ActivityComponent getInitializeCompoenet() {
         return SellerApplication.get(this)
                .getAppComponent()
                .plus(new AuthActivityModule(this));
    }

    @Override
    protected void onInject(@Nullable ActivityComponent component) {
        if (component != null){
            ((AuthActivityComponent) component).inject(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }






}
