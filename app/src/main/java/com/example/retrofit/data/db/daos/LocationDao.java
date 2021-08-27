package com.example.retrofit.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.retrofit.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(ArrayList<LocationModel> locationModel);

   @Query("SELECT * FROM locationModel")
    List<LocationModel> getAll();
}
