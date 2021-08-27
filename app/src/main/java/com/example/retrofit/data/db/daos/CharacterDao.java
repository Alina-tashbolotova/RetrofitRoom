package com.example.retrofit.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofit.model.CharacterModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// для замены одинаковых items
    void insertAll(ArrayList<CharacterModel> characterModel);

    @Query("SELECT * FROM charactermodel")
    List<CharacterModel> getAll();
}
