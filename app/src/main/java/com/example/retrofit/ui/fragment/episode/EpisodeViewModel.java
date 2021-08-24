package com.example.retrofit.ui.fragment.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.App;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {

    MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode() {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiService.fetchEpisode().enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.setValue(null);

            }
        });

        return data;
    }
}
