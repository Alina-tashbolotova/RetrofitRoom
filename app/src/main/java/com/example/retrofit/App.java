package com.example.retrofit;

import android.app.Application;

import com.example.retrofit.data.db.RoomClient;
import com.example.retrofit.data.db.daos.CharacterDao;
import com.example.retrofit.data.db.daos.EpisodeDao;
import com.example.retrofit.data.db.daos.LocationDao;
import com.example.retrofit.data.network.RetrofitClient;
import com.example.retrofit.data.network.apiservices.CharacterApiService;
import com.example.retrofit.data.network.apiservices.EpisodeApiService;
import com.example.retrofit.data.network.apiservices.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService;
    public static LocationApiService locationApiService;
    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodeDao episodeDao;

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitClient retrofitClient = new RetrofitClient();
        characterApiService = retrofitClient.provideCharacterApService();
        episodeApiService = retrofitClient.provideEpisodeApiService();
        locationApiService = retrofitClient.provideLocationApiService();

        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideDatabase(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideDatabase(this));





    }


}
