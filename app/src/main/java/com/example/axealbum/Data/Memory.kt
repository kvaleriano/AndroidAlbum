package com.example.axealbum.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
data class Memory(@ColumnInfo(name = "title") var title: String,
                       @ColumnInfo(name = "image") var image: Int,
                       @ColumnInfo(name = "comment") var comment: String,
                       @ColumnInfo(name = "landmark") var landmark: String,
                        @ColumnInfo(name = "landmarkID") var year: String

){
    constructor():this("", 0, "", "", "")
}
