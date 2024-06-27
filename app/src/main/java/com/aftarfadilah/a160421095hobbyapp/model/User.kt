package com.aftarfadilah.a160421095hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "username")
    var username: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
