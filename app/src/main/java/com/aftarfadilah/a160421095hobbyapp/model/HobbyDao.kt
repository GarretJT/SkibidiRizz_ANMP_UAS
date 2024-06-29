package com.aftarfadilah.a160421095hobbyapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface HobbyDao {
    @Query("SELECT * FROM hobby")
    fun selectAllHobby():List<Hobby>

    @Query("SELECT * FROM Hobby WHERE uuid=:id")
    fun selectHobbyById(id:Int):Hobby

    @Delete
    fun deleteHobby(hobby: Hobby)
}
