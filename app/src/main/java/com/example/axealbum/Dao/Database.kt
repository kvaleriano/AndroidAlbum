package com.example.axealbum.Dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.axealbum.Data.Landmark
import com.example.axealbum.Data.Memory

@Database(entities = arrayOf(Landmark::class, Memory::class), version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun landmarkDao(): LandmarkDao
    abstract fun memoryDao(): MemoryDao

    companion object {
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase? {
            if (INSTANCE == null) {
                synchronized(DataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        DataBase::class.java, "axealbum.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}