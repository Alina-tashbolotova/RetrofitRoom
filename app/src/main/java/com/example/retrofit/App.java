package com.example.retrofit;

import android.app.Application;

import com.example.retrofit.data.network.CharacterApiService;
import com.example.retrofit.data.network.RetrofitClient;

public class App extends Application {

    public static CharacterApiService characterApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        characterApiService = new RetrofitClient().provideCharacterApService();
    }


}
