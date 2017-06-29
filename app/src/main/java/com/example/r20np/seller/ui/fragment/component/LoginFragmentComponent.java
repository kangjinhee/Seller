package com.example.r20np.seller.ui.fragment.component;

import com.example.r20np.seller.ui.fragment.FragmentScope;
import com.example.r20np.seller.ui.fragment.LoginFragment;
import com.example.r20np.seller.ui.fragment.module.LoginFragmentModule;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = LoginFragmentModule.class
)
public interface LoginFragmentComponent {
        LoginFragment inject(LoginFragment loginFragment);

}
