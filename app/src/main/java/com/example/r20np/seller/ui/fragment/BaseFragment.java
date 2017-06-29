package com.example.r20np.seller.ui.fragment;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r20np.seller.ui.component.HasComponent;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInitializeCompoent();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        onCreateView();
        return view;
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @LayoutRes
    protected abstract int getLayoutResId();
    protected abstract void onCreateView();
    protected abstract void getInitializeCompoent();
    protected abstract String getFragmentName();
}
