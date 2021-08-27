package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> fetchCharacter() {
        MutableLiveData<RickAndMortyResponse<CharacterModel>> data = new MutableLiveData<>();

        App.characterApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<CharacterModel>> call, Response<RickAndMortyResponse<CharacterModel>> response) {
                App.characterDao.insertAll(response.body().getResults());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<CharacterModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<CharacterModel> fetchId(int id) {
        MutableLiveData<CharacterModel> liveData = new MutableLiveData<>();

        App.characterApiService.fetchId(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CharacterModel> call, Throwable t) {
                liveData.setValue(null);

            }
        });
        return liveData;
    }

    public List<CharacterModel> getCharacters() {
        return App.characterDao.getAll();
    }
}
