package com.example.retrofit.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.retrofit.data.db.daos.CharacterDao;
import com.example.retrofit.data.db.daos.EpisodeDao;
import com.example.retrofit.data.db.daos.LocationDao;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.LocationModel;

@Database(entities = {CharacterModel.class, EpisodeModel.class, LocationModel.class},version = 3)
abstract class AppDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao();
    public abstract EpisodeDao episodeDao();
    public abstract LocationDao locationDao();
}
