package com.aftarfadilah.a160421095hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

const val DB_NAME = "hobbydb"

@Database(entities = [User::class, Hobby::class], version = 2)
abstract class HobbyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun hobbyDao(): HobbyDao

    companion object {
        @Volatile private var instance: HobbyDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context): HobbyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                HobbyDatabase::class.java,
                DB_NAME
            ).addMigrations(MIGRATION_1_2)
                .build()
        }

        operator fun invoke(context: Context): HobbyDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `User_new` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`firstName` TEXT, " +
                    "`lastName` TEXT, " +
                    "`password` TEXT, " +
                    "`username` TEXT)"
        )

        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `Hobby_new` (" +
                    "`uuid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`image` TEXT, " +
                    "`title` TEXT, " +
                    "`author` TEXT, " +
                    "`desc` TEXT, " +
                    "`dateCreated` TEXT)"
        )

        database.execSQL(
            "INSERT INTO `User_new` (`id`, `firstName`, `lastName`, `password`, `username`)" +
                    "SELECT `id`, `firstName`, `lastName`, `password`, `username` FROM `User`"
        )

        database.execSQL(
            "INSERT INTO `Hobby_new` (`uuid`, `image`, `title`, `author`, `desc`)" +
                    "SELECT `uuid`, `image`, `title`, `author`, `desc` FROM `Hobby`"
        )

        database.execSQL("DROP TABLE `User`")
        database.execSQL("DROP TABLE `Hobby`")

        database.execSQL("ALTER TABLE `User_new` RENAME TO `User`")
        database.execSQL("ALTER TABLE `Hobby_new` RENAME TO `Hobby`")
    }
}
