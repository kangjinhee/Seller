package com.example.r20np.seller.ui.fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.r20np.seller.R;
import com.example.r20np.seller.ui.activity.AuthActivity;
import com.example.r20np.seller.ui.component.AuthActivityComponent;
import com.example.r20np.seller.ui.fragment.contract.LoginFragmentContract;
import com.example.r20np.seller.ui.fragment.module.LoginFragmentModule;
import com.example.r20np.seller.ui.fragment.presenter.LoginFragmentPresenter;

import javax.inject.Inject;

import butterknife.BindView;


public class LoginFragment extends BaseFragment implements LoginFragmentContract.View {

    @Inject
    AuthActivity authActivity;
    @Inject
    LoginFragmentPresenter presenter;




    @BindView(R.id.login_register_btn)
    Button register_btn;
    @BindView(R.id.login_google_sign_in_btn)
    Button signInButton;
    @BindView(R.id.login_submit_btn)Button submit_btn;
    @BindView(R.id.login_id_et)EditText email;
    @BindView(R.id.login_password_et)EditText password;
    private static final int RC_SIGN_IN = 9001;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;



    public LoginFragment() {
        // Required empty public constructor
    }



    public static LoginFragment newInstance(String param1, String param2) {

        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void onCreateView() {

    }


/*
    @OnClick({R.id.login_register_btn, R.id.login_google_sign_in_btn, R.id.login_submit_btn})
    void onButtonClick(View v){
        switch (v.getId()){
            case R.id.login_register_btn :
                authActivity.updateFragment(AuthActivity.REGISTER_FRAGMENT_BACKSTACK);
                break;
            case R.id.login_google_sign_in_btn :
                signIn();
                break;
            case R.id.login_submit_btn :
                presenter.login(email.getText().toString(), password.getText().toString());
                break;
        }
    }*/




    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }


    @Override
    protected void getInitializeCompoent() {
        getComponent(AuthActivityComponent.class).plus(new LoginFragmentModule(this)).inject(this);
    }

    @Override
    protected String getFragmentName() {
        return null;
    }


}
