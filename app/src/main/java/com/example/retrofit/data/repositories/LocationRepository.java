package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocation(
            int page
    ) {

        MutableLiveData<RickAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiService.fetchLocation(page).enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                if (response.body() != null){
                    App.locationDao.InsertAll(response.body().getResults());
                    data.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                data.postValue(null);

            }
        });

        return data;
    }

    public List<LocationModel> getLocation() {
        return App.locationDao.getAll();
    }
}

