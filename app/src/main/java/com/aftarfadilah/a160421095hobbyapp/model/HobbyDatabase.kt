package com.aftarfadilah.a160421095hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.aftarfadilah.a160421095hobbyapp.util.DB_NAME

@Database(entities = [User::class], version = 1)
abstract class HobbyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: HobbyDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context): HobbyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                HobbyDatabase::class.java,
                DB_NAME
            ).build()
        }

        operator fun invoke(context: Context): HobbyDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
    }
}
