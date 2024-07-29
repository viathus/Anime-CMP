package core.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

fun getAnimeDatabaseBuild(): AnimeDatabase {
    val dbFilePath = "${NSHomeDirectory()}/$DB_FILE_NAME}"
    return Room.databaseBuilder<AnimeDatabase>(
        name = dbFilePath,
        factory = { AnimeDatabase::class.instantiateImpl() }
    ).fallbackToDestructiveMigration(true).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO).build()
}