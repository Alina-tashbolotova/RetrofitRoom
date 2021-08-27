package com.example.retrofit.ui.fragment.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.data.repositories.LocationRepository;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

public class LocationViewModel extends ViewModel {

    private final LocationRepository locationRepository = new LocationRepository();

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocation() {
        return locationRepository.fetchLocation();
    }

    public List<LocationModel> getLocation() {
        return locationRepository.getLocation();
    }

}
