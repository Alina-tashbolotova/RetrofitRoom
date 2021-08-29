package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters(
           @Query("page") int page
    );

    @GET("api/character/{id}")
    Call<CharacterModel> fetchId(@Path("id") int id);
}
