package com.aftarfadilah.a160421095hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hobby(
    @ColumnInfo("image")
    var image:String,
    @ColumnInfo("title")
    var title:String,
    @ColumnInfo("author")
    var author:String,
    @ColumnInfo("desc")
    var desc:String,
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}
