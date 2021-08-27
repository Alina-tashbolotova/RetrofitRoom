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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofit.databinding.FragmentLocationBinding;
import com.example.retrofit.ui.adapters.LocationAdapter;


public class LocationFragment extends Fragment {
    private LocationViewModel locationViewModel;
    private FragmentLocationBinding binding;
    private LocationAdapter locationAdapter = new LocationAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        checkInternetLocation();
    }


    private void initialize() {
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        setUpLocationRecycler();
    }

    private void setUpRequestsLocation() {
        locationViewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse ->
                locationAdapter.addList(locationModelRickAndMortyResponse.getResults()));
    }


    private void setUpLocationRecycler() {
        binding.recyclerLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerLocation.setAdapter(locationAdapter);

        locationAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });
    }

    public void setUpOffRequestsLocations() {
        locationAdapter.addList(locationViewModel.getLocation());
    }

    private void checkInternetLocation() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            setUpRequestsLocation();

        } else {
            setUpOffRequestsLocations();

        }
    }


}