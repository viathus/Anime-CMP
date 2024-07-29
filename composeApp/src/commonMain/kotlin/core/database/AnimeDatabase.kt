package core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import core.database.dao.AnimeDao
import core.database.model.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1)
abstract class AnimeDatabase : RoomDatabase(), DB {
    abstract fun getAnimeDao(): AnimeDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables(): Unit {}
}

internal const val DB_FILE_NAME = "anime.db"
