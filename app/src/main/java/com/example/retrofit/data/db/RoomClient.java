package com.example.retrofit.data.db;

import android.content.Context;

import androidx.room.Room;

import com.example.retrofit.data.db.daos.CharacterDao;
import com.example.retrofit.data.db.daos.EpisodeDao;
import com.example.retrofit.data.db.daos.LocationDao;

public class RoomClient {

    public AppDatabase provideDatabase(Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, "rick_and_morty_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries() // для тестинга
                .build();
    }

    public CharacterDao provideCharacterDao(AppDatabase database) {
        return database.characterDao();
    }

    public EpisodeDao provideEpisodeDao(AppDatabase database){
        return  database.episodeDao();
    }

    public LocationDao provideLocationDao(AppDatabase database){
        return database.locationDao();
    }

}
