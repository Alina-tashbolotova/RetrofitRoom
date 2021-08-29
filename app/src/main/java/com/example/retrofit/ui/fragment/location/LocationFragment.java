package com.example.retrofit.ui.fragment.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import org.jetbrains.annotations.NotNull;


public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {


    private LocationViewModel locationViewModel;
    private FragmentLocationBinding binding;
    private LocationAdapter locationAdapter = new LocationAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    protected void initialize() {
        super.initialize();
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        setupRecycler();
    }

    @Override
    protected void setupRequests() {
        super.setupRequests();
        locationViewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse ->
                locationAdapter.addList(locationModelRickAndMortyResponse.getResults()));
    }


    @Override
    protected void setupRecycler() {
        super.setupRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerLocation.setLayoutManager(linearLayoutManager);
        binding.recyclerLocation.setAdapter(locationAdapter);

        locationAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });

        binding.recyclerLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getItemCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + postVisiblesItems) >= totalItemCount) {
                        locationViewModel.page++;
                        locationViewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse -> {
                            locationAdapter.addList(locationModelRickAndMortyResponse.getResults());
                        });


                    }

                }
            }
        });
    }

    @Override
    protected void setupOffRequests() {
        super.setupOffRequests();
        locationAdapter.addList(locationViewModel.getLocation());
    }


    @Override
    protected void checkInternet() {
        super.checkInternet();
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            setupRequests();

        } else {
            setupOffRequests();

        }
    }
}