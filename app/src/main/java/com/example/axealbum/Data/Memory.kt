package com.example.axealbum.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
data class Memory(@PrimaryKey var title: String,
                       @ColumnInfo(name = "image") var image: Int,
                       @ColumnInfo(name = "comment") var comment: String,
                       @ColumnInfo(name = "landmark") var landmark: String,
                        @ColumnInfo(name = "year") var year: String

){
    constructor():this("", 0, "", "", "")
}
