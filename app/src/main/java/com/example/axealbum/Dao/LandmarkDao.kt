package com.example.axealbum.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.axealbum.Data.Landmark

@Dao
interface LandmarkDao {

    @Query("SELECT * from Landmark")
    fun getAll(): List<Landmark>

    @Query("SELECT * from Landmark WHERE year = :year")
    fun getAllByYear(year: String): List<Landmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(landmark: Landmark)

    @Query("DELETE from Landmark")
    fun deleteAll()
}