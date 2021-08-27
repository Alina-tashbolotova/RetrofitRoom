package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode() {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiService.fetchEpisode().enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                App.episodeDao.InsertAll(response.body().getResults());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.setValue(null);

            }
        });

        return data;
    }

    public List<EpisodeModel> getEpisode() {
        return App.episodeDao.getAll();
    }

}
