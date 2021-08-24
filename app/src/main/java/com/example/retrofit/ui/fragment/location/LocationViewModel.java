package com.example.retrofit.ui.fragment.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.App;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {
    MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocation() {
        MutableLiveData<RickAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiService.fetchLocation().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);

            }
        });


        return data;
    }
}
