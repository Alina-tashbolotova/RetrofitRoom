package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationApiService {
    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation(
            @Query("page") int page
    );
}
