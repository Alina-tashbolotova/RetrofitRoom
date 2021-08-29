package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EpisodeApiService {

    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisode(
            @Query("page") int page
    );
}
