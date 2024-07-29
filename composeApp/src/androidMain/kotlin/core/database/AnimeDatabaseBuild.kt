package core.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getAnimeDatabaseBuild(ctx: Context): AnimeDatabase {
    val appContext = ctx.applicationContext
    return Room.databaseBuilder<AnimeDatabase>(
        context = appContext,
        name = appContext.getDatabasePath(DB_FILE_NAME).absolutePath
    ).fallbackToDestructiveMigration(true).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO).build()
}