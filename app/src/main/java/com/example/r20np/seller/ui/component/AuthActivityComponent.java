package com.example.r20np.seller.ui.component;

import com.example.r20np.seller.ui.activity.ActivityScope;
import com.example.r20np.seller.ui.activity.AuthActivity;
import com.example.r20np.seller.ui.fragment.component.LoginFragmentComponent;
import com.example.r20np.seller.ui.fragment.component.RegisterFragmentComponent;
import com.example.r20np.seller.ui.fragment.module.LoginFragmentModule;
import com.example.r20np.seller.ui.fragment.module.RegisterFragmentModule;
import com.example.r20np.seller.ui.module.AuthActivityModule;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(
        modules = AuthActivityModule.class
)
public interface AuthActivityComponent extends ActivityComponent {
        AuthActivity inject(AuthActivity authActivity);
        LoginFragmentComponent plus(LoginFragmentModule loginFragmentModule);
        RegisterFragmentComponent plus(RegisterFragmentModule registerFragmentModule);
        //UserDetailFragmentComponent plus(UserDetailFragmentModule module);
}
