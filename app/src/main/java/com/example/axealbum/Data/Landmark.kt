package com.example.axealbum.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
data class Landmark(
                    @ColumnInfo(name = "titler") var title: String,
                  @ColumnInfo(name = "image") var image: Int,
                    @ColumnInfo(name = "year") var year: String

){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    constructor():this("", 0, "")
}
