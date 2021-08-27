package com.example.retrofit.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofit.model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(ArrayList<EpisodeModel> episodeModel);

    @Query("SELECT * FROM episodeModel")
    List<EpisodeModel> getAll();
}
