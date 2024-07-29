package app

import core.database.AnimeDatabase
import core.database.getAnimeDatabaseBuild
import org.koin.dsl.module

actual val platformModule = module {
    single<AnimeDatabase> { getAnimeDatabaseBuild() }
}