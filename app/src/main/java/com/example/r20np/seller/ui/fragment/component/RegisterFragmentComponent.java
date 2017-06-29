package com.example.r20np.seller.ui.fragment.component;


import com.example.r20np.seller.ui.fragment.FragmentScope;
import com.example.r20np.seller.ui.fragment.RegisterFragment;
import com.example.r20np.seller.ui.fragment.module.RegisterFragmentModule;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = RegisterFragmentModule.class
)
public interface RegisterFragmentComponent {
        RegisterFragment inject(RegisterFragment registerFragment);

}
