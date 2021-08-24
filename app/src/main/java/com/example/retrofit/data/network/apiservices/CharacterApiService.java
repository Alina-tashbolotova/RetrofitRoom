package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.RickAndMortyResponse;
import com.example.retrofit.model.CharacterModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters();

    @GET("api/character/{id}")
    Call<CharacterModel> fetchId(@Path("id") int id);
}
