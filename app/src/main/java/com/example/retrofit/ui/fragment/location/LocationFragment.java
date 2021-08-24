package com.example.retrofit.ui.fragment.location;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        setUpRequests();
    }

    private void setUpRequests() {
        locationViewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse ->
                locationAdapter.addList(locationModelRickAndMortyResponse.getResults()));
    }

    private void initialize() {
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        setUpLocationRecycler();
    }

    private void setUpLocationRecycler() {
        binding.recyclerLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerLocation.setAdapter(locationAdapter);

        locationAdapter.setOnItemClickListener(position -> {
            Toast.makeText(requireContext(), "Click position" + position, Toast.LENGTH_SHORT).show();

        });
    }
}