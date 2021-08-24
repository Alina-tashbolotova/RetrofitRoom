package com.example.retrofit;

import android.app.Application;
import com.example.retrofit.data.network.RetrofitClient;
import com.example.retrofit.data.network.apiservices.CharacterApiService;
import com.example.retrofit.data.network.apiservices.EpisodeApiService;
import com.example.retrofit.data.network.apiservices.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService;
    public static LocationApiService locationApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        characterApiService = new RetrofitClient().provideCharacterApService();
        episodeApiService = new RetrofitClient().provideEpisodeApiService();
        locationApiService = new RetrofitClient().provideLocationApiService();



    }


}
