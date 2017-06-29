package com.example.r20np.seller.ui.fragment.module;


import com.example.r20np.seller.ui.fragment.FragmentScope;
import com.example.r20np.seller.ui.fragment.RegisterFragment;
import com.example.r20np.seller.ui.fragment.contract.RegisterFragmentContract;
import com.example.r20np.seller.ui.fragment.presenter.RegisterFragmentPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class RegisterFragmentModule {
    private RegisterFragment registerFragment;
    private final RegisterFragmentContract.View mView;

    public RegisterFragmentModule(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
        this.mView = registerFragment;

    }

    @Provides
    @FragmentScope
    RegisterFragmentContract.View provideRegisterFragmentContractView() {
        return mView;
    }

    @Provides
    @FragmentScope
    RegisterFragment provideRegisterFragment() {
        return registerFragment;
    }

    @Provides
    @FragmentScope
    RegisterFragmentPresenter provideRegisterFragmentPresenter() {
        return new RegisterFragmentPresenter(registerFragment);
    }


}
