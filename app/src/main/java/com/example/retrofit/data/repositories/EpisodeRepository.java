package com.example.retrofit.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RickAndMortyResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode(
            int page
    ) {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiService.fetchEpisode(page).enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(@NotNull Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                Log.e("tag", "tag");
                if (response.body() != null) {
                    App.episodeDao.InsertAll(response.body().getResults());
                    data.setValue(response.body());

                }


            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.postValue(null);

            }
        });

        return data;
    }

    public List<EpisodeModel> getEpisode() {
        return App.episodeDao.getAll();
    }

}
