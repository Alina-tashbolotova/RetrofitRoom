package com.example.retrofit.ui.fragment.location;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.base.BaseViewModel;
import com.example.retrofit.data.repositories.LocationRepository;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

public class LocationViewModel extends BaseViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocation() {
        return locationRepository.fetchLocation(page);
    }

    public List<LocationModel> getLocation() {
        return locationRepository.getLocation();
    }

}
