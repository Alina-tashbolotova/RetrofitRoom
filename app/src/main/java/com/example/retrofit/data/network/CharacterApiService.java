package com.example.retrofit.data.network;

import com.example.retrofit.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<Character>> fetchCharacters();
}
