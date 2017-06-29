package com.example.r20np.seller.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.r20np.seller.R;
import com.example.r20np.seller.ui.activity.AuthActivity;
import com.example.r20np.seller.ui.component.AuthActivityComponent;
import com.example.r20np.seller.ui.fragment.contract.RegisterFragmentContract;
import com.example.r20np.seller.ui.fragment.module.RegisterFragmentModule;
import com.example.r20np.seller.ui.fragment.presenter.RegisterFragmentPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterFragmentContract.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @BindView(R.id.login_id_et)EditText email;
    @BindView(R.id.login_password_et)EditText password;
    @BindView(R.id.regitser_name_et)EditText displayName;
    @BindView(R.id.login_password_confirm_et)EditText passwordConfirm;
    @Inject
    AuthActivity authActivity;
    @Inject
    RegisterFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void onCreateView() {

    }





    @Override
    protected void getInitializeCompoent() {
        getComponent(AuthActivityComponent.class).plus(new RegisterFragmentModule(this)).inject(this);

    }

    @Override
    protected String getFragmentName() {
        return null;
    }

    @OnClick(R.id.login_register_btn)
    void onButtonClick(View v){
        //presenter.register(email.getText().toString(), password.getText().toString(), displayName.getText().toString(), passwordConfirm.getText().toString());
    }


/*    @Override
    public void showValidationError(ValidError validError) {
        Toast.makeText(authActivity, validError.getMsg(), Toast.LENGTH_SHORT).show();
        if (validError.getFocus()== ValidError.EMAIL){
            email.requestFocus();
        }else if (validError.getFocus()==ValidError.PASSWORD){
            password.requestFocus();
        }else if (validError.getFocus()==ValidError.PASSWORDCONFIRM){
            passwordConfirm.requestFocus();
        }
    }*/

/*    @Override
    public void registerCompleted(User user,String type) {
        authManager.loginCompleted(user,type);
        authActivity.finish();
    }*/
}
