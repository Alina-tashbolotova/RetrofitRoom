package com.example.retrofit.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<ViewModel extends BaseViewModel, Binding extends ViewBinding> extends Fragment {

    protected ViewModel viewModel;
    protected Binding viewBinding;


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setupObservers();
        checkInternet();

    }

    protected void initialize() {
    }

    protected void setupRequests() {
    }

    protected void setupOffRequests() {
    }

    protected void setupObservers() {
    }

    protected void checkInternet() {
    }

    protected void setupRecycler() {

    }


}
