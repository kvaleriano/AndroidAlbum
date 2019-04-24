package com.example.axealbum.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.axealbum.Data.Memory

@Dao
interface MemoryDao {

    @Query("SELECT * from Memory")
    fun getAll(): List<Memory>

    @Insert(onConflict = REPLACE)
    fun insert(memory: Memory)

    @Query("DELETE from Memory")
    fun deleteAll()
}