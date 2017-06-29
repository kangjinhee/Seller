package com.example.r20np.seller.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.r20np.seller.R;
import com.example.r20np.seller.ui.component.ActivityComponent;
import com.example.r20np.seller.ui.component.MainActivityComponent;
import com.example.r20np.seller.ui.contract.MainActivityContract;
import com.example.r20np.seller.ui.module.MainActivityModule;
import com.example.r20np.seller.ui.presenter.MainActivityPresenter;
import com.example.r20np.seller.util.MyLocation;
import com.example.r20np.seller.util.PermissionUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;


public class MainActivity extends BaseActivity implements MainActivityContract.View, OnPageClickListener, View.OnClickListener
                                                           {
    @Inject MainActivityPresenter presenter;
    @Inject IndicatorConfiguration configuration;
    @Inject MyLocation myLocation;
    @BindView(R.id.main_toolbar) Toolbar toolbar;
    @BindView(R.id.main_indicator) InfiniteIndicator infiniteIndicator;
    @BindView(R.id.main_appbar) public AppBarLayout appBarLayout;
    @BindView(R.id.main_collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.main_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.main_search_et)
    public AppCompatEditText popupEditText;
    ArrayList<Page> pageViews = null;
    public int HOMEICON = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
       // setupSearchBar();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        infiniteIndicator.start();
        if (pageViews ==null){
            presenter.getAD();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        infiniteIndicator.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myLocation.disconnect();
    }

    private void init() {
        setupToolbar();
        initIndicator();
        myLocation.connect();


    }

    private void initIndicator() {
        infiniteIndicator.init(configuration);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /* set title only when collapsed */
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isVisible = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setTitle("Seller");
                    isVisible = true;
                } else if(isVisible) {
                    toolbar.setTitle("");
                    isVisible = false;
                }
            }
        });
    }

    @Override
    protected ActivityComponent getInitializeCompoenet() {
        return getAppComponent().plus(new MainActivityModule(this));
    }

    @Override
    protected void onInject(@Nullable ActivityComponent component) {
        if (component != null){
            ((MainActivityComponent) component).inject(this);
        }
    }

    @Override
    public void showAD(ArrayList<Page> pageViews) {
        this.pageViews =pageViews;
        infiniteIndicator.notifyDataChange(pageViews);
    }

    @Override
    public void onPageClick(int position, Page page) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(page.data));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @OnClick({R.id.nav_login_btn, R.id.nav_register_btn,R.id.nav_user_setting_iv})
    void onButtonClick(View v){
        switch (v.getId()){
            case R.id.nav_login_btn:
                AuthActivity.startWithFragmentType(AuthActivity.LOGIN_FRAGMENT, this);
                break;
            case R.id.nav_register_btn:
                AuthActivity.startWithFragmentType(AuthActivity.REGISTER_FRAGMENT, this);
                break;
            case R.id.nav_user_setting_iv:
                AuthActivity.startWithFragmentType(AuthActivity.USER_DETAIL_FRAGMENT,this);
                break;
        }
    }

    @BindView(R.id.main_location_search) public LinearLayout ll;
    @BindView(R.id.main_iv_cross) public ImageView iv;
    @BindView(R.id.main_iv_gps_setting) public ImageView iv1;
/*
    public void setupSearchBar(){
       // myDatabase = new DistrictDatabase(this);

        iv1.setOnClickListener(this);
        ll.setOnClickListener(this);
        iv.setOnClickListener(this);
    }
*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("mylocation", "onRequestPermissionsResult");
        switch (requestCode) {
            case PermissionUtil.REQUEST_LOCATION:
                Log.d("mylocation", "REQUEST_LOCATION");
                if (PermissionUtil.verifyPermission(grantResults)){
                    myLocation.startLocationUpdates();
                }else {
                    PermissionUtil.showRequestAgainDialog(this);
                    Log.d("mylocation", "shouldShowRequestPermissionRationale");
                }


        }
    }
}

