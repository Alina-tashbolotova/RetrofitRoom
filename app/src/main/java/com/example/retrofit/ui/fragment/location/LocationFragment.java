package com.example.retrofit.ui.fragment.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentLocationBinding;
import com.example.retrofit.ui.adapters.LocationAdapter;


public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {

    private LocationAdapter locationAdapter = new LocationAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentLocationBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }


    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        setupRecycler();
    }


    @Override
    protected void setupRecycler() {
        super.setupRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        viewBinding.recyclerLocation.setLayoutManager(linearLayoutManager);
        viewBinding.recyclerLocation.setAdapter(locationAdapter);

        locationAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    protected void setupRequests() {
        if (!fetchInternetLocation()) {
            locationAdapter.submitList(viewModel.getLocation());
        }

        viewBinding.recyclerLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getItemCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + postVisiblesItems) >= totalItemCount) {
                        viewModel.page++;
                        fetchInternetLocation();
                    }
                }
            }
        });
    }


    private boolean fetchInternetLocation() {
        if (checkInternet()) {
            viewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse -> {
                locationAdapter.submitList(locationModelRickAndMortyResponse.getResults());
            });
            return true;
        } else {
            return false;
        }
    }
}